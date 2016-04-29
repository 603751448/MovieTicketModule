package com.funguide.cc.movieticket.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tom on 2016/4/29.
 */
public class FilmComment extends BaseResponse implements Parcelable {

    /**
     * respCode : 0000
     * respMsg : 查询成功
     * pre : 5
     * sum : 2
     * mark : 5.8
     * comments : [{"id":12,"content":"得咯(⊙o⊙)袄","time":"2016-04-15 17:21:13","name":null,"picurl":"http://zhaohang.fun-guide.mobi:8080/ticket/images/ua/DEFAULT.png","mobile":"135****345","comments":[{"id":13,"content":"得咯(⊙o⊙)袄","time":"2016-04-15 17:21:13","name":null,"picurl":"http://zhaohang.fun-guide.mobi:8080/ticket/images/ua/DEFAULT.png","mobile":"135****345"}]}]
     * pageNum : 1
     */

    private String pre;
    private int sum;
    private String mark;
    private int pageNum;
    private List<CommentsBean> comments;//父级评论

    public void setPre(String pre) {
        this.pre = pre;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }
    public String getPre() {
        return pre;
    }

    public int getSum() {
        return sum;
    }

    public String getMark() {
        return mark;
    }

    public int getPageNum() {
        return pageNum;
    }

    public List<CommentsBean> getComments() {
        return comments;
    }

    public static class CommentsBean {
        /**
         * id : 12
         * content : 得咯(⊙o⊙)袄
         * time : 2016-04-15 17:21:13
         * name : null
         * picurl : http://zhaohang.fun-guide.mobi:8080/ticket/images/ua/DEFAULT.png
         * mobile : 135****345
         * comments : [{"id":13,"content":"得咯(⊙o⊙)袄","time":"2016-04-15 17:21:13","name":null,"picurl":"http://zhaohang.fun-guide.mobi:8080/ticket/images/ua/DEFAULT.png","mobile":"135****345"}]
         */

        private int id;
        private String content;
        private String time;
        private Object name;
        private String picurl;
        private String mobile;
        private List<CommentsBean> childComments;//子级评论

        public void setId(int id) {
            this.id = id;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setComments(List<CommentsBean> comments) {
            this.childComments = comments;
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public String getTime() {
            return time;
        }

        public Object getName() {
            return name;
        }

        public String getPicurl() {
            return picurl;
        }

        public String getMobile() {
            return mobile;
        }
        public List<CommentsBean> getComments() {
            return childComments;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.pre);
        dest.writeInt(this.sum);
        dest.writeString(this.mark);
        dest.writeInt(this.pageNum);
        dest.writeList(this.comments);
    }

    public FilmComment() {
    }

    protected FilmComment(Parcel in) {
        this.pre = in.readString();
        this.sum = in.readInt();
        this.mark = in.readString();
        this.pageNum = in.readInt();
        this.comments = new ArrayList<CommentsBean>();
        in.readList(this.comments, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<FilmComment> CREATOR = new Parcelable.Creator<FilmComment>() {
        public FilmComment createFromParcel(Parcel source) {
            return new FilmComment(source);
        }
        public FilmComment[] newArray(int size) {
            return new FilmComment[size];
        }
    };
}
