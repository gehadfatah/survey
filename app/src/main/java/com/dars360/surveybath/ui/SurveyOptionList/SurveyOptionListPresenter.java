package com.dars360.surveybath.ui.SurveyOptionList;

import android.content.Context;
import android.util.Log;

import com.dars360.surveybath.R;
import com.dars360.surveybath.SurveyApp;
import com.dars360.surveybath.data.network.Interfaces.CallBackJSONArray;
import com.dars360.surveybath.data.network.ParserApiHelper.JSONArrayParser;
import com.dars360.surveybath.data.network.RestClientRetrofit;
import com.dars360.surveybath.dataModels.GetActiveSurveyResponse;
import com.dars360.surveybath.dataModels.GetRatingOptionListResponse;
import com.dars360.surveybath.dataModels.GetSurveyRatingListResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class SurveyOptionListPresenter implements CallBackJSONArray {
    Context mContext;
    private RestClientRetrofit.RetrofitInterface retrofitInterface;
    ISurveyOptionList ISurveyOptionList;

    public SurveyOptionListPresenter(ISurveyOptionList ISurveyOptionList) {
        super();
        mContext = SurveyApp.newInstance().getSurveyContext();
        retrofitInterface = RestClientRetrofit.getClient(mContext).create(RestClientRetrofit.RetrofitInterface.class);

        this.ISurveyOptionList = ISurveyOptionList;
    }

    public void getSurveyRatingList(Integer ratingId) {
        JSONArrayParser parser = new JSONArrayParser(this);

        parser.getRatingList(retrofitInterface, mContext.getString(R.string.RatingOptionList_url), ratingId);

    }

    public void sendSurveyRating(Integer ratingId) {
        JSONArrayParser parser = new JSONArrayParser(this);

        parser.setRating(retrofitInterface, mContext.getString(R.string.setRating), ratingId);

    }

    @Override
    public void onSuccessArray(Response<JsonArray> o) {
        if (o.code() == 200) {
            if (o.raw().request().url().toString().contains("http://api.temp.web.darsint.arvixededicated.com/survey/GetRatingOptionList")) {

                Gson gson = new Gson();
                Type listType = new TypeToken<List<GetRatingOptionListResponse>>() {
                }.getType();
                ArrayList<GetRatingOptionListResponse> RatingOptionList = gson.fromJson(o.body().toString(), listType);

                ISurveyOptionList.successOptionList(RatingOptionList);
            } else if (o.raw().request().url().toString().contains("http://api.temp.web.darsint.arvixededicated.com/survey/LogEntry")) {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<GetRatingOptionListResponse>>() {
                }.getType();
                // ArrayList<GetRatingOptionListResponse> RatingOptionList = gson.fromJson(o.body().toString(), listType);

                ISurveyOptionList.successPostRating();
            }
        }
    }

    @Override
    public void OnFailArray(Throwable o) {
        Log.d("d", "OnFailArray: ");

    }
}
