package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Keywords implements Parcelable {

    private String rank;
    private String name;
    private String value;
    private String major;

    protected Keywords(Parcel in) {
        rank = in.readString();
        name = in.readString();
        value = in.readString();
        major = in.readString();
    }

    public static final Creator<Keywords> CREATOR = new Creator<Keywords>() {
        @Override
        public Keywords createFromParcel(Parcel in) {
            return new Keywords(in);
        }

        @Override
        public Keywords[] newArray(int size) {
            return new Keywords[size];
        }
    };

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rank);
        parcel.writeString(name);
        parcel.writeString(value);
        parcel.writeString(major);
    }
}
