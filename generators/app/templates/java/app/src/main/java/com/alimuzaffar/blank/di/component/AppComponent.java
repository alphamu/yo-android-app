package <%= package %>.di.component;

import android.app.Application;
import <%= package %>.App;
import <%= package %>.di.module.*;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class, FragmentModule.class, NetModule.class, ViewModelModule.class, RepositoryModule.class})
public interface AppComponent { 
 
    @Component.Builder 
    interface Builder { 
        @BindsInstance
        Builder application(Application application);
        AppComponent build();

    }
    void inject(App app);
} 