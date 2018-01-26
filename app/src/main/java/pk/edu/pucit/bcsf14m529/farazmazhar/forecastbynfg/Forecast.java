package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

/**
 * Created by faraz on 22-Jan-18.
 */

public class Forecast {

    private int id;
    private int city_id;
    private String today_weather;
    private String today_high;
    private String today_low;
    private String day2_weather;
    private String day2_high;
    private String day2_low;
    private String day3_weather;
    private String day3_high;
    private String day3_low;
    private String day4_weather;
    private String day4_high;
    private String day4_low;
    private String day5_weather;
    private String day5_high;
    private String day5_low;

    /**
     * ADT Forecast.
     * @param id int
     * @param city_id int
     * @param today_weather String
     * @param today_high String
     * @param today_low String
     * @param day2_weather String
     * @param day2_high String
     * @param day2_low String
     * @param day3_weather String
     * @param day3_high String
     * @param day3_low String
     * @param day4_weather String
     * @param day4_high String
     * @param day4_low String
     * @param day5_weather String
     * @param day5_high String
     * @param day5_low String
     */
    public Forecast(int id, int city_id, String today_weather, String today_high, String today_low, String day2_weather, String day2_high, String day2_low, String day3_weather, String day3_high, String day3_low, String day4_weather, String day4_high, String day4_low, String day5_weather, String day5_high, String day5_low) {
        this.id = id;
        this.city_id = city_id;
        this.today_weather = today_weather;
        this.today_high = today_high;
        this.today_low = today_low;
        this.day2_weather = day2_weather;
        this.day2_high = day2_high;
        this.day2_low = day2_low;
        this.day3_weather = day3_weather;
        this.day3_high = day3_high;
        this.day3_low = day3_low;
        this.day4_weather = day4_weather;
        this.day4_high = day4_high;
        this.day4_low = day4_low;
        this.day5_weather = day5_weather;
        this.day5_high = day5_high;
        this.day5_low = day5_low;
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

    public String getToday_weather() {
        return today_weather;
    }

    public void setToday_weather(String today_weather) {
        this.today_weather = today_weather;
    }

    public String getToday_high() {
        return today_high;
    }

    public void setToday_high(String today_high) {
        this.today_high = today_high;
    }

    public String getToday_low() {
        return today_low;
    }

    public void setToday_low(String today_low) {
        this.today_low = today_low;
    }

    public String getDay2_weather() {
        return day2_weather;
    }

    public void setDay2_weather(String day2_weather) {
        this.day2_weather = day2_weather;
    }

    public String getDay2_high() {
        return day2_high;
    }

    public void setDay2_high(String day2_high) {
        this.day2_high = day2_high;
    }

    public String getDay2_low() {
        return day2_low;
    }

    public void setDay2_low(String day2_low) {
        this.day2_low = day2_low;
    }

    public String getDay3_weather() {
        return day3_weather;
    }

    public void setDay3_weather(String day3_weather) {
        this.day3_weather = day3_weather;
    }

    public String getDay3_high() {
        return day3_high;
    }

    public void setDay3_high(String day3_high) {
        this.day3_high = day3_high;
    }

    public String getDay3_low() {
        return day3_low;
    }

    public void setDay3_low(String day3_low) {
        this.day3_low = day3_low;
    }

    public String getDay4_weather() {
        return day4_weather;
    }

    public void setDay4_weather(String day4_weather) {
        this.day4_weather = day4_weather;
    }

    public String getDay4_high() {
        return day4_high;
    }

    public void setDay4_high(String day4_high) {
        this.day4_high = day4_high;
    }

    public String getDay4_low() {
        return day4_low;
    }

    public void setDay4_low(String day4_low) {
        this.day4_low = day4_low;
    }

    public String getDay5_weather() {
        return day5_weather;
    }

    public void setDay5_weather(String day5_weather) {
        this.day5_weather = day5_weather;
    }

    public String getDay5_high() {
        return day5_high;
    }

    public void setDay5_high(String day5_high) {
        this.day5_high = day5_high;
    }

    public String getDay5_low() {
        return day5_low;
    }

    public void setDay5_low(String day5_low) {
        this.day5_low = day5_low;
    }
}
