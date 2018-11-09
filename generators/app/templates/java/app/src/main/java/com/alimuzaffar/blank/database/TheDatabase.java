package <%= package %>.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import <%= package %>.database.converter.DateConverter;
import <%= package %>.database.converter.ListConverter;
import <%= package %>.database.dao.SampleDao;
import <%= package %>.database.entity.Sample;

@Database(entities = {
        Sample.class
}, version = 1)
@TypeConverters({DateConverter.class, ListConverter.class})
public abstract class TheDatabase extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile TheDatabase INSTANCE;

    // --- DAO ---
    public abstract SampleDao userDao();

}
