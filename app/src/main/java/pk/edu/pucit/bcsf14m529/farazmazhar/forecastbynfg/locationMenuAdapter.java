package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by hassan on 26-Jan-18.
 */

public class locationMenuAdapter  extends RecyclerView.Adapter<locationMenuAdapter.ViewChildLocation> {

    private ArrayList<City> menuData;
    private LayoutInflater layoutInflater;
    Context context;


    public locationMenuAdapter(Context context, ArrayList<City> menuData)
    {
        this.menuData = menuData;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public ViewChildLocation onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.location_list_row_layout, parent, false);
        ViewChildLocation viewHolder = new ViewChildLocation(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewChildLocation holder, int i) {

        final ViewChildLocation localHolder=holder;

        holder.cityName.setText(menuData.get(i).getName());
        holder.counteryName.setText(menuData.get(i).getCountry());


    }



    public void updateRecord(ArrayList<City> data)
    {
        this.menuData = data;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return menuData.size();
    }

    public class ViewChildLocation extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView cityName;
        TextView counteryName;
        RelativeLayout topLayout;


        private ViewChildLocation(View itemView)
        {
            super(itemView);

            cityName = itemView.findViewById(R.id.cityName_location_listRow_xml);
            counteryName = itemView.findViewById(R.id.country_location_listRow_xml);
            topLayout = itemView.findViewById(R.id.topLayout_list_row_layout);

            topLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            Log.i("Called->","Yes");

            Log.i("TL->LocalLATT",menuData.get(position).getLatitude());
            Log.i("TL->LocalLNG",menuData.get(position).getLongitude());
            Location.localLattidude = menuData.get(position).getLatitude();
            Location.localLongitude = menuData.get(position).getLongitude();
            Location.city = menuData.get(position).getName();
            Location.country = menuData.get(position).getCountry();

            MainActivity.isFromLocationAdapter = true;
            ((Activity)context).finish();

        }
    }

}
