package com.dars360.surveybath.data.network.ParserApiHelper;


import com.dars360.surveybath.data.network.Interfaces.CallBackJSONArray;
import com.dars360.surveybath.data.network.RestClientRetrofit;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayman on 5/23/2017.
 */

public class JSONArrayParser implements Callback<JsonArray> {
    // private final String baseURL;

    // private final String baseURL;
    CallBackJSONArray callBackJSONArray;

    //=================================================================
    // constructor for creating calling object for network instance
    //=================================================================
    public JSONArrayParser(/*String baseUrl,*/ CallBackJSONArray call) {
        this.callBackJSONArray = call;
        // this.baseURL = baseUrl;
    }

    //=================================================================
    // below is all web api methods with return type jsonObject
    //=================================================================

    public void getSurvey(RestClientRetrofit.RetrofitInterface retrofitInterface, String url) {
        Call<JsonArray> jsonArrayCall = retrofitInterface.getArrayData(url);
        jsonArrayCall.enqueue(this);

    }

    public void getSurveyList(RestClientRetrofit.RetrofitInterface retrofitInterface, String url, Integer surveyId) {
        Call<JsonArray> jsonArrayCall = retrofitInterface.getArraySurveyData(url, surveyId);
        jsonArrayCall.enqueue(this);
    }
    public void getRatingList(RestClientRetrofit.RetrofitInterface retrofitInterface, String url, Integer ratingId) {
        Call<JsonArray> jsonArrayCall = retrofitInterface.getArrayRatingData(url, ratingId);
        jsonArrayCall.enqueue(this);
    }

  /*  public void setRating(RestClientRetrofit.RetrofitInterface retrofitInterface, String url, Integer ratingId) {
        Call<JsonArray> jsonArrayCall = retrofitInterface.postArrayRatingData(url, ratingId);
        jsonArrayCall.enqueue(this);

    }

    public void setRatingWithReason(RestClientRetrofit.RetrofitInterface retrofitInterface, String url, Integer ratingId,Integer optionId, String comment, String mobileNum) {
        Call<JsonArray> jsonArrayCall = retrofitInterface.setArrayRatingDataWithOption(url, ratingId, optionId,comment, mobileNum);
        jsonArrayCall.enqueue(this);

    }*/
    //=================================================================
    // DefaultResponse CallBack for success response
    //=================================================================
    @Override
    public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
        callBackJSONArray.onSuccessArray(response);
    }

    //=================================================================
    // DefaultResponse CallBack for error response
    //=================================================================
    @Override
    public void onFailure(Call<JsonArray> call, Throwable t) {
        callBackJSONArray.OnFailArray(t);
    }



}