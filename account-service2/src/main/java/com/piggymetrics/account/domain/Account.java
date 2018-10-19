package com.piggymetrics.account.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Account {

    @SerializedName("_id")
    @Expose
    private String name;
    @SerializedName("lastSeen")
    @Expose
    private LastSeen lastSeen;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("expenses")
    @Expose
    private List<Expense> expenses = null;
    @SerializedName("incomes")
    @Expose
    private List<Income> incomes = null;
    @SerializedName("saving")
    @Expose
    private Saving saving;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LastSeen getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(LastSeen lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public List<Income> getIncomes() {
        return incomes;
    }

    public void setIncomes(List<Income> incomes) {
        this.incomes = incomes;
    }

    public Saving getSaving() {
        return saving;
    }

    public void setSaving(Saving saving) {
        this.saving = saving;
    }

}