package com.example.electricvehicle.Connect;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface Interface {

    @FormUrlEncoded
    @POST("adduser.php") //php file
    Call<Response> adduser(@Field("name")String name, @Field("email")String email, @Field("password")String password, @Field("phonenumber")String phonenumber);

    @FormUrlEncoded
    @POST("checkuser.php") //php file
    Call<Response>checkuser(@Field("email")String email,@Field("password")String password);



}
