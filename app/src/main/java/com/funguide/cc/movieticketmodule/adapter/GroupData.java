package com.funguide.cc.movieticketmodule.adapter;

import java.util.List;

/**
 * Created by tom on 2016/4/25.
 */
public class GroupData {
    String startTime;
    String endTime;
    String type;
    List<Child> childs;

    @Override
    public String toString() {
        return "GroupData{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", type='" + type + '\'' +
                ", childs=" + childs +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }
    public static class  Child{
        String url;
        String name;

        @Override
        public String toString() {
            return "Child{" +
                    "url='" + url + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
