package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;

import java.util.ArrayList;

public interface IAccountService {

    Account create(User user);

    Account findByName(String accountName);

    void saveChanges(Account account);

    ArrayList<Account> getAccounts();

}
