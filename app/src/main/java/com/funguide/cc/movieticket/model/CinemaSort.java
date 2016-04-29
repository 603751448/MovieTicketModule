package com.funguide.cc.movieticket.model;

import java.util.List;

/**
 * Created by tom on 2016/4/28.
 */
public class CinemaSort {
    String area;
    String count;
    List<String>  addre;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<String> getAddre() {
        return addre;
    }

    public void setAddre(List<String> addre) {
        this.addre = addre;
    }
}
