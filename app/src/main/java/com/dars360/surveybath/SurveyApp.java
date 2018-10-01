package com.dars360.surveybath;

import android.app.Application;
import android.content.Context;

public class SurveyApp extends Application {
    private static Context mContext;

    public Context getSurveyContext() {

        return mContext;
    }

    public static SurveyApp newInstance() {
        return new SurveyApp();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

    }
}
