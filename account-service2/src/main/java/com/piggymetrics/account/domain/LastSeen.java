package com.piggymetrics.account.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastSeen {

    @SerializedName("$date")
    @Expose
    private Long $date;

    public Long get$date() {
        return $date;
    }

    public void set$date(Long $date) {
        this.$date = $date;
    }

}
