package com.example.news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    private NewsAdapter mAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       newsreport cxt= new newsreport(MainActivity.this);
       cxt.getreport(new newsreport.VolleyResponseListener() {
           @Override
           public void onError(String message) {
               Toast.makeText(MainActivity.this, "something wrong", Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onResponse(ArrayList response) {
          mAdapter = new NewsAdapter(MainActivity.this, response);
               // Attach the adapter to a ListView
                listView = (ListView) findViewById(R.id.list);
               listView.setAdapter(mAdapter);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                       // Find the current earthquake that was clicked on
                       News currentEarthquake = mAdapter.getItem(position);

                       // Convert the String URL into a URI object (to pass into the Intent constructor)
                       Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                       // Create a new intent to view the earthquake URI
                       Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                       // Send the intent to launch a new activity
                       startActivity(websiteIntent);
                   }
               });
           }
       });
    }
}