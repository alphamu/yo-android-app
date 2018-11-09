package <%= package %>.di.module;

import android.app.Application;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;
import <%= package %>.BuildConfig;
import <%= package %>.net.ApiInterface;
import <%= package %>.net.mock.MockApiImpl;
import <%= package %>.util.Prefs;
import com.google.gson.*;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Module
public class NetModule {
    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache, final Prefs prefs) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder();
            requestBuilder.method(original.method(), original.body());
            String auth = original.header("Authorization");

            if (!TextUtils.isEmpty(auth)) {
                requestBuilder.removeHeader("Authorization");
            }

            if (!TextUtils.isEmpty(prefs.getAccessToken())) {
                requestBuilder.header("Authorization", "Bearer " + prefs.getAccessToken());
            }

            String correlationId = UUID.randomUUID().toString();
            boolean hasAcceptHeaders = false;
            if (original.headers() != null) {
                for (String key : original.headers().names()) {
                    if (key.equalsIgnoreCase("Accept")) {
                        hasAcceptHeaders = true;
                        Log.d("NetModule", "HAS HEADERS");
                        break;
                    }
                }
            }
            requestBuilder
                    .header("Correlation-Id", correlationId);

            if (!hasAcceptHeaders) {
                requestBuilder
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json");

            }
            return chain.proceed(requestBuilder.build());
        });
        return httpClient.cache(cache)
                .followRedirects(false)
                .followSslRedirects(false)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.MINUTES)
                .build();

    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @SuppressWarnings("ConstantConditions")
    @Provides
    @Singleton
    public ApiInterface providesApiInterface(Retrofit retrofit) {
        // Uncomment everything here if the API comes online
        // and remember to change BASE_URL
        if (BuildConfig.FLAVOR.contains("mock")) {
            return new MockApiImpl();
        }
        return retrofit.create(ApiInterface.class);

    }

    @Provides
    @Singleton
    public Prefs providesPrefs(Application application) {
        return Prefs.getInstance(application.getApplicationContext());
    }

    @Provides
    public Resources providesResources(Application application) {
        return application.getResources();
    }
}
