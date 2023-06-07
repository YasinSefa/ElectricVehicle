package com.example.electricvehicle.Connect;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Connection {
    private static final String adduser="http://192.168.1.110/adduser.php/";

    private static final String checkuser="http://192.168.1.110/checkuser.php/";

    private static Retrofit retrofit=null;
    private static Retrofit retrofit2=null;

    //public method that returns connection

    public static  Retrofit getConnectionAdduser()
    {
        if(retrofit==null)
        {
            retrofit=new Retrofit.Builder().baseUrl(adduser).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }


    public static  Retrofit getConnectionCheckuser()
    {
        if(retrofit2==null)
        {
            retrofit2=new Retrofit.Builder().baseUrl(checkuser).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit2;
    }
}