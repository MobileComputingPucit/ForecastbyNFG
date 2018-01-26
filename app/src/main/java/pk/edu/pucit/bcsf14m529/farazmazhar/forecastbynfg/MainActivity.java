package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.location.Location;
import android.widget.ImageView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener ,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    RequestQueue requestQueue;

    ImageView settingButton, locationButton;
    RecyclerView recyclerView;

    ArrayList<Weather> weatherArrayList = null;
    Adapter _refAdapter;

    SwipeRefreshLayout swipeRefreshLayout;

    private Location mLastLocation;

    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;

    double latitude, longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createWidget();
        initializeObject();

        locationButton.setOnClickListener(this);
        settingButton.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(_refAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Toast.makeText(getApplicationContext(),"Refreshing ...", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(true);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        displayLocation();
                    }
                },3000);
            }
        });


        buildGoogleApiClient();
        //displayLocation();

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.locationsIcon_main_xml)
        {
            Intent intent  = new Intent(getApplicationContext(),LocationsActivity.class);
            startActivity(intent);
        }
        else if(view.getId() == R.id.settings_btn_main_xml)
        {
            Intent intent  = new Intent(getApplicationContext(),settings.class);
            startActivity(intent);
        }
    }


    private void get_weather()
    {
        weatherArrayList.clear();

        StringRequest request = new StringRequest(Request.Method.POST, getLocationURl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"Updated !", Toast.LENGTH_SHORT).show();
                Log.i("response->",response);
                parse_response(response);
                _refAdapter.updateRecord(weatherArrayList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "There appears to be a problem, Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(request);
    }


    private void parse_response(String response)
    {
        Weather _refWeather = new Weather();

        try
        {
            JSONObject recordHolder = new JSONObject(response);
            JSONArray jsonArray = recordHolder.getJSONArray("weather");

            if(jsonArray.length()>0)
            {

                JSONObject weatherStatusDetails  = jsonArray.getJSONObject(0);
                _refWeather.setWeather(weatherStatusDetails.getString("main"));

            }

            if(SettingsMenu.TemperatureUnit.trim().equals("C"))
            {
                int temperatureInKelvin = recordHolder.getJSONObject("main").getInt("temp");
                int minTemperatureInKelvin = recordHolder.getJSONObject("main").getInt("temp_min");
                int highTemperatureInKelvin =  recordHolder.getJSONObject("main").getInt("temp_max");

                _refWeather.setCurrent_temperature(String.valueOf(Kelvin2Celsius(temperatureInKelvin)));
                _refWeather.setHigh_temperature(String.valueOf(Kelvin2Celsius(minTemperatureInKelvin)));
                _refWeather.setLow_temperature(String.valueOf(Kelvin2Celsius(highTemperatureInKelvin)));

            }
            _refWeather.setHumidity(recordHolder.getJSONObject("main").getString("humidity"));

            _refWeather.setVisibility(recordHolder.getString("visibility"));
            _refWeather.setWind(recordHolder.getJSONObject("wind").getString("speed"));


            weatherArrayList.add(_refWeather);

        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private int Kelvin2Celsius(int kelvin)
    {
        return kelvin - 273;
    }



    private String getLocationURl()
    {
        StringBuilder weatherUrl= new StringBuilder("http://api.openweathermap.org/data/2.5/weather?");
        weatherUrl.append("lat=" + String.valueOf(latitude));
        weatherUrl.append("&lon=" + String.valueOf(longitude));
        weatherUrl.append("&appid=1a2a0d8fa5b0cb9e70bd9bbcf19f084c");

        return weatherUrl.toString();
    }

    private String getUrl()
    {

        StringBuilder weatherUrl= new StringBuilder("http://api.openweathermap.org/data/2.5/weather?");
        weatherUrl.append("q=" + "LAHORE,PAKISTAN");
        weatherUrl.append("&appid=1a2a0d8fa5b0cb9e70bd9bbcf19f084c");

        return weatherUrl.toString();
    }

    private void createWidget()
    {
        settingButton = (ImageView) findViewById(R.id.settings_btn_main_xml);
        locationButton = (ImageView) findViewById(R.id.locationsIcon_main_xml);

        recyclerView =(RecyclerView) findViewById(R.id.recyclerView_main_xml);
        swipeRefreshLayout =(SwipeRefreshLayout) findViewById(R.id.swiperefresh_main_xml);

    }

    private void initializeObject()
    {
        requestQueue = Volley.newRequestQueue(this);
        weatherArrayList = new ArrayList<>();

        _refAdapter = new Adapter(getApplicationContext(),weatherArrayList);
    }


    public void displayLocation() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},101);

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            mLastLocation = LocationServices.FusedLocationApi
                    .getLastLocation(mGoogleApiClient);

            if (mLastLocation != null) {
                 latitude = mLastLocation.getLatitude();
                 longitude = mLastLocation.getLongitude();

                //Toast.makeText(getApplicationContext(),"Your Location:"+ latitude+" , "+longitude,Toast.LENGTH_SHORT).show();

                get_weather();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Location not Accesable",Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        displayLocation();
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }
}
