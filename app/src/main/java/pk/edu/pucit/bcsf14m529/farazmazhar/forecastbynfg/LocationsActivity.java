package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import java.util.ArrayList;
import java.util.Locale;

public class LocationsActivity extends AppCompatActivity implements View.OnClickListener{


    DatabaseHandler db;
    RecyclerView recyclerView;
    ImageView addNewLocaiton_btn;
    TextView popUpTexrView;

    SwipeRefreshLayout swipeRefreshLayout;
    locationMenuAdapter adapter;

    ArrayList<City> locationArrayList =null;

    int PLACE_AUTOCOMPLETE_REQUEST_CODE =  100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        createWidget();
        initializeObject();

        addNewLocaiton_btn.setOnClickListener(this);

        popUpTexrView.setVisibility(View.INVISIBLE);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        retrieveAllCitiesFromLocalDB();


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
                        retrieveAllCitiesFromLocalDB();
                    }
                },3000);
            }
        });


    }


    private void retrieveAllCitiesFromLocalDB()
    {
        locationArrayList = db.getAllCities();

        if(locationArrayList.size() ==0)
        {
            popUpTexrView.setVisibility(View.VISIBLE);
            return;
        }

        adapter.updateRecord(locationArrayList);
        Toast.makeText(getApplicationContext(),"Updated ... ", Toast.LENGTH_SHORT).show();
    }


    private void createWidget()
    {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_locations_xml);
        addNewLocaiton_btn = (ImageView) findViewById(R.id.addNewLocation_imageView_xml);
        popUpTexrView = (TextView) findViewById(R.id.popUp_textView_xml);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh_location_xml);

    }

    private void initializeObject()
    {
        locationArrayList = new ArrayList<>();
        db = new DatabaseHandler(this);
        adapter = new locationMenuAdapter(this, locationArrayList);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.addNewLocation_imageView_xml)
        {
            try
            {
                Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN).build(this);
                startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
            }
            catch (GooglePlayServicesRepairableException e)
            {

            }
            catch (GooglePlayServicesNotAvailableException e)
            {

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK)
            {
                Place place = PlaceAutocomplete.getPlace(this, data);

                Log.i("Address->",place.getAddress().toString());
                Log.i("name->",place.getName().toString());

                try
                {


                    City _refCity = new City();

                    Log.i("LATTI->",place.getLatLng().toString().split(" ")[1].split(",")[0].split("\\(")[1].trim().split("\\.")[0].trim());
                    Log.i("LONG->",place.getLatLng().toString().split(" ")[1].split(",")[1].split("\\)")[0].trim().split("\\.")[0].trim());
                    int saveLatt = Integer.parseInt(place.getLatLng().toString().split(" ")[1].split(",")[0].split("\\(")[1].trim().split("\\.")[0].trim());
                    int saveLong = Integer.parseInt(place.getLatLng().toString().split(" ")[1].split(",")[1].split("\\)")[0].trim().split("\\.")[0].trim());

                    _refCity.setLatitude(String.valueOf(saveLatt));
                    _refCity.setLongitude(String.valueOf(saveLong));
                    String[] addressplace = place.getAddress().toString().split(" ");
                    int size = addressplace.length;

                    if (size == 0) {
                        return;
                    }
                    _refCity.setName(addressplace[size - 2]);
                    _refCity.setCountry(addressplace[size - 1]);

                    Log.i("cityName", addressplace[size - 2]);
                    Log.i("countryName", addressplace[size - 1]);


                    db.addCity(_refCity);

                    retrieveAllCitiesFromLocalDB();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


            }
            else if (resultCode == PlaceAutocomplete.RESULT_ERROR)
            {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.i("Test", status.getStatusMessage());

            }
            else if (resultCode == RESULT_CANCELED)
            {
                // The user canceled the operation.
            }
        }
    }

}
