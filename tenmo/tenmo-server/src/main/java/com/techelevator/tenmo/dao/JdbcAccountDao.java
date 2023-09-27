package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcAccountDao implements AccountDao{
    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountByAccountId(int accountId) {
        String sql = "SELECT * from account where account_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, accountId);
        if(result.next()){
            return mapRowToAccount(result);
        }
        return null;
    }
    private Account mapRowToAccount(SqlRowSet sqlRowSet){
        Account account = new Account();
        account.setAccountId(sqlRowSet.getInt("account_id"));
        account.setUserId(sqlRowSet.getInt("user_id"));
        account.setBalance(sqlRowSet.getDouble("balance"));
        return account;
    }
}
