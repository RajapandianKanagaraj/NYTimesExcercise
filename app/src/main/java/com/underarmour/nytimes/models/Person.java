package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

    private String rank;
    private String organization;
    private String title;
    private String middlename;
    private String role;
    private String lastname;
    private String qualifier;
    private String firstname;

    protected Person(Parcel in) {
        rank = in.readString();
        organization = in.readString();
        title = in.readString();
        middlename = in.readString();
        role = in.readString();
        lastname = in.readString();
        qualifier = in.readString();
        firstname = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rank);
        parcel.writeString(organization);
        parcel.writeString(title);
        parcel.writeString(middlename);
        parcel.writeString(role);
        parcel.writeString(lastname);
        parcel.writeString(qualifier);
        parcel.writeString(firstname);
    }
}
