package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Byline implements Parcelable {

    private String organization;
    private ArrayList<Person> person;
    private String original;

    protected Byline(Parcel in) {
        organization = in.readString();
        original = in.readString();
    }

    public static final Creator<Byline> CREATOR = new Creator<Byline>() {
        @Override
        public Byline createFromParcel(Parcel in) {
            return new Byline(in);
        }

        @Override
        public Byline[] newArray(int size) {
            return new Byline[size];
        }
    };

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public ArrayList<Person> getPerson() {
        return person;
    }

    public void setPerson(ArrayList<Person> person) {
        this.person = person;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(organization);
        parcel.writeString(original);
    }
}
