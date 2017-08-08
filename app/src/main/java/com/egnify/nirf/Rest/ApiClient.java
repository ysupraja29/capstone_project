package com.egnify.nirf.Rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://egnify.com/vsb/api/";
   public static final String BASE_URL2 = "http://124.124.124.60/mobile/";
    public static final String BASE_URL3 = "http://vignan.egnify.com/api/";
    //
    //
     //public static final String BASE_URL2 = "http://egnify.com/jans_exp/srichai/";
 //   private static Retrofit retrofit = null;

public static Retrofit getClient3()
{
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    return retrofit;
}
    public static Retrofit getClient()
    {

        Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        return retrofit;
    }
    public static Retrofit getClient2()
    {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
