package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response implements Parcelable {

    @SerializedName("docs")
    private ArrayList<Article> articles;

    private Meta meta;

    protected Response(Parcel in) {
        articles = in.createTypedArrayList(Article.CREATOR);
        meta = in.readParcelable(Meta.class.getClassLoader());
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(articles);
        parcel.writeParcelable(meta, i);
    }
}
