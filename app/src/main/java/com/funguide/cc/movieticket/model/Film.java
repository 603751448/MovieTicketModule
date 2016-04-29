package com.funguide.cc.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tom on 2016/4/27.
 */
public class Film extends BaseResponse implements Parcelable {

    /**
     * filmId : 1764
     * filmName : 荒野猎人
     * director : 亚利桑德罗·冈萨雷斯·伊纳里多
     * actor : 莱昂纳多·迪卡普里奥/汤姆·哈迪/威尔·保尔特/多姆纳尔·格里森
     * sightType : 2D/IMAX
     * filmType : 剧情/惊悚/冒险
     * duration : 156
     * buyCount : 199
     * score : 8.0
     * focus : 熊袭惨兮兮，小李拿影帝
     * releaseTime : 2016-03-18
     * picHor : http://img.fun-guide.mobi/140/https://s3.cn-north-1.amazonaws.com.cn/movie-files/uploads/film_photo/photo_file/9726/p2323065951.jpg
     * picVer : http://img.fun-guide.mobi/140/https://s3.cn-north-1.amazonaws.com.cn/movie-files/uploads/film_photo/photo_file/9727/p2256908905.jpg|http://img.fun-guide.mobi/140/https://s3.cn-north-1.amazonaws.com.cn/movie-files/uploads/film_photo/photo_file/9728/p2256909201.jpg
     * video : http://mr.funguide.com.cn:3001/uploads/film/video_file/1764/
     * release : 0
     */

    private int filmId;
    private String filmName;
    private String director;
    private String actor;
    private String sightType;
    private String filmType;
    private String duration;
    private String buyCount;
    private String score;
    private String focus;
    private String releaseTime;
    private String picHor;
    private String picVer;
    private String video;
    private String release;



    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public void setSightType(String sightType) {
        this.sightType = sightType;
    }

    public void setFilmType(String filmType) {
        this.filmType = filmType;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setBuyCount(String buyCount) {
        this.buyCount = buyCount;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setFocus(String focus) {
        this.focus = focus;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setPicHor(String picHor) {
        this.picHor = picHor;
    }

    public void setPicVer(String picVer) {
        this.picVer = picVer;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public String getSightType() {
        return sightType;
    }

    public String getFilmType() {
        return filmType;
    }

    public String getDuration() {
        return duration;
    }

    public String getBuyCount() {
        return buyCount;
    }

    public String getScore() {
        return score;
    }

    public String getFocus() {
        return focus;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public String getPicHor() {
        return picHor;
    }

    public String getPicVer() {
        return picVer;
    }

    public String getVideo() {
        return video;
    }

    public String getRelease() {
        return release;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.filmId);
        dest.writeString(this.filmName);
        dest.writeString(this.director);
        dest.writeString(this.actor);
        dest.writeString(this.sightType);
        dest.writeString(this.filmType);
        dest.writeString(this.duration);
        dest.writeString(this.buyCount);
        dest.writeString(this.score);
        dest.writeString(this.focus);
        dest.writeString(this.releaseTime);
        dest.writeString(this.picHor);
        dest.writeString(this.picVer);
        dest.writeString(this.video);
        dest.writeString(this.release);
    }

    public Film() {
    }

    protected Film(Parcel in) {
        this.filmId = in.readInt();
        this.filmName = in.readString();
        this.director = in.readString();
        this.actor = in.readString();
        this.sightType = in.readString();
        this.filmType = in.readString();
        this.duration = in.readString();
        this.buyCount = in.readString();
        this.score = in.readString();
        this.focus = in.readString();
        this.releaseTime = in.readString();
        this.picHor = in.readString();
        this.picVer = in.readString();
        this.video = in.readString();
        this.release = in.readString();
    }

    public static final Parcelable.Creator<Film> CREATOR = new Parcelable.Creator<Film>() {
        public Film createFromParcel(Parcel source) {
            return new Film(source);
        }

        public Film[] newArray(int size) {
            return new Film[size];
        }
    };
}
