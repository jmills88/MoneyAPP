package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransactionDao implements TransactionDao{
    private JdbcTemplate jdbcTemplate;
    private AccountDao accountDao;

    public JdbcTransactionDao(JdbcTemplate jdbcTemplate, AccountDao accountDao){
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
    }

    public void transferFund( Transaction transaction){
        if(transaction.getTransferAmount() <= 0) {
            throw new IllegalArgumentException("Amount to send must to greater than Zero");
        }

        Account senderAccount = accountDao.getAccountByAccountId(transaction.getSenderAccountId());
        // Check to make sure accounts are different
        if (transaction.getSenderAccountId() == transaction.getRecipientAccountId()) {
            throw new IllegalArgumentException("Cannot send money to same account");
        }
        // Checking to see if they have money to send
        if(senderAccount.getBalance() < transaction.getTransferAmount()) {
            throw new IllegalArgumentException("Insufficient Funds ");
        }

        String deductSql = "UPDATE account SET balance = balance - ? WHERE account_id = ? ";
        int rowsUpdated = jdbcTemplate.update(deductSql, transaction.getTransferAmount(), transaction.getSenderAccountId());
        if (rowsUpdated != 1) {
            throw new RuntimeException("Failed to deduct money from account");
        }

        String addSql = "UPDATE account SET balance = balance + ? WHERE account_id = ?";
        rowsUpdated = jdbcTemplate.update(addSql, transaction.getTransferAmount(), transaction.getRecipientAccountId());
        if (rowsUpdated != 1) {
            throw new RuntimeException("Failed to add money from account");
        }

        String transactionSql = "INSERT INTO transaction (sender_account_id, recipient_account_id, transfer_amount, transaction_status) " +
                 "VALUES (?,?,?,'APPROVED') ";
            jdbcTemplate.update(transactionSql, transaction.getSenderAccountId(), transaction.getRecipientAccountId() , transaction.getTransferAmount());

//       return findTransactionByTransactionId(returnedTransactionId);

    }   @Override
        public Transaction findTransactionByTransactionId(int transactionId) {
        Transaction transaction = new Transaction();
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, transactionId);
        if (result.next()) {
            transaction = mapRowToTransaction(result);
        }
        return transaction;

    }
        private Transaction mapRowToTransaction (SqlRowSet rs) {
        Transaction transaction = new Transaction();
        transaction.setTransactionId(rs.getInt("transaction_id"));
        transaction.setRecipientAccountId(rs.getInt("recipient_account_id"));
        transaction.setSenderAccountId(rs.getInt("sender_account_id"));
        transaction.setTransferAmount(rs.getInt("transaction_amount"));
        transaction.setTransactionStatus(rs.getString("Approved"));

        return transaction;
        }


}
