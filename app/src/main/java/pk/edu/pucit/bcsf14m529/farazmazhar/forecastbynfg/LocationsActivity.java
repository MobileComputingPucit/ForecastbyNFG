package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LocationsActivity extends AppCompatActivity implements View.OnClickListener{


    DatabaseHandler db;
    RecyclerView recyclerView;
    ImageView addNewLocaiton_btn;
    TextView popUpTexrView;

    SwipeRefreshLayout swipeRefreshLayout;
    locationMenuAdapter adapter;

    ArrayList<City> locationArrayList =null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);

        createWidget();
        initializeObject();

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
        adapter = new locationMenuAdapter(getApplicationContext(), locationArrayList);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == R.id.addNewLocation_imageView_xml)
        {

        }
    }
}
