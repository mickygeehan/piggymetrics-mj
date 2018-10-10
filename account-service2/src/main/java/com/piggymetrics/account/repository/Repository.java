package com.piggymetrics.account.repository;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public enum Repository {
    INSTANCE;

    MongoClient mongoClient = MongoClients.create(
            new ConnectionString("mongodb://user:sd@account-mongodb/piggymetrics"));

    public MongoClient getMongoClient() {
        return this.mongoClient;
    }

}
