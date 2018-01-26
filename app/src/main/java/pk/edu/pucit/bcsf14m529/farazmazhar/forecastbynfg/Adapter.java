package pk.edu.pucit.bcsf14m529.farazmazhar.forecastbynfg;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by hassan on 24-Jan-18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.ViewChildWeather> {
    private ArrayList<Weather> menuData;
    private LayoutInflater layoutInflater;
    Context context;

    private int position;

    public  Adapter(Context context, ArrayList<Weather> menuData)
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
    public ViewChildWeather onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.main_list_layout, parent, false);
        ViewChildWeather viewHolder = new ViewChildWeather(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewChildWeather holder, int i) {

        final ViewChildWeather localHolder=holder;

        holder.cityName.setText(Location.city);
        holder.counteryName.setText(Location.country);
        holder.temperature.setText(menuData.get(i).getCurrent_temperature());
        holder.weatherStatus.setText(menuData.get(i).getWeather());
        holder.highLowTemp.setText(menuData.get(i).getLow_temperature() + " / " + menuData.get(i).getHigh_temperature());
        holder.visibility.setText(menuData.get(i).getVisibility()+ " meters");
        holder.windSpeed.setText(menuData.get(i).getWind() + " m/s");
        holder.Humidity.setText(menuData.get(i).getHumidity() + " % ");

        if(menuData.get(i).getWeather().trim().toLowerCase().equals("fog") || menuData.get(i).getWeather().trim().toLowerCase().equals("smoke") || menuData.get(i).getWeather().trim().toLowerCase().equals("mist"))
        {
            holder.background.setImageResource(R.drawable.fog);
           // holder.background.setBackgroundResource(R.drawable.fog);
        }
        else if(menuData.get(i).getWeather().trim().toLowerCase().equals("rain") || menuData.get(i).getWeather().toLowerCase().trim().equals("drizzle") || menuData.get(i).getWeather().toLowerCase().trim().equals("thunderstorm"))
        {
            holder.background.setImageResource(R.drawable.rain);
            //holder.background.setBackgroundResource(R.drawable.rain);
        }
        else if(menuData.get(i).getWeather().toLowerCase().trim().equals("few clouds") || menuData.get(i).getWeather().toLowerCase().trim().equals("scattered clouds") || menuData.get(i).getWeather().toLowerCase().trim().equals("broken clouds") )
        {
            holder.background.setImageResource(R.drawable.cloudy);
            //holder.background.setBackgroundResource(R.drawable.cloudy);
        }
        else
        {
            holder.background.setImageResource(R.drawable.sunny_jpg);
            //holder.background.setBackgroundResource(R.drawable.sunny_jpg);
        }

    }



    public void updateRecord(ArrayList<Weather> data)
    {
        this.menuData = data;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return menuData.size();
    }

    public class ViewChildWeather extends RecyclerView.ViewHolder {

        TextView cityName;
        TextView counteryName;
        TextView temperature;
        TextView weatherStatus;
        TextView highLowTemp;
        TextView visibility;
        TextView windSpeed;
        TextView Humidity;
        ImageView background;


        private ViewChildWeather(View itemView)
        {
            super(itemView);

            cityName = itemView.findViewById(R.id.location_City_list_row_layout_xml);
            counteryName = itemView.findViewById(R.id.location_country_list_row_xml);
            temperature = itemView.findViewById(R.id.temperature_textView_main_xml);
            weatherStatus = itemView.findViewById(R.id.weatherStatus_textView_main_Xml);
            highLowTemp = itemView.findViewById(R.id.highLow_textView_list_row_xml);
            visibility = itemView.findViewById(R.id.visibility_textView_list_row_layout);
            windSpeed = itemView.findViewById(R.id.wind_textView_list_row_layout);
            Humidity = itemView.findViewById(R.id.humidity_textView_list_row_layout);
            background = itemView.findViewById(R.id.imageView_background);

        }

    }
}
