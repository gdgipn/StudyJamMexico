package com.androidtalks.recyclerviewandroidlab.model;

/**
 * Created by leo on 1/25/15.
 */
public class RecyclerAdapterItem {

    private String firstText;
    private String secondText;

    public RecyclerAdapterItem(String firstText, String secondText) {
        this.firstText = firstText;
        this.secondText = secondText;
    }

    public String getFirstText() {
        return firstText;
    }

    public void setFirstText(String firstText) {
        this.firstText = firstText;
    }

    public String getSecondText() {
        return secondText;
    }

    public void setSecondText(String secondText) {
        this.secondText = secondText;
    }
}
