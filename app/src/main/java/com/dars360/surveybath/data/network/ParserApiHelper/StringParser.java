package com.dars360.surveybath.data.network.ParserApiHelper;


import com.dars360.surveybath.data.network.Interfaces.CallBackString;
import com.dars360.surveybath.data.network.RestClientRetrofit;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.MultipartBody;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StringParser implements Callback<String> {
    CallBackString callBackString;

    public StringParser(CallBackString callBackString) {
        this.callBackString = callBackString;
        // this.baseURL = baseUrl;
        // retrofitInterface = GeoMingleApp.newInstance().newRetrofitRequest();
    }



      public void setRating(RestClientRetrofit.RetrofitInterface retrofitInterface, String url, Integer ratingId) {
           Call<String> jsonArrayCall = retrofitInterface.postArrayRatingData(url, ratingId);
           jsonArrayCall.enqueue(this);

       }

       public void setRatingWithReason(RestClientRetrofit.RetrofitInterface retrofitInterface, String url, Integer ratingId,String optionId, String comment, String mobileNum) {
           Call<String> jsonArrayCall = retrofitInterface.setArrayRatingDataWithOption(url, ratingId, optionId,comment, mobileNum);
           jsonArrayCall.enqueue(this);

       }
    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        callBackString.onSuccessObject(response);
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        callBackString.OnFailObject(t);
    }


}
