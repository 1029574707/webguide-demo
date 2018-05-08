package com.ceiec.webguide.formal.vo;

/**
 * CreateDate: 2018/5/8 <br/>
 * Author: WangHao <br/>
 * Description:
 **/
public class ParentVO {

    private String keywords;

    private int start;

    private int size;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ParentVO{" +
                "keywords='" + keywords + '\'' +
                ", start=" + start +
                ", size=" + size +
                '}';
    }
}
