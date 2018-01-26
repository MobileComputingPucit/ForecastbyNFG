package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

/**
 * Created by faraz on 22-Jan-18.
 */

public class City {
    private int id;
    private String name;
    private String country;
    private String latitude;
    private String longitude;


    /**
     * ADT City.
     * @param id int
     * @param name String
     * @param country String
     * @param latitude String
     * @param longitude String
     */


    public City()
    {

    }
    public City(int id, String name, String country, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
