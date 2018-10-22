package com.piggymetrics.account.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class LastSeen {

    @SerializedName("$date")
    @Expose
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date $date) {
        this.date = $date;
    }

}