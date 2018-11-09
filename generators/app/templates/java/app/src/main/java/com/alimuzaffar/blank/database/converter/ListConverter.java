package <%= package %>.database.converter;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ListConverter {
    public static Gson gson = new Gson();

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        return gson.toJson(list);
    }


    @TypeConverter
    public static List<Integer> fromStringToArrayListInt(String value) {
        Type listType = new TypeToken<List<Integer>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListIntToString(List<Integer> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Double> fromStringToArrayListDouble(String value) {
        Type listType = new TypeToken<List<Double>>() {}.getType();
        return gson.fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayListDoubleToString(List<Double> list) {
        return gson.toJson(list);
    }
}
