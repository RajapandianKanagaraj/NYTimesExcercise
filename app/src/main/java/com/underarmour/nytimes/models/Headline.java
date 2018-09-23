package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Headline implements Parcelable {

    private String sub;
    private String name;
    private String seo;
    @SerializedName("print_headline")
    private String printHeadline;
    @SerializedName("contentKicker")
    private String contentKicker;
    private String kicker;
    private String main;

    public Headline() {

    }

    protected Headline(Parcel in) {
        sub = in.readString();
        name = in.readString();
        seo = in.readString();
        printHeadline = in.readString();
        contentKicker = in.readString();
        kicker = in.readString();
        main = in.readString();
    }

    public static final Creator<Headline> CREATOR = new Creator<Headline>() {
        @Override
        public Headline createFromParcel(Parcel in) {
            return new Headline(in);
        }

        @Override
        public Headline[] newArray(int size) {
            return new Headline[size];
        }
    };

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeo() {
        return seo;
    }

    public void setSeo(String seo) {
        this.seo = seo;
    }

    public String getPrintHeadline() {
        return printHeadline;
    }

    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    public String getContentKicker() {
        return contentKicker;
    }

    public void setContentKicker(String contentKicker) {
        this.contentKicker = contentKicker;
    }

    public String getKicker() {
        return kicker;
    }

    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(sub);
        parcel.writeString(name);
        parcel.writeString(seo);
        parcel.writeString(printHeadline);
        parcel.writeString(contentKicker);
        parcel.writeString(kicker);
        parcel.writeString(main);
    }
}
