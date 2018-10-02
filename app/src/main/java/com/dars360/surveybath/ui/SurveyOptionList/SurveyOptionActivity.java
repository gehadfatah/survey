package com.dars360.surveybath.ui.SurveyOptionList;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
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
import com.dars360.surveybath.utils.DeviceDimensionsUtils;
import com.dars360.surveybath.utils.SurveyConstants;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SurveyOptionActivity extends AppCompatActivity implements ISurveyOptionList {
    private static final String TAG = "SurveyOptionActivity";
    SurveyOptionListPresenter surveyOptionListPresenter;
    GetSurveyRatingListResponse statesUser;
    RadioGroup rgp;
    EditText otherEd;
    EditText otherEdNum;
    TextView tvQuestion;
    Button sendButton;
    @BindView(R.id.dummy_id)
    LinearLayout dummy;
    @BindView(R.id.finishText)
    TextView finishText;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_option);
        ButterKnife.bind(this);
        statesUser = Objects.requireNonNull(getIntent().getExtras()).getParcelable(SurveyConstants.surveyRating);
        rgp = findViewById(R.id.groupView);
        sendButton = findViewById(R.id.sendButton);
        otherEd = findViewById(R.id.otherEd);
        otherEdNum = findViewById(R.id.otherEdNum);
        tvQuestion = findViewById(R.id.tvQuestion);
        surveyOptionListPresenter = new SurveyOptionListPresenter(this);
        //setMarginTop(tvQuestion, 0);
        dummy.requestFocus();

        if (statesUser != null && statesUser.getHasOptions()) {
            rgp.setVisibility(View.VISIBLE);

            surveyOptionListPresenter.getSurveyRatingList(statesUser.getID());

            rgp.setOrientation(LinearLayout.VERTICAL);
            // otherEd.setVisibility(View.INVISIBLE);
            //  otherEdNum.setVisibility(View.INVISIBLE);
            sendButton.setVisibility(View.VISIBLE);
            otherEd.setVisibility(View.VISIBLE);

            otherEdNum.setVisibility(View.VISIBLE);
            tvQuestion.setText("لماذا الخدمة غير جيدة؟");

        } else if (statesUser != null) {
            rgp.setVisibility(View.INVISIBLE);
            tvQuestion.setText("شكرا لرايك");
            // setMarginTop(tvQuestion, DeviceDimensionsUtils.getDisplayHeight(this) / 2);
            //setGravity(tvQuestion);
            finishText.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.INVISIBLE);
            otherEd.setVisibility(View.INVISIBLE);
            tvQuestion.setVisibility(View.INVISIBLE);

            otherEdNum.setVisibility(View.INVISIBLE);

            surveyOptionListPresenter.sendSurveyRating(statesUser.getID());
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String radioSelected = String.valueOf(rgp.getCheckedRadioButtonId());
                if (radioSelected.equals("-1")) {
                    radioSelected = "";
                }
                surveyOptionListPresenter.sendSurveyRatingWithReason(statesUser.getID(), radioSelected, otherEd.getText().toString(), otherEdNum.getText().toString());
                rgp.setVisibility(View.INVISIBLE);
                tvQuestion.setText("شكرا لرايك");
                finishText.setVisibility(View.VISIBLE);
                tvQuestion.setVisibility(View.INVISIBLE);
                //setMarginTop(tvQuestion, DeviceDimensionsUtils.getDisplayHeight(SurveyOptionActivity.this) / 2);
                //setGravity(tvQuestion);
                // sendButton.setVisibility(View.INVISIBLE);
                sendButton.setVisibility(View.INVISIBLE);
                otherEd.setVisibility(View.INVISIBLE);

                otherEdNum.setVisibility(View.INVISIBLE);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });

    }

    public void setMarginTop(View v, int top) {
        ViewGroup.MarginLayoutParams params =
                (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.setMargins(params.leftMargin, top,
                params.rightMargin, params.bottomMargin);
        v.setLayoutParams(params);


    }

    public void setGravity(View v) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.FILL_PARENT);
        params.gravity = Gravity.CENTER;
        v.setLayoutParams(params);

    }

    @SuppressLint("ResourceType")
    @Override
    public void successOptionList(ArrayList<GetRatingOptionListResponse> RatingOptionList) {
        final int buttons = RatingOptionList.size();
        for (int i = 0; i < buttons; i++) {
            final CompoundButton rbn = new RadioButton(this);

           /* if (i == buttons) {
                rbn.setText("اخري");
                rbn.setId(20);

            } else {
                rbn.setId(RatingOptionList.get(i).getID());
                rbn.setText(RatingOptionList.get(i).getTitle());
            }*/
            rbn.setId(RatingOptionList.get(i).getID());
            rbn.setText(RatingOptionList.get(i).getTitle());
            //rbn.setMaxLines(1);


            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 3f);
            final int finalI = i;
            rbn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


              /*      if (finalI == buttons) {
                        otherEd.setVisibility(View.VISIBLE);
                        otherEdNum.setVisibility(View.VISIBLE);
                        // otherEd.setHint("اكتب السبب");
                        //  rgp.clearCheck();
                        //rbn.setChecked(true);
                    } else {
                        otherEd.setVisibility(View.INVISIBLE);
                        otherEdNum.setVisibility(View.INVISIBLE);

                    }
                    rgp.clearCheck();
                    rbn.setChecked(true);*/
                    // sendButton.setVisibility(View.VISIBLE);

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
