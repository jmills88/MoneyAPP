package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transaction;

public interface TransactionDao {

   void transferFund( Transaction transaction) throws IllegalAccessException;

    Transaction findTransactionByTransactionId(int transactionId);
}
