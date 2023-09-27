package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.JdbcTransactionDao;
import com.techelevator.tenmo.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
public class TransactionController {
    private JdbcTransactionDao jdbcTransactionDao;

    public TransactionController(JdbcTransactionDao jdbcTransactionDao) {
        this.jdbcTransactionDao = jdbcTransactionDao;
    }
   // @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public void transferFund(@Valid @RequestBody Transaction transaction)  {
          jdbcTransactionDao.transferFund(transaction);

    }

// @PreAuthorize("permitAll")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public LoginResponse login(@Valid @RequestBody LoginDTO loginDto) {
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
//
//        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = tokenProvider.createToken(authentication, false);
//
//        User user = userDao.findByUsername(loginDto.getUsername());
//
//        return new LoginResponse(jwt, user);

}
