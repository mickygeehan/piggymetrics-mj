package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;

import java.util.ArrayList;

public interface AccountService {

    Account create(User user);

    ArrayList<Account> getAccounts();

}
