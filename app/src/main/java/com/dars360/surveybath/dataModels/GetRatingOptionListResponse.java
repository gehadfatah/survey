package com.dars360.surveybath.dataModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetRatingOptionListResponse implements Parcelable
{

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Title")
    @Expose
    private String title;
    public final static Parcelable.Creator<GetRatingOptionListResponse> CREATOR = new Parcelable.Creator<GetRatingOptionListResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public GetRatingOptionListResponse createFromParcel(Parcel in) {
            return new GetRatingOptionListResponse(in);
        }

        public GetRatingOptionListResponse[] newArray(int size) {
            return (new GetRatingOptionListResponse[size]);
        }

    }
            ;

    protected GetRatingOptionListResponse(Parcel in) {
        this.iD = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
    }

    public GetRatingOptionListResponse() {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iD);
        dest.writeValue(title);
    }

    public int describeContents() {
        return 0;
    }

}