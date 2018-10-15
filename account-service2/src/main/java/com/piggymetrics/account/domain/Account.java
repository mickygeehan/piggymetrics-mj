package com.piggymetrics.account.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("lastSeen")
    @Expose
    private LastSeen lastSeen;
    @SerializedName("saving")
    @Expose
    private Saving saving;
    @SerializedName("_class")
    @Expose
    private String _class;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LastSeen getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LastSeen lastSeen) {
        this.lastSeen = lastSeen;
    }

    public Saving getSaving() {
        return saving;
    }

    public void setSaving(Saving saving) {
        this.saving = saving;
    }

    public String getClass_() {
        return _class;
    }

    public void setClass_(String _class) {
        this._class = _class;
    }

}
