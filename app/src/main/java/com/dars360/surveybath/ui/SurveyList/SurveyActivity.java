package com.dars360.surveybath.ui.SurveyList;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dars360.surveybath.R;
import com.dars360.surveybath.SurveyApp;
import com.dars360.surveybath.dataModels.GetActiveSurveyResponse;
import com.dars360.surveybath.dataModels.GetSurveyRatingListResponse;
import com.dars360.surveybath.ui.SurveyOptionList.SurveyOptionActivity;
import com.dars360.surveybath.utils.SurveyConstants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyActivity extends AppCompatActivity implements IActiveSurveyView, View.OnClickListener {
    ActiveSurveyPresenter activeSurveyPresenter;
    @BindView(R.id.firstStatusimg)
    ImageView firstStatusimg;
    @BindView(R.id.firstStatusTv)
    TextView textView1;
    @BindView(R.id.secondStatusimg)
    ImageView secondStatusimg;
    @BindView(R.id.secondStatusTv)
    TextView textView2;
    @BindView(R.id.thirdStatusimg)
    ImageView thirdStatusimg;
    @BindView(R.id.thirdStatusTv)
    TextView textView3;
    Intent intent;
    private ArrayList<GetSurveyRatingListResponse> SurveyRatingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        ButterKnife.bind(this);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }*/
        setListener();
        activeSurveyPresenter = new ActiveSurveyPresenter(this);
        getActiveSurvey();
        intent = new Intent(this, SurveyOptionActivity.class);
    }

    private void setListener() {
        textView1.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
    }

    public void getActiveSurvey() {
        activeSurveyPresenter.getSurveyId();
    }


    @Override
    public void successGetSurvey(ArrayList<GetActiveSurveyResponse> getActiveSurveyResponse) {
        GetActiveSurveyResponse getActiveSurveyResponse1 = getActiveSurveyResponse.get(0);
        getActiveSurveyResponse1.getID();
        getActiveSurveyResponse1.getRatingTitle();
        getActiveSurveyResponse1.getTitle();
        activeSurveyPresenter.getSurveyRatingList(getActiveSurveyResponse1.getID());

    }

    @Override
    public void successGetSurveyList(ArrayList<GetSurveyRatingListResponse> SurveyRatingList) {
        this.SurveyRatingList = SurveyRatingList;
        Glide.with(SurveyApp.newInstance().getSurveyContext())
                .load(SurveyRatingList.get(0).getImageUrl())
                .into(firstStatusimg);
        Glide.with(SurveyApp.newInstance().getSurveyContext())
                .load(SurveyRatingList.get(1).getImageUrl())
                .into(secondStatusimg);
        Glide.with(SurveyApp.newInstance().getSurveyContext())
                .load(SurveyRatingList.get(2).getImageUrl())
                .into(thirdStatusimg);

        textView1.setText(SurveyRatingList.get(0).getTitle());
        textView2.setText(SurveyRatingList.get(1).getTitle());
        textView3.setText(SurveyRatingList.get(2).getTitle());

    }

    @Override
    public void failedGetSurvey(String messege) {
        if (messege != null) {
            if (messege.contains("Failed to connect")||messege.contains("Unable to resolve host"))
                Toast.makeText(SurveyApp.newInstance().getSurveyContext(), getResources().getString(R.string.checkInternet), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.firstStatusTv:
                firstRelClick(v);


                break;
            case R.id.secondStatusTv:
                secondRelClick(v);

                break;
            case R.id.thirdStatusTv:
                thirdRelClick(v);
                break;

        }
    }

    public void thirdRelClick(View view) {
        if (SurveyRatingList != null)
            intent.putExtra(SurveyConstants.surveyRating, SurveyRatingList.get(2));
        navigateTo();
    }

    public void secondRelClick(View view) {
        if (SurveyRatingList != null)

            intent.putExtra(SurveyConstants.surveyRating, SurveyRatingList.get(1));
        navigateTo();
    }

    public void firstRelClick(View view) {
        if (SurveyRatingList != null)

            intent.putExtra(SurveyConstants.surveyRating, SurveyRatingList.get(0));
        navigateTo();
    }

    public void navigateTo() {
        if (SurveyRatingList != null)

            startActivity(intent);
    }
}
