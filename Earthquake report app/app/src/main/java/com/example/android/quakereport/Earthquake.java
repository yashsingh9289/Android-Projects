package com.example.android.quakereport;

public class Earthquake {
    private double mMagnitude;
    private String mLocation;
    private String mDate;
    private  String mMilliseconds;
    private String mUrl;
    public Earthquake(double magnitude, String location, String date,String time,String url)
    {
        mMagnitude=magnitude;
        mLocation= location;
        mDate=date;
        mMilliseconds=time;
        mUrl=url;
    }
    public double getMagnitude()
    {
        return mMagnitude;
    }
    public String getLocation()
    {
        return mLocation;
    }
    public String getDate()
    {
        return mDate;
    }
    public String gettime()
    {
        return mMilliseconds;
    }
    public String getUrl() {
        return mUrl;
    }

}
