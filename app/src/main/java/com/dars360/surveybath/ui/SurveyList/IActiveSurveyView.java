package com.dars360.surveybath.ui.SurveyList;

import com.dars360.surveybath.dataModels.GetActiveSurveyResponse;
import com.dars360.surveybath.dataModels.GetSurveyRatingListResponse;

import java.util.ArrayList;

public interface IActiveSurveyView {
    void successGetSurvey(ArrayList<GetActiveSurveyResponse> SurveyList);

    void successGetSurveyList(ArrayList<GetSurveyRatingListResponse> SurveyRatingList);

    void failedGetSurvey(String messege);
}
