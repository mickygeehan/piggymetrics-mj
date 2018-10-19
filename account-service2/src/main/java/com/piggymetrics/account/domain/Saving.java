package com.piggymetrics.account.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saving {

    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("capitalization")
    @Expose
    private Boolean capitalization;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("deposit")
    @Expose
    private Boolean deposit;
    @SerializedName("interest")
    @Expose
    private Double interest;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(Boolean capitalization) {
        this.capitalization = capitalization;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getDeposit() {
        return deposit;
    }

    public void setDeposit(Boolean deposit) {
        this.deposit = deposit;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

}