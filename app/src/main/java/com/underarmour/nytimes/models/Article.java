package com.underarmour.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Article implements Parcelable {

    private Headline headline;
    private ArrayList<Keywords> keywords;

    @SerializedName("print_page")
    private String printPage;

    @SerializedName("pub_date")
    private String pubDate;

    @SerializedName("web_url")
    private String webUrl;

    private String score;

    @SerializedName("news_desk")
    private String newsDesk;

    private String snippet;

    @SerializedName("type_of_material")
    private String typeOfMaterial;

    private Blog blog;
    private String uri;

    @SerializedName("document_type")
    private String documentType;

    @SerializedName("multimedia")
    private ArrayList<Multimedia> multimedia;
    private Byline byline;

    @SerializedName("_id")
    private String id;
    private String source;

    @SerializedName("word_count")
    private String wordCount;

    public Article() {

    }

    protected Article(Parcel in) {
        printPage = in.readString();
        pubDate = in.readString();
        webUrl = in.readString();
        score = in.readString();
        newsDesk = in.readString();
        snippet = in.readString();
        typeOfMaterial = in.readString();
        uri = in.readString();
        documentType = in.readString();
        id = in.readString();
        source = in.readString();
        wordCount = in.readString();
        headline = in.readParcelable(Legacy.class.getClassLoader());
        byline = in.readParcelable(Byline.class.getClassLoader());
        blog = in.readParcelable(Blog.class.getClassLoader());
        multimedia = new ArrayList<>();
        in.readTypedList(multimedia, Multimedia.CREATOR);
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    public ArrayList<Keywords> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keywords> keywords) {
        this.keywords = keywords;
    }

    public String getPrintPage() {
        return printPage;
    }

    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public ArrayList<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(ArrayList<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getWordCount() {
        return wordCount;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(printPage);
        parcel.writeString(pubDate);
        parcel.writeString(webUrl);
        parcel.writeString(score);
        parcel.writeString(newsDesk);
        parcel.writeString(snippet);
        parcel.writeString(typeOfMaterial);
        parcel.writeString(uri);
        parcel.writeString(documentType);
        parcel.writeString(id);
        parcel.writeString(source);
        parcel.writeString(wordCount);
        parcel.writeParcelable(headline, i);
        parcel.writeParcelable(byline, i);
        parcel.writeParcelable(blog, i);
        parcel.writeTypedList(multimedia);
    }
}
