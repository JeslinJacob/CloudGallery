package com.example.blackmask.cloudgallery.Retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageDetails {

    @SerializedName("imgid")
    @Expose
    private String imgid;
    @SerializedName("userid")
    @Expose
    private String userid;
    @SerializedName(" imgURI")
    @Expose
    private String imgURI;
    @SerializedName("title")
    @Expose
    private String title;

    public ImageDetails(String imgid,String userid,String imgURI,String title)
    {
        this.imgid=imgid;
        this.userid=userid;
        this.imgURI=imgURI;
        this.title=title;
    }
    public String getImgid() {
        return imgid;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getImgURI() {
        return imgURI;
    }

    public void setImgURI(String imgURI) {
        this.imgURI = imgURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}