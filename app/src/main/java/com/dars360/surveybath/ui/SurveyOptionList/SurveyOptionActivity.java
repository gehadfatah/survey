package com.dars360.surveybath.ui.SurveyOptionList;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dars360.surveybath.R;
import com.dars360.surveybath.dataModels.GetRatingOptionListResponse;
import com.dars360.surveybath.dataModels.GetSurveyRatingListResponse;
import com.dars360.surveybath.utils.SurveyConstants;

import java.util.ArrayList;
import java.util.Objects;

public class SurveyOptionActivity extends AppCompatActivity implements ISurveyOptionList {
    private static final String TAG = "SurveyOptionActivity";
    SurveyOptionListPresenter surveyOptionListPresenter;
    GetSurveyRatingListResponse statesUser;
    RadioGroup rgp;
    EditText otherEd;
    TextView tvQuestion;
    Button sendButton;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_option);
        statesUser = Objects.requireNonNull(getIntent().getExtras()).getParcelable(SurveyConstants.surveyRating);
        rgp = findViewById(R.id.groupView);
        sendButton = findViewById(R.id.sendButton);
        otherEd = findViewById(R.id.otherEd);
        tvQuestion = findViewById(R.id.tvQuestion);
        surveyOptionListPresenter = new SurveyOptionListPresenter(this);

        if (statesUser != null && statesUser.getHasOptions()) {
            rgp.setVisibility(View.VISIBLE);

            surveyOptionListPresenter.getSurveyRatingList(statesUser.getID());

            rgp.setOrientation(LinearLayout.VERTICAL);
            otherEd.setVisibility(View.INVISIBLE);
            tvQuestion.setText("لماذا الخدمة غير جيدة؟");

        } else if (statesUser != null) {
            rgp.setVisibility(View.INVISIBLE);
            tvQuestion.setText("شكرا لرايك");
            sendButton.setVisibility(View.INVISIBLE);
            surveyOptionListPresenter.sendSurveyRating(statesUser.getID());
        }
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int radioSelected = rgp.getCheckedRadioButtonId();
                if (radioSelected == 20) {
                    //other

                }else {

                }
            }
        });

    }

    @Override
    public void successOptionList(ArrayList<GetRatingOptionListResponse> RatingOptionList) {
        final int buttons = RatingOptionList.size();
        for (int i = 0; i < buttons + 1; i++) {
            final CompoundButton rbn = new RadioButton(this);

            if (i == buttons) {
                rbn.setText("اخري");
                rbn.setId(20);

            } else {
                rbn.setId(RatingOptionList.get(i).getID());
                rbn.setText(RatingOptionList.get(i).getTitle());
            }
            //rbn.setMaxLines(1);


            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            final int finalI = i;
            rbn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (finalI == buttons) {
                        otherEd.setVisibility(View.VISIBLE);
                        otherEd.setHint("اكتب السبب");
                        //  rgp.clearCheck();
                        //rbn.setChecked(true);
                    } else {
                        otherEd.setVisibility(View.INVISIBLE);

                    }
                    rgp.clearCheck();
                    rbn.setChecked(true);
                    sendButton.setVisibility(View.VISIBLE);

                }
            });
            rbn.setLayoutParams(params);
            rgp.addView(rbn);
        /*    rbn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d(TAG, "onCheckedChanged  " + buttonView.getId());
                    rgp.clearCheck();
                    if (isChecked) {
                        rgp.check(buttonView.getId());
                    }
                }
            });*/
        }
        /*final CompoundButton rbn = new RadioButton(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
        rbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherEd.setVisibility(View.VISIBLE);
                otherEd.setHint("اكتب السبب");
                rgp.clearCheck();
                rbn.setChecked(true);
            }
        });
        rbn.setLayoutParams(params);
     *//*   rbn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged  " + buttonView.getId());
                rgp.clearCheck();
                if (isChecked) {
                    rgp.check(buttonView.getId());
                }
            }
        });*//*
        rgp.addView(rbn);*/

    }

    @Override
    public void failedOptionList() {
        Log.d(TAG, "failedOptionList: ");

    }

    @Override
    public void successPostRating() {
        Log.d(TAG, "successPostRating: ");
    }

    @Override
    public void failedPostRating() {
        Log.d(TAG, "failedPostRating: ");
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
