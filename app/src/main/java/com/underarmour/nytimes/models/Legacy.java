package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Legacy implements Parcelable {

    private String xlargeheight;
    private String xlargewidth;
    private String xlarge;

    protected Legacy(Parcel in) {
        xlargeheight = in.readString();
        xlargewidth = in.readString();
        xlarge = in.readString();
    }

    public static final Creator<Legacy> CREATOR = new Creator<Legacy>() {
        @Override
        public Legacy createFromParcel(Parcel in) {
            return new Legacy(in);
        }

        @Override
        public Legacy[] newArray(int size) {
            return new Legacy[size];
        }
    };

    public String getXlargeheight() {
        return xlargeheight;
    }

    public void setXlargeheight(String xlargeheight) {
        this.xlargeheight = xlargeheight;
    }

    public String getXlargewidth() {
        return xlargewidth;
    }

    public void setXlargewidth(String xlargewidth) {
        this.xlargewidth = xlargewidth;
    }

    public String getXlarge() {
        return xlarge;
    }

    public void setXlarge(String xlarge) {
        this.xlarge = xlarge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(xlargeheight);
        parcel.writeString(xlargewidth);
        parcel.writeString(xlarge);
    }
}
