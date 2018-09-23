package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Meta implements Parcelable {

    private String time;
    private String hits;
    private String offset;

    protected Meta(Parcel in) {
        time = in.readString();
        hits = in.readString();
        offset = in.readString();
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(time);
        parcel.writeString(hits);
        parcel.writeString(offset);
    }
}
