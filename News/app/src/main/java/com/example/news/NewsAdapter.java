package com.example.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import com.squareup.picasso.Picasso;
public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, ArrayList<News> users) {
        super(context, 0, users);
    }

    private static final String LOCATION_SEPARATOR = "T";
    String Date;
    StringBuffer Time;
    @NonNull
        @Override
        public View getView ( int position, @Nullable View convertView, @NonNull ViewGroup parent){

            View ListItemView = convertView;
            if (ListItemView == null) {
                ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
            }
            News currentEarthquake = getItem(position);
        String originalLocation = currentEarthquake.getDate();

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            Date = parts[0];
            String temp=parts[1];
            //creating a constructor of StringBuffer class
            StringBuffer sb= new StringBuffer(temp);
            sb.deleteCharAt(sb.length()-1);

            Time=sb;
        }
            TextView titleview = (TextView) ListItemView.findViewById(R.id.title);
            // Display the magnitude of the current earthquake in that TextView
            titleview.setText(currentEarthquake.gettitle());
            TextView dateview = (TextView) ListItemView.findViewById(R.id.date);
            dateview.setText(Date);
            TextView timeview=(TextView) ListItemView.findViewById(R.id.time);
            timeview.setText(Time);
        ImageView img=(ImageView)ListItemView.findViewById(R.id.magnitude);
        if(currentEarthquake.getimage().isEmpty())
        {Picasso.with(getContext())
                .load("https://i.imgur.com/DvpvklR.png")
                .into(img);

        }
        else {
            Picasso.with(getContext())
                    .load(currentEarthquake.getimage())
                    .into(img);
        }
            return ListItemView;
        }

}
