package <%= package %>.database.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import <%= package %>.database.entity.Sample
import java.util.*

@Dao
interface SampleDao {

    @Insert(onConflict = REPLACE)
    fun save(user: Sample)

    @Query("SELECT * FROM sample WHERE login = :userLogin")
    fun load(userLogin: String): LiveData<Sample>

    @Query("SELECT * FROM sample WHERE login = :userLogin AND lastRefresh > :lastRefreshMax LIMIT 1")
    fun hasUser(userLogin: String, lastRefreshMax: Date): Sample
}