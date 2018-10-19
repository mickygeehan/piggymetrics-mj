package com.piggymetrics.account.service;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.DBConnector;
import com.piggymetrics.account.repository.Repository;

import org.bson.Document;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

/**
 * This class deals with the logic between the controller and database.
 * Eg.. Convertying from json to Object
 */
@Dependent
public class AccountService implements AccountServiceInt {

    @Inject
    private DBConnector dbConnector;


    /**
     * Create a user
     * @param user
     * @return
     */
    @Override
    public Account create(User user) {
        dbConnector.create();
        return null;
    }

    /**
     * Find account in the db by name.
     * @param accountName
     * @return
     */
    @Override
    public Account findByName(String accountName) {
        //MongoCollection<Document> coll = dbConnector.getCollectionAccounts();
        //Document doc = coll.find(eq("_id",accountName)).first();
        Gson gson = new Gson();
        Document accountDocument = dbConnector.getAccountDocumentByName(accountName);
        Account toReturn = gson.fromJson(accountDocument.toJson(), Account.class);
        return toReturn;
    }

    /**
     * For testing returns a list of accounts
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
