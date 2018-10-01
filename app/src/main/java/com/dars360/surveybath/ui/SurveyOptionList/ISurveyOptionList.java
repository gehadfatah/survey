package com.dars360.surveybath.ui.SurveyOptionList;

import com.dars360.surveybath.dataModels.GetRatingOptionListResponse;

import java.util.ArrayList;

public interface ISurveyOptionList {
    void successOptionList(ArrayList<GetRatingOptionListResponse> RatingOptionList);
    void failedOptionList();
}
