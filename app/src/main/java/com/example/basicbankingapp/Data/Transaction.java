package com.example.basicbankingapp.Data;

public class Transaction {
    private String fromUser;
    private String ToUser;
    private int amountTransferred;
    private int status;

    public Transaction(String fromUser, String toUser, int amountTransferred, int status) {
        this.fromUser = fromUser;
        ToUser = toUser;
        this.amountTransferred = amountTransferred;
        this.status = status;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return ToUser;
    }

    public void setToUser(String toUser) {
        ToUser = toUser;
    }

    public int getAmountTransferred() {
        return amountTransferred;
    }

    public void setAmountTransferred(int amountTransferred) {
        this.amountTransferred = amountTransferred;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
