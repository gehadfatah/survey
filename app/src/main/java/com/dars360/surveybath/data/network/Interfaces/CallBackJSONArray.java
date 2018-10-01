package com.dars360.surveybath.data.network.Interfaces;

import com.google.gson.JsonArray;

import retrofit2.Response;

/**
 * Created by aayman on 5/23/2017.
 */

public interface CallBackJSONArray {
    void onSuccessArray(Response<JsonArray> o);

    void OnFailArray(Throwable o);
}
