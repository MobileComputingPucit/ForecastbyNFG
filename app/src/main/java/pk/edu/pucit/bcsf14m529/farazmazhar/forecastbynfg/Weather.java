package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

/**
 * Created by faraz on 22-Jan-18.
 */

public class Weather {
    private int id;
    private int city_id;
    private String current_temperature;
    private String high_temperature;
    private String low_temperature;
    private String wind;
    private String pressure;
    private String clouds;
    private String weather;
    private String humidity;


    /**
     * ADT Weather.
     * @param id int
     * @param city_id int
     * @param current_temperature String
     * @param high_temperature String
     * @param low_temperature String
     * @param wind String
     * @param pressure String
     * @param clouds String
     * @param weather String
     * @param humidity String
     */
    public Weather(int id, int city_id, String current_temperature, String high_temperature, String low_temperature, String wind, String pressure, String clouds, String weather, String humidity) {
        this.id = id;
        this.city_id = city_id;
        this.current_temperature = current_temperature;
        this.high_temperature = high_temperature;
        this.low_temperature = low_temperature;
        this.wind = wind;
        this.pressure = pressure;
        this.clouds = clouds;
        this.weather = weather;
        this.humidity = humidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public String getCurrent_temperature() {
        return current_temperature;
    }

    public void setCurrent_temperature(String current_temperature) {
        this.current_temperature = current_temperature;
    }

    public String getHigh_temperature() {
        return high_temperature;
    }

    public void setHigh_temperature(String high_temperature) {
        this.high_temperature = high_temperature;
    }

    public String getLow_temperature() {
        return low_temperature;
    }

    public void setLow_temperature(String low_temperature) {
        this.low_temperature = low_temperature;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getClouds() {
        return clouds;
    }

    public void setClouds(String clouds) {
        this.clouds = clouds;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
