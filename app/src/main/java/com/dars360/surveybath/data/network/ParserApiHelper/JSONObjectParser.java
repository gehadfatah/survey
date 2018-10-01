package com.dars360.surveybath.data.network.ParserApiHelper;

import android.text.LoginFilter;
import android.util.Log;


import com.dars360.surveybath.data.network.Interfaces.CallBackJSONObject;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Observable;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//=================================================================
// this class contains all functions with return type JsonObject
//this callback for asynchronized requests
//=================================================================
public class JSONObjectParser implements Callback<JsonObject> {

    //private final String baseURL;
    CallBackJSONObject callBackJSONObject;
    // RestClientRetrofit.RetrofitInterface retrofitInterface;

    //=================================================================
    // constructor for creating calling object for network instance
    //=================================================================
    public JSONObjectParser(/*String baseUrl,*/ CallBackJSONObject call) {
        this.callBackJSONObject = call;
        // this.baseURL = baseUrl;
        // retrofitInterface = GeoMingleApp.newInstance().newRetrofitRequest();
    }



    //=================================================================
    // DefaultResponse CallBack for success response
    //=================================================================
    @Override
    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
        callBackJSONObject.onSuccessObject(response);

    }

    //=================================================================
    // DefaultResponse CallBack for error response
    //=================================================================
    @Override
    public void onFailure(Call<JsonObject> call, Throwable t) {
        callBackJSONObject.OnFailObject(t);
    }


}
