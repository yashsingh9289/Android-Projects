package com.example.news;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class newsreport {
     Context context;

    public newsreport(Context context)
    {
        this.context=context;
    }
    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(ArrayList response);
    }
    public void getreport(final VolleyResponseListener volleyresponselistener)
    {// Construct the data source
        ArrayList<News> arrayOfUsers = new ArrayList<News>();
        String url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        String title = "";
                        String date = "";
                        String murl = "";
                        String mphoto="";
                        try {

                            JSONArray Articles = response.getJSONArray("articles");
                            for (int i = 0; i < Articles.length(); i++) {
                                JSONObject Title = Articles.getJSONObject(i);
                                title = Title.getString("title");
                                murl = Title.getString("url");
                                date = Title.getString("publishedAt");
                                mphoto=Title.getString("urlToImage");
                                News getnews = new News(title, date, murl,mphoto);
                                // Toast.makeText(MainActivity.this, title+ "  "+murl, Toast.LENGTH_SHORT).show();
                                arrayOfUsers.add(getnews);
                                volleyresponselistener.onResponse(arrayOfUsers);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        //Toast.makeText(MainActivity.this, "complied successfully", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(context, "Something wrong", Toast.LENGTH_SHORT).show();
                        volleyresponselistener.onError("something wrong");

                    }

                });
        MySingleton.getInstance(context).addToRequestQueue(jsonArrayRequest);
    }
}
