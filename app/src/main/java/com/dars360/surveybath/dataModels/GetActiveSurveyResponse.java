package com.dars360.surveybath.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetActiveSurveyResponse  implements Parcelable
{

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("RatingTitle")
    @Expose
    private String ratingTitle;
    public final static Parcelable.Creator<GetActiveSurveyResponse> CREATOR = new Parcelable.Creator<GetActiveSurveyResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetActiveSurveyResponse createFromParcel(Parcel in) {
            return new GetActiveSurveyResponse(in);
        }

        public GetActiveSurveyResponse[] newArray(int size) {
            return (new GetActiveSurveyResponse[size]);
        }

    }
            ;

    protected GetActiveSurveyResponse(Parcel in) {
        this.iD = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.ratingTitle = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetActiveSurveyResponse() {
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRatingTitle() {
        return ratingTitle;
    }

    public void setRatingTitle(String ratingTitle) {
        this.ratingTitle = ratingTitle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(title);
        dest.writeValue(ratingTitle);
    }

    public int describeContents() {
        return 0;
    }

}