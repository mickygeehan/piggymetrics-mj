package com.piggymetrics.account.service;

import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.piggymetrics.account.client.AuthServiceClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.LastSeen;
import com.piggymetrics.account.domain.Saving;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.DBConnector;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

/**
 * This class deals with the logic between the controller and database.
 * Eg.. Converting from json to Object
 */
@Dependent
public class AccountService implements IAccountService {

    @Inject
    private DBConnector dbConnector;

    /**
     * Create a user
     *
     * @param user
     * @return
     */
    @Override
    public Account create(User user) {
        AuthServiceClient authServiceClient = Feign.builder()
        	  .contract(new SpringMvcContract())
        	  .encoder(new GsonEncoder())
               .decoder(new GsonDecoder())
              .target(AuthServiceClient.class, "http://auth-service:5000");

        authServiceClient.createUser(user);
        Account starterAccount = makeStarterAccount(user.getUsername());
        dbConnector.create(starterAccount);
        return starterAccount;
    }

    /**
     * Find account in the db by name.
     *
     * @param accountName
     * @return
     */
    @Override
    public Account findByName(String accountName) {
        Account foundAccount = dbConnector.getAccountDocumentByName(accountName);
        return foundAccount;
    }

    /**
     * Save changes in the database
     * @param account
     */
    @Override
    public void saveChanges(Account account) {
        dbConnector.saveChanges(account);
    }

    /**
     * For testing returns a list of accounts
     *
     * @return
     */
    @Override
    public ArrayList<Account> getAccounts() {
        return dbConnector.getCollectionAccounts();
    }

    private Account makeStarterAccount(String username) {
        Saving saving = new Saving();
        saving.setAmount(0.0);
        saving.setCurrency("USD");
        saving.setInterest(0.0);
        saving.setDeposit(false);
        saving.setCapitalization(false);

        Account account = new Account();
        account.setName(username);
        account.setLastSeen(new LastSeen());
        account.getLastSeen().setDate(new Date());
        account.setSaving(saving);

        return account;
    }

}
