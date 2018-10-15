package com.piggymetrics.account.domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saving {

    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("interest")
    @Expose
    private String interest;
    @SerializedName("deposit")
    @Expose
    private Boolean deposit;
    @SerializedName("capitalization")
    @Expose
    private Boolean capitalization;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public Boolean getDeposit() {
        return deposit;
    }

    public void setDeposit(Boolean deposit) {
        this.deposit = deposit;
    }

    public Boolean getCapitalization() {
        return capitalization;
    }

    public void setCapitalization(Boolean capitalization) {
        this.capitalization = capitalization;
    }

}