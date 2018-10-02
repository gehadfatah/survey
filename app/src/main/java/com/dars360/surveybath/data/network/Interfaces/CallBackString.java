package com.dars360.surveybath.data.network.Interfaces;

import retrofit2.Response;

public interface CallBackString {
    void onSuccessObject(Response<String> o);

    void OnFailObject(Throwable o);
}
