package <%= package %>.database.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import <%= package %>.database.entity.Sample;

import java.util.Date;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface SampleDao {

    @Insert(onConflict = REPLACE)
    void save(Sample user);

    @Query("SELECT * FROM sample WHERE login = :userLogin")
    LiveData<Sample> load(String userLogin);

    @Query("SELECT * FROM sample WHERE login = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    Sample hasUser(String userLogin, Date lastRefreshMax);
}