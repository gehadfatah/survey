package com.dars360.surveybath.data.network.Interfaces;

import com.google.gson.JsonObject;

import retrofit2.Response;


//=================================================================
// interface to be implemented by calling class or instance for jsonObject return type
//=================================================================
public interface CallBackJSONObject {

    void onSuccessObject(Response<JsonObject> o);

    void OnFailObject(Throwable o);
}
