package com.example.news;

public class News {

    private String mtitle;

    private String mDate;
private String photo;
    private String mUrl;
    public News(String title, String date,String url,String urlToImage)
    {
        mtitle=title;
        mDate=date;
        photo=urlToImage;
        mUrl=url;
    }
    public String gettitle()
    {
        return mtitle;
    }
public String getimage(){return photo;}
    public String getDate()
    {
        return mDate;
    }

    public String getUrl() {
        return mUrl;
    }
}
