package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Multimedia implements Parcelable {

    @SerializedName("crop_name")
    private String cropName;
    private String rank;
    private String height;
    private String subType;
    private String width;
    private String subtype;
    private String caption;
    private String type;
    private String credit;
    private String url;
    private Legacy legacy;

    protected Multimedia(Parcel in) {
        cropName = in.readString();
        rank = in.readString();
        height = in.readString();
        subType = in.readString();
        width = in.readString();
        subtype = in.readString();
        caption = in.readString();
        type = in.readString();
        credit = in.readString();
        url = in.readString();
        legacy = in.readParcelable(Legacy.class.getClassLoader());
    }

    public static final Creator<Multimedia> CREATOR = new Creator<Multimedia>() {
        @Override
        public Multimedia createFromParcel(Parcel in) {
            return new Multimedia(in);
        }

        @Override
        public Multimedia[] newArray(int size) {
            return new Multimedia[size];
        }
    };

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String crop_name) {
        this.cropName = cropName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Legacy getLegacy() {
        return legacy;
    }

    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(cropName);
        parcel.writeString(rank);
        parcel.writeString(height);
        parcel.writeString(subType);
        parcel.writeString(width);
        parcel.writeString(subtype);
        parcel.writeString(caption);
        parcel.writeString(type);
        parcel.writeString(credit);
        parcel.writeString(url);
        parcel.writeParcelable(legacy, i);
    }
}
