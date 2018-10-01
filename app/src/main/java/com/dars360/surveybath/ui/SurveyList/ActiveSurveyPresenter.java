package com.dars360.surveybath.ui.SurveyList;

import android.content.Context;
import android.util.Log;

import com.dars360.surveybath.R;
import com.dars360.surveybath.SurveyApp;
import com.dars360.surveybath.data.network.Interfaces.CallBackJSONArray;
import com.dars360.surveybath.data.network.ParserApiHelper.JSONArrayParser;
import com.dars360.surveybath.data.network.RestClientRetrofit;
import com.dars360.surveybath.dataModels.GetActiveSurveyResponse;
import com.dars360.surveybath.dataModels.GetSurveyRatingListResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ActiveSurveyPresenter implements CallBackJSONArray {
    Context mContext;
    private RestClientRetrofit.RetrofitInterface retrofitInterface;
    IActiveSurveyView iActiveSurveyView;

    public ActiveSurveyPresenter(IActiveSurveyView iActiveSurveyView) {
        super();
        mContext = SurveyApp.newInstance().getSurveyContext();
        retrofitInterface = RestClientRetrofit.getClient(mContext).create(RestClientRetrofit.RetrofitInterface.class);

        this.iActiveSurveyView = iActiveSurveyView;
    }

    public void getSurveyId() {
        JSONArrayParser parser = new JSONArrayParser(this);

        parser.getSurvey(retrofitInterface, mContext.getString(R.string.active_survey_url));

    }

    public void getSurveyRatingList(Integer surveyId) {
        JSONArrayParser parser = new JSONArrayParser(this);

        parser.getSurveyList(retrofitInterface, mContext.getString(R.string.SurveyRatingList_url), surveyId);

    }

    @Override
    public void onSuccessArray(Response<JsonArray> o) {
        if (o.code() == 200) {
            if (o.raw().request().url().toString().equals("http://api.temp.web.darsint.arvixededicated.com/survey/getactivesurvey")) {

                Gson gson = new Gson();
                Type listType = new TypeToken<List<GetActiveSurveyResponse>>() {
                }.getType();
                ArrayList<GetActiveSurveyResponse> surveyList = gson.fromJson(o.body().toString(), listType);

                iActiveSurveyView.successGetSurvey(surveyList);
            } else/* if (o.raw().request().url().toString().contains("http://api.temp.web.darsint.arvixededicated.com/survey/GetSurveyRatingList"))*/ {
                Gson gson = new Gson();
                Type listType = new TypeToken<List<GetSurveyRatingListResponse>>() {
                }.getType();
                ArrayList<GetSurveyRatingListResponse> SurveyRatingListResponse = gson.fromJson(o.body().toString(), listType);

                iActiveSurveyView.successGetSurveyList(SurveyRatingListResponse);
            }
        }
    }

    @Override
    public void OnFailArray(Throwable o) {
        Log.d("d", "OnFailArray: ");
        iActiveSurveyView.failedGetSurvey(o.getMessage());

    }
}
