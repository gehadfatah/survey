package com.dars360.surveybath.ui.SurveyOptionList;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dars360.surveybath.R;
import com.dars360.surveybath.dataModels.GetRatingOptionListResponse;
import com.dars360.surveybath.dataModels.GetSurveyRatingListResponse;
import com.dars360.surveybath.utils.SurveyConstants;

import java.util.ArrayList;
import java.util.Objects;

public class SurveyOptionActivity extends AppCompatActivity implements ISurveyOptionList {
    SurveyOptionListPresenter surveyOptionListPresenter;
    GetSurveyRatingListResponse statesUser;
    RadioGroup rgp;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_option);
        statesUser = Objects.requireNonNull(getIntent().getExtras()).getParcelable(SurveyConstants.surveyRating);
        if (statesUser != null && statesUser.getHasOptions()) {
            surveyOptionListPresenter = new SurveyOptionListPresenter(this);

            surveyOptionListPresenter.getSurveyRatingList(statesUser.getID());
        }
        rgp = (RadioGroup) findViewById(R.id.groupView);

    }

    @Override
    public void successOptionList(ArrayList<GetRatingOptionListResponse> RatingOptionList) {
        rgp.setOrientation(LinearLayout.HORIZONTAL);
        int buttons = RatingOptionList.size();
        for (int i = 1; i <= buttons; i++) {
            RadioButton rbn = new RadioButton(this);
            rbn.setId(1 + 1000);
            rbn.setText(RatingOptionList.get(i).getTitle());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
            rbn.setLayoutParams(params);
            //rgp.addView(rbn);
        }
    }

    @Override
    public void failedOptionList() {


    }

    public void radioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        // This check which radio button was clicked
        switch (view.getId()) {
           /* case R.id.radioButton1:
                if (checked)
                    //Do something when radio button is clicked
                    Toast.makeText(getApplicationContext(), "It seems like you feeal RelativeLayout easy", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton2:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "It seems like you feel LinearLayout easy", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton3:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "It seems like you feel FrameLayout easy", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton4:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "It seems like you feel TablleLayout easy", Toast.LENGTH_SHORT).show();
                break;

            case R.id.radioButton5:
                //Do something when radio button is clicked
                Toast.makeText(getApplicationContext(), "It seems like you feel GridLayout easy", Toast.LENGTH_SHORT).show();
                break;*/
        }
    }
}
