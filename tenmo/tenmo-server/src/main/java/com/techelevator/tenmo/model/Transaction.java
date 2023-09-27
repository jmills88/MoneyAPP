package com.techelevator.tenmo.model;

public class Transaction {
    private int transactionId;
    private int senderAccountId;
    private int recipientAccountId;
    private double transferAmount;
    private String transactionStatus;
    public Transaction() {
        transactionId = 0;
        senderAccountId = 0;
        recipientAccountId = 0;
        transferAmount = 0.0;
        transactionStatus = "";
    }
    public Transaction(int transactionId, int senderAccountId, int recipientAccountId, double transferAmount, String transactionStatus) {
        this.transactionId = transactionId;
        this.senderAccountId = senderAccountId;
        this.recipientAccountId = recipientAccountId;
        this.transferAmount = transferAmount;
        this.transactionStatus = transactionStatus;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public int getSenderAccountId() {
        return senderAccountId;
    }
    public void setSenderAccountId(int senderAccountId) {
        this.senderAccountId = senderAccountId;
    }
    public int getRecipientAccountId() {
        return recipientAccountId;
    }
    public void setRecipientAccountId(int recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }
    public double getTransferAmount() {
        return transferAmount;
    }
    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }
    public String getTransactionStatus() {
        return transactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}


