package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by faraz on 22-Jan-18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // ........................................................DB VERSION................................................//

    private static final int DATABASE_VERSION = 1;

    // ........................................................DB NAME................................................//

    private static final String DATABASE_NAME                = "weatherData";

    // ........................................................TABLES................................................//

    private static final String CITY_TABLE_NAME              = "city_table";            // This is the table name for the cities added by user.
    private static final String WEATHER_TABLE_NAME           = "weather_table";         // This is the table name for detailed weather information.
    private static final String FORECAST_TABLE_NAME          = "forecast_table";        // This is the table name for five day forecast.



    // ........................................................ATTRIBUTES................................................//

    private static final String CITY_ID                      = "id";                    // This is primary key. /INTEGER
    private static final String CITY_NAME                    = "name";                  // This is the name of the added city.
    private static final String CITY_COUNTRY                 = "country";               // This is the name of the country in which the city is located.
    private static final String CITY_LATITUDE                = "latitude";              // This is the latitude of the city.
    private static final String CITY_LONGITUDE               = "longitude";             // This is the longitude of the city.




    private static final String WEATHER_ID                  = "id";                     // This is primary key. /INTEGER
    private static final String WEATHER_CITY_ID             = "city_id";                // This is the id(foreign key) of a city. /INTEGER
    private static final String WEATHER_CURRENT_TEMPERATURE = "current_temperature";    // This will be the current temperature of the day.
    private static final String WEATHER_HIGH_TEMPERATURE    = "high_temperature";       // This will be the highest temperature of the day.
    private static final String WEATHER_LOW_TEMPERATURE     = "low_temperature";        // This will be the lowest temperature of the day.
    private static final String WEATHER_WIND                = "wind";                   // This will be the current wind speed.
    private static final String WEATHER_PRESSURE            = "pressure";               // This will be the current pressure.
    private static final String WEATHER_CLOUDS              = "clouds";                 // This will be the percentage of clouds in the sky.
    private static final String WEATHER_WEATHER             = "weather";                // This will be how the weather is... e.g. Sunny, smoke, raining, foggy etc.
    private static final String WEATHER_HUMIDITY            = "humidity";               // This will contain



    private static final String FORECAST_ID                 = "id";                     // This is primary key. /INTEGER
    private static final String FORECAST_CITY_ID            = "city_id";                // This is the id(foreign key) of a city.
    private static final String FORECAST_TODAY_WEATHER      = "today_weather";          // This will be how the weather is... e.g. Sunny, smoke, raining, foggy etc.
    private static final String FORECAST_TODAY_HIGH         = "today_high";             // This will be the highest temperature of the day.
    private static final String FORECAST_TODAY_LOW          = "today_low";              // This will be the lowest temperature of the day.
    private static final String FORECAST_DAY2_WEATHER       = "day2_weather";           // This will be how the weather is for day 2... e.g. Sunny, smoke, raining, foggy etc.
    private static final String FORECAST_DAY2_HIGH          = "day2_high";              // This will be the highest temperature of the day 2.
    private static final String FORECAST_DAY2_LOW           = "day2_low";               // This will be the lowest temperature of the day 2.
    private static final String FORECAST_DAY3_WEATHER       = "day3_weather";           // This will be how the weather is for day 3... e.g. Sunny, smoke, raining, foggy etc.
    private static final String FORECAST_DAY3_HIGH          = "day3_high";              // This will be the highest temperature of the day 3.
    private static final String FORECAST_DAY3_LOW           = "day3_low";               // This will be the lowest temperature of the day 3.
    private static final String FORECAST_DAY4_WEATHER       = "day4_weather";           // This will be how the weather is for day 4... e.g. Sunny, smoke, raining, foggy etc.
    private static final String FORECAST_DAY4_HIGH          = "day4_high";              // This will be the highest temperature of the day 4.
    private static final String FORECAST_DAY4_LOW           = "day4_low";               // This will be the lowest temperature of the day 4.
    private static final String FORECAST_DAY5_WEATHER       = "day5_weather";           // This will be how the weather is for day 5... e.g. Sunny, smoke, raining, foggy etc.
    private static final String FORECAST_DAY5_HIGH          = "day5_high";              // This will be the highest temperature of the day 5.
    private static final String FORECAST_DAY5_LOW           = "day5_low";               // This will be the lowest temperature of the day 5.



    // ........................................................CRUD METHODS................................................//


    // .......................................CURD for City...................//
    public void addCity(City city)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CITY_NAME, city.getName());
        values.put(CITY_COUNTRY, city.getCountry());
        values.put(CITY_LATITUDE, city.getLatitude());
        values.put(CITY_LONGITUDE, city.getLongitude());
        db.insert(CITY_TABLE_NAME, null, values);
        db.close();
    }



    public City getCity(int id)
    {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(CITY_TABLE_NAME,
                                    new String[] { CITY_ID,
                                                    CITY_NAME,
                                                    CITY_COUNTRY,
                                                    CITY_LONGITUDE,
                                                    CITY_LATITUDE},
                                                    CITY_ID + "=?" + new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        City city = new City(Integer.parseInt(cursor.getString(0)),
                                                cursor.getString(1),
                                                cursor.getString(2),
                                                cursor.getString(3),
                                                cursor.getString(4));

        return city;
    }



    public List<City> getAllCities()
    {
        List<City> cityList = new ArrayList<City>();

        String selectQuery = "SELECT * FROM " + CITY_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                City city = new City(Integer.parseInt(cursor.getString(0)),
                                                        cursor.getString(1),
                                                        cursor.getString(2),
                                                        cursor.getString(3),
                                                        cursor.getString(4));

                cityList.add(city);
            } while (cursor.moveToNext());
        }

        return cityList;
    }



    public int getCityCount()
    {
        String countQuery = "SELECT  * FROM " + CITY_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }


    public int updateCity(City city)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CITY_NAME, city.getName());
        values.put(CITY_COUNTRY, city.getCountry());
        values.put(CITY_LONGITUDE, city.getLongitude());
        values.put(CITY_LATITUDE, city.getLatitude());

        return db.update(CITY_TABLE_NAME,
                            values,
                            CITY_ID + " =?",
                            new String[] { String.valueOf(city.getId()) });
    }



    public void deleteCity(City city)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(CITY_TABLE_NAME,
                    CITY_ID + " =?",
                    new String[] { String.valueOf(city.getId()) });
        db.close();
    }

    // .......................................CURD for WEATHER...................//

    public void addWeather(Weather weather)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WEATHER_CITY_ID, weather.getCity_id());
        values.put(WEATHER_CURRENT_TEMPERATURE, weather.getCurrent_temperature());
        values.put(WEATHER_HIGH_TEMPERATURE, weather.getHigh_temperature());
        values.put(WEATHER_LOW_TEMPERATURE, weather.getLow_temperature());
        values.put(WEATHER_WIND, weather.getWind());
        values.put(WEATHER_PRESSURE, weather.getPressure());
        values.put(WEATHER_CLOUDS, weather.getClouds());
        values.put(WEATHER_WEATHER, weather.getWeather());
        values.put(WEATHER_HUMIDITY, weather.getHumidity());
        db.insert(CITY_TABLE_NAME, null, values);
        db.close();
    }



    public Weather getWeather(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(WEATHER_TABLE_NAME,
                                    new String[] { WEATHER_ID,
                                            WEATHER_CITY_ID,
                                            WEATHER_CURRENT_TEMPERATURE,
                                            WEATHER_HIGH_TEMPERATURE,
                                            WEATHER_LOW_TEMPERATURE,
                                            WEATHER_WIND,
                                            WEATHER_PRESSURE,
                                            WEATHER_CLOUDS,
                                            WEATHER_HUMIDITY,
                                            WEATHER_WEATHER},
                                            WEATHER_ID + "=?" + new String[] {String.valueOf(id)},null, null, null,null);

        if (cursor != null)
            cursor.moveToFirst();

        Weather weather = new Weather(Integer.parseInt(cursor.getString(0)),
                                                        Integer.parseInt(cursor.getString(1)),
                                                        cursor.getString(2),
                                                        cursor.getString(3),
                                                        cursor.getString(4),
                                                        cursor.getString(5),
                                                        cursor.getString(6),
                                                        cursor.getString(7),
                                                        cursor.getString(8),
                                                        cursor.getString(9));

        return weather;
    }


    public List<Weather> getAllWeather()
    {
        List<Weather> weatherList = new ArrayList<Weather>();

        String selectQuery = "SELECT * FROM " + WEATHER_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Weather weather = new Weather(Integer.parseInt(cursor.getString(0)),
                                                Integer.parseInt(cursor.getString(1)),
                                                cursor.getString(2),
                                                cursor.getString(3),
                                                cursor.getString(4),
                                                cursor.getString(5),
                                                cursor.getString(6),
                                                cursor.getString(7),
                                                cursor.getString(8),
                                                cursor.getString(9));

                weatherList.add(weather);
            } while (cursor.moveToNext());
        }

        return weatherList;
    }


    public int getWeatherCount()
    {
        String countQuery = "SELECT  * FROM " + WEATHER_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }


    public int updateWeather(Weather weather)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WEATHER_CITY_ID, weather.getCity_id());
        values.put(WEATHER_CURRENT_TEMPERATURE, weather.getCurrent_temperature());
        values.put(WEATHER_HIGH_TEMPERATURE, weather.getHigh_temperature());
        values.put(WEATHER_LOW_TEMPERATURE, weather.getLow_temperature());
        values.put(WEATHER_WIND, weather.getWind());
        values.put(WEATHER_PRESSURE, weather.getPressure());
        values.put(WEATHER_CLOUDS, weather.getClouds());
        values.put(WEATHER_WEATHER, weather.getWeather());
        values.put(WEATHER_HUMIDITY, weather.getHumidity());

        return db.update(WEATHER_TABLE_NAME,
                            values,
                            WEATHER_ID + " =?",
                            new String[] { String.valueOf(weather.getId()) });
    }


    public void deleteWeather(Weather weather)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(WEATHER_TABLE_NAME,
                    WEATHER_ID + " =?",
                    new String[] { String.valueOf(weather.getId()) });
        db.close();
    }

    // .......................................CURD for FORECAST...................//


    public void addForecast(Forecast forecast)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FORECAST_CITY_ID, forecast.getCity_id());
        values.put(FORECAST_TODAY_WEATHER, forecast.getToday_weather());
        values.put(FORECAST_TODAY_HIGH, forecast.getToday_high());
        values.put(FORECAST_TODAY_LOW, forecast.getToday_low());
        values.put(FORECAST_DAY2_WEATHER, forecast.getDay2_weather());
        values.put(FORECAST_DAY2_HIGH, forecast.getDay2_high());
        values.put(FORECAST_DAY2_LOW, forecast.getDay2_low());
        values.put(FORECAST_DAY3_WEATHER, forecast.getDay3_weather());
        values.put(FORECAST_DAY3_HIGH, forecast.getDay3_high());
        values.put(FORECAST_DAY3_LOW, forecast.getDay3_low());
        values.put(FORECAST_DAY4_WEATHER, forecast.getDay4_weather());
        values.put(FORECAST_DAY4_HIGH, forecast.getDay4_high());
        values.put(FORECAST_DAY4_LOW, forecast.getDay4_low());
        values.put(FORECAST_DAY5_WEATHER, forecast.getDay5_weather());
        values.put(FORECAST_DAY5_HIGH, forecast.getDay5_high());
        values.put(FORECAST_DAY5_LOW, forecast.getDay5_low());

        db.insert(FORECAST_TABLE_NAME, null, values);
        db.close();
    }

    public Forecast getForecast(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FORECAST_TABLE_NAME,
                                    new String[] { FORECAST_ID,
                                                    FORECAST_CITY_ID,
                                                    FORECAST_TODAY_WEATHER,
                                                    FORECAST_TODAY_HIGH,
                                                    FORECAST_TODAY_LOW,
                                                    FORECAST_DAY2_WEATHER,
                                                    FORECAST_DAY2_HIGH,
                                                    FORECAST_DAY2_LOW,
                                                    FORECAST_DAY3_WEATHER,
                                                    FORECAST_DAY3_HIGH,
                                                    FORECAST_DAY3_LOW,
                                                    FORECAST_DAY4_WEATHER,
                                                    FORECAST_DAY4_HIGH,
                                                    FORECAST_DAY4_LOW,
                                                    FORECAST_DAY5_WEATHER,
                                                    FORECAST_DAY5_HIGH,
                                                    FORECAST_DAY5_LOW},
                                                    FORECAST_ID + "=?" + new String[] {String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Forecast forecast = new Forecast(Integer.parseInt(cursor.getString(0)),
                                            Integer.parseInt(cursor.getString(1)),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getString(5),
                                            cursor.getString(6),
                                            cursor.getString(7),
                                            cursor.getString(8),
                                            cursor.getString(9),
                                            cursor.getString(10),
                                            cursor.getString(11),
                                            cursor.getString(12),
                                            cursor.getString(13),
                                            cursor.getString(14),
                                            cursor.getString(15),
                                            cursor.getString(16));

        return forecast;
    }

    public List<Forecast> getAllForecast() {
        List<Forecast> forecastList = new ArrayList<Forecast>();

        String selectQuery = "SELECT * FROM " + FORECAST_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Forecast forecast = new Forecast(Integer.parseInt(cursor.getString(0)),
                                                    Integer.parseInt(cursor.getString(1)),
                                                    cursor.getString(2),
                                                    cursor.getString(3),
                                                    cursor.getString(4),
                                                    cursor.getString(5),
                                                    cursor.getString(6),
                                                    cursor.getString(7),
                                                    cursor.getString(8),
                                                    cursor.getString(9),
                                                    cursor.getString(10),
                                                    cursor.getString(11),
                                                    cursor.getString(12),
                                                    cursor.getString(13),
                                                    cursor.getString(14),
                                                    cursor.getString(15),
                                                    cursor.getString(16));

                forecastList.add(forecast);
            } while (cursor.moveToNext());
        }

        return forecastList;
    }

    public int getForecastCount() {
        String countQuery = "SELECT  * FROM " + FORECAST_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

    public int updateForecast(Forecast forecast)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FORECAST_CITY_ID, forecast.getCity_id());
        values.put(FORECAST_TODAY_WEATHER, forecast.getToday_weather());
        values.put(FORECAST_TODAY_HIGH, forecast.getToday_high());
        values.put(FORECAST_TODAY_LOW, forecast.getToday_low());
        values.put(FORECAST_DAY2_WEATHER, forecast.getDay2_weather());
        values.put(FORECAST_DAY2_HIGH, forecast.getDay2_high());
        values.put(FORECAST_DAY2_LOW, forecast.getDay2_low());
        values.put(FORECAST_DAY3_WEATHER, forecast.getDay3_weather());
        values.put(FORECAST_DAY3_HIGH, forecast.getDay3_high());
        values.put(FORECAST_DAY3_LOW, forecast.getDay3_low());
        values.put(FORECAST_DAY4_WEATHER, forecast.getDay4_weather());
        values.put(FORECAST_DAY4_HIGH, forecast.getDay4_high());
        values.put(FORECAST_DAY4_LOW, forecast.getDay4_low());
        values.put(FORECAST_DAY5_WEATHER, forecast.getDay5_weather());
        values.put(FORECAST_DAY5_HIGH, forecast.getDay5_high());
        values.put(FORECAST_DAY5_LOW, forecast.getDay5_low());

        return db.update(WEATHER_TABLE_NAME,
                            values,
                            WEATHER_ID + " =?",
                            new String[] { String.valueOf(forecast.getId()) });
    }

    public void deleteForecast(Forecast forecast)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FORECAST_TABLE_NAME,
                    FORECAST_ID + " =?",
                    new String[] { String.valueOf(forecast.getId()) });
        db.close();
    }




    //.......................................... MAIN METHOD ....................................//

    public DatabaseHandler (Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_CITY_TABLE = "CREATE TABLE "  + CITY_TABLE_NAME + "("
                + CITY_ID           + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + CITY_NAME         + " TEXT, "
                + CITY_COUNTRY      + " TEXT, "
                + CITY_LONGITUDE    + " TEXT, "
                + CITY_LATITUDE     + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_CITY_TABLE);



        String CREATE_WEATHER_TABLE = "CREATE TABLE " + WEATHER_TABLE_NAME + "("
                + WEATHER_ID                     + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + WEATHER_CITY_ID                + " INTEGER, "
                + WEATHER_CURRENT_TEMPERATURE    + " TEXT, "
                + WEATHER_HIGH_TEMPERATURE       + " TEXT, "
                + WEATHER_LOW_TEMPERATURE        + " TEXT, "
                + WEATHER_WIND                   + " TEXT, "
                + WEATHER_PRESSURE               + " TEXT, "
                + WEATHER_CLOUDS                 + " TEXT, "
                + WEATHER_WEATHER                + " TEXT, "
                + WEATHER_HUMIDITY               + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_WEATHER_TABLE);



        String CREATE_FORECAST_TABLE = "CREATE TABLE " + FORECAST_TABLE_NAME + "("
                + FORECAST_ID                   + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + FORECAST_CITY_ID              + " INTEGER, "
                + FORECAST_TODAY_WEATHER        + " TEXT, "
                + FORECAST_TODAY_HIGH           + " TEXT, "
                + FORECAST_TODAY_LOW            + " TEXT, "
                + FORECAST_DAY2_WEATHER         + " TEXT, "
                + FORECAST_DAY2_HIGH            + " TEXT, "
                + FORECAST_DAY2_LOW             + " TEXT, "
                + FORECAST_DAY3_WEATHER         + " TEXT, "
                + FORECAST_DAY3_HIGH            + " TEXT, "
                + FORECAST_DAY3_LOW             + " TEXT, "
                + FORECAST_DAY4_WEATHER         + " TEXT, "
                + FORECAST_DAY4_HIGH            + " TEXT, "
                + FORECAST_DAY4_LOW             + " TEXT, "
                + FORECAST_DAY5_WEATHER         + " TEXT, "
                + FORECAST_DAY5_HIGH            + " TEXT, "
                + FORECAST_DAY5_LOW             + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_FORECAST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {

       /* sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CITY_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FORECAST_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WEATHER_TABLE_NAME);
        onCreate(sqLiteDatabase);*/
    }

}
