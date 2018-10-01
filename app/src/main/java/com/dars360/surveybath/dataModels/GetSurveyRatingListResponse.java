package com.dars360.surveybath.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetSurveyRatingListResponse  implements Parcelable
{

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("HasOptions")
    @Expose
    private Boolean hasOptions;
    @SerializedName("ImageUrl")
    @Expose
    private String imageUrl;
    public final static Parcelable.Creator<GetSurveyRatingListResponse> CREATOR = new Parcelable.Creator<GetSurveyRatingListResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetSurveyRatingListResponse createFromParcel(Parcel in) {
            return new GetSurveyRatingListResponse(in);
        }

        public GetSurveyRatingListResponse[] newArray(int size) {
            return (new GetSurveyRatingListResponse[size]);
        }

    }
            ;

    protected GetSurveyRatingListResponse(Parcel in) {
        this.iD = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.hasOptions = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.imageUrl = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetSurveyRatingListResponse() {
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

    public Boolean getHasOptions() {
        return hasOptions;
    }

    public void setHasOptions(Boolean hasOptions) {
        this.hasOptions = hasOptions;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(title);
        dest.writeValue(hasOptions);
        dest.writeValue(imageUrl);
    }

    public int describeContents() {
        return 0;
    }

}