package com.dars360.surveybath.data.network;

import android.content.Context;


import com.dars360.surveybath.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;


import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by godaa on 29/04/2017.
 */
//retrieve data using retrofit
public class RestClientRetrofit {

    static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().
                addInterceptor(httpLoggingInterceptor)
                .build();
     /*   CachingSystem system = new BasicCaching(
                new File(context.getCacheDir(), "myDir"), // caching dir
                1024 * 1024, // max disk size
                50 // max memory entries
        );*/

        //cached happen with Requested with get data only


        retrofit = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())


                //you can add here any url as you like
                .baseUrl(context.getResources().getString(R.string.base_url))

                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public interface RetrofitInterface {

        @Headers({
                "content-type: application/json"})
        @GET
        Call<JsonArray> getArrayData(@Url String url);

        @Headers({
                "content-type: application/json"})
        @GET
        Call<JsonArray> getArraySurveyData(@Url String url, @Query("survey_ID") Integer surveyId);

        @Headers({
                "content-type: application/json"})
        @GET
        Call<JsonArray> getArrayRatingData(@Url String url, @Query("Rating_ID") Integer Rating_ID);

        @Headers({
                "content-type: application/json"})
        @POST
        Call<String> postArrayRatingData(@Url String url, @Query("Rating_ID") Integer Rating_ID);

        @Headers({
                "content-type: application/json"})
        @POST
        Call<String> setArrayRatingDataWithOption(@Url String url, @Query("Rating_ID") Integer Rating_ID, @Query("Option_ID") String Option_ID, @Query("comment") String comment, @Query("Mobile_No") String Mobile_No);

    }
}

