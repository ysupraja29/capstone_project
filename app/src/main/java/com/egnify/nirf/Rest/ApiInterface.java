package com.egnify.nirf.Rest;


import com.egnify.nirf.MainScreen.college_response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface ApiInterface {


    @GET
    Call<college_response> get_colleges(@Url String url);


}
