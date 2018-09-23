package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class SearchResponse implements Parcelable {

    private Response response;
    private String status;
    private String copyright;

    protected SearchResponse(Parcel in) {
        response = in.readParcelable(Response.class.getClassLoader());
        status = in.readString();
        copyright = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(response, flags);
        dest.writeString(status);
        dest.writeString(copyright);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SearchResponse> CREATOR = new Creator<SearchResponse>() {
        @Override
        public SearchResponse createFromParcel(Parcel in) {
            return new SearchResponse(in);
        }

        @Override
        public SearchResponse[] newArray(int size) {
            return new SearchResponse[size];
        }
    };

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
