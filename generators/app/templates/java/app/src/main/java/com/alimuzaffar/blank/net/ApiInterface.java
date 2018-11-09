package <%= package %>.net;

import <%= package %>.database.entity.Sample;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("users/{user}")
    Call<Sample> getUser(@Path("user") String userId);
}
