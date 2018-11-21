package com.piggymetrics.account.service;

import java.util.ArrayList;
import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.bson.Document;
import org.springframework.cloud.openfeign.support.SpringMvcContract;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mongodb.Block;
import com.mongodb.client.MongoCollection;
import com.piggymetrics.account.client.AuthServiceClient;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.LastSeen;
import com.piggymetrics.account.domain.Saving;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.DBConnector;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * This class deals with the logic between the controller and database.
 * Eg.. Converting from json to Object
 */
@Dependent
public class AccountServiceImpl implements AccountService {

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
        //Unfinished feign stuff
//        AuthServiceClient authServiceClient = Feign.builder()
//        	  .contract(new SpringMvcContract())
//        	  .encoder(new GsonEncoder())
//               .decoder(new GsonDecoder())
//              .target(AuthServiceClient.class, "http://localhost");
//
//        authServiceClient.createUser(user);

        Saving saving = new Saving();
        saving.setAmount(0.0);
        saving.setCurrency("USD");
        saving.setInterest(0.0);
        saving.setDeposit(false);
        saving.setCapitalization(false);

        Account account = new Account();
        account.setName(user.getUsername());
        account.setLastSeen(new LastSeen());
        account.setSaving(saving);

        dbConnector.create(account);

        return account;
    }

    /**
     * Find account in the db by name.
     *
     * @param accountName
     * @return
     */
    @Override
    public Account findByName(String accountName) {
        GsonBuilder builder = new GsonBuilder();

        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));

        Gson gson = builder.create();
        Document accountDocument = dbConnector.getAccountDocumentByName(accountName);
        Account toReturn = gson.fromJson(accountDocument.toJson(), Account.class);
        return toReturn;
    }

    /**
     * For testing returns a list of accounts
     *
     * @return
     */
    @Override
    public ArrayList<Account> getAccounts() {
        Gson gson = new Gson();
        MongoCollection<Document> coll = dbConnector.getCollectionAccounts();
        ArrayList<Account> accounts = new ArrayList<>();
        coll.find(new Document()).forEach((Block<? super Document>) document -> accounts.add(gson.fromJson(document.toJson(), Account.class)));
        return accounts;
    }


}