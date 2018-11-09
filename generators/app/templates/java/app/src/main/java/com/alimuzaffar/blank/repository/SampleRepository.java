package <%= package %>.repository;

import android.util.Log;
import android.widget.Toast;
import androidx.lifecycle.LiveData;
import <%= package %>.App;
import <%= package %>.database.dao.SampleDao;
import <%= package %>.database.entity.Sample;
import <%= package %>.net.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutorService;

@Singleton
public class SampleRepository {

    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final ApiInterface apiInterface;
    private final SampleDao sampleDao;
    private final ExecutorService executor;

    @Inject
    public SampleRepository(ApiInterface apiInterface, SampleDao sampleDao, ExecutorService executor) {
        this.apiInterface = apiInterface;
        this.sampleDao = sampleDao;
        this.executor = executor;
    }

    // ---

    public LiveData<Sample> getUser(String userLogin) {
        refreshUser(userLogin); // try to refresh data if possible from Github Api
        return sampleDao.load(userLogin); // return a LiveData directly from the database.
    }

    // ---

    private void refreshUser(final String userLogin) {
        executor.execute(() -> {
            // Check if user was fetched recently
            boolean userExists = (sampleDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
            // If user have to be updated
            if (!userExists) {
                apiInterface.getUser(userLogin).enqueue(new Callback<Sample>() {
                    @Override
                    public void onResponse(Call<Sample> call, Response<Sample> response) {
                        Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                        Toast.makeText(App.getContext(), "Data refreshed from network !", Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            Sample user = response.body();
                            user.setLastRefresh(new Date());
                            sampleDao.save(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<Sample> call, Throwable t) { }
                });
            }
        });
    }

    // ---

    private Date getMaxRefreshTime(Date currentDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
