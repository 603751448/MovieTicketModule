package com.funguide.cc.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tom on 2016/4/29.
 */
public class BaseResponse implements Parcelable {

    /**
     * respCode : 0000
     * respMsg : 查询成功
     */

    private String respCode;
    private String respMsg;

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getRespCode() {
        return respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.respCode);
        dest.writeString(this.respMsg);
    }

    public BaseResponse() {
    }

    protected BaseResponse(Parcel in) {
        this.respCode = in.readString();
        this.respMsg = in.readString();
    }

    public static final Parcelable.Creator<BaseResponse> CREATOR = new Parcelable.Creator<BaseResponse>() {
        public BaseResponse createFromParcel(Parcel source) {
            return new BaseResponse(source);
        }

        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };
}
