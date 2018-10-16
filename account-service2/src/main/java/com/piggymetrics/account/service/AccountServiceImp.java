package com.piggymetrics.account.service;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.repository.Repository;

import org.bson.Document;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;

@Dependent
public class AccountServiceImp implements AccountService {

    private MongoClient mongoClient = Repository.INSTANCE.getMongoClient();
    private MongoDatabase db = mongoClient.getDatabase("piggymetrics");

    @Override
    public Account create(User user) {
        // AuthServiceClient authServiceClient = Feign.builder()
        //      .decoder(new GsonDecoder()).build();
        //authServiceClient.createUser(user);
        return null;
    }

    @Override
    public ArrayList<Account> getAccounts() {
        Gson gson = new Gson();
        MongoCollection<Document> coll = db.getCollection("accounts");
        ArrayList<Account> accounts = new ArrayList<>();
        coll.find(new Document()).forEach((Block<? super Document>) document -> accounts.add(gson.fromJson(document.toJson(), Account.class)));
        return accounts;
    }


}
