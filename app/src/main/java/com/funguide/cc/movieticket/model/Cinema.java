package com.funguide.cc.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tom on 2016/4/27.
 */
public class Cinema extends BaseResponse implements Parcelable {

    /**
     * cinemaId : 3123
     * cinemaName : 天水大地数字影院-亚太店
     * addre : 天水市麦积区商埠路亚太购物中心5楼
     * publicTransit : 33路公交车
     * descript : 天水市内五星级的配置,合理化的票价,首轮大片抢先观看,更多精彩,更多体验。精彩尽在大地数字影院！
     * longitude : 105.8890973020
     * latitude : 34.5704798492
     * cityId : 317
     * area : 麦积区
     * tel : 0938-2788663
     * goods : false
     * lowestPrice : 20
     */

    private int cinemaId; //影院id
    private String cinemaName;//影院名称
    private String addre;//影院地址
    private String publicTransit;//公交路线
    private String descript;//影院简介
    private String longitude;//经度
    private String latitude;//纬度
    private int cityId;//城市id
    private String area;//城区名称
    private String tel;//联系电话
    private boolean goods;//是否去过
    private int lowestPrice;//最低价格


    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getAddre() {
        return addre;
    }

    public void setAddre(String addre) {
        this.addre = addre;
    }

    public String getPublicTransit() {
        return publicTransit;
    }

    public void setPublicTransit(String publicTransit) {
        this.publicTransit = publicTransit;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isGoods() {
        return goods;
    }

    public void setGoods(boolean goods) {
        this.goods = goods;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.cinemaId);
        dest.writeString(this.cinemaName);
        dest.writeString(this.addre);
        dest.writeString(this.publicTransit);
        dest.writeString(this.descript);
        dest.writeString(this.longitude);
        dest.writeString(this.latitude);
        dest.writeInt(this.cityId);
        dest.writeString(this.area);
        dest.writeString(this.tel);
        dest.writeByte(goods ? (byte) 1 : (byte) 0);
        dest.writeInt(this.lowestPrice);
    }

    public Cinema() {
    }

    protected Cinema(Parcel in) {
        this.cinemaId = in.readInt();
        this.cinemaName = in.readString();
        this.addre = in.readString();
        this.publicTransit = in.readString();
        this.descript = in.readString();
        this.longitude = in.readString();
        this.latitude = in.readString();
        this.cityId = in.readInt();
        this.area = in.readString();
        this.tel = in.readString();
        this.goods = in.readByte() != 0;
        this.lowestPrice = in.readInt();
    }

    public static final Parcelable.Creator<Cinema> CREATOR = new Parcelable.Creator<Cinema>() {
        public Cinema createFromParcel(Parcel source) {
            return new Cinema(source);
        }

        public Cinema[] newArray(int size) {
            return new Cinema[size];
        }
    };
}
