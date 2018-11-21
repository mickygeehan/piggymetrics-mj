package com.piggymetrics.account.repository;

import static com.mongodb.client.model.Filters.eq;

import javax.enterprise.inject.Model;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.domain.Account;

/**
 * This class deals with the connection between this service and mongodb
 */
@Model
public class DBConnector {


    private MongoClient mongoClient = Repository.INSTANCE.getMongoClient();
    private MongoDatabase db = mongoClient.getDatabase("piggymetrics");
    private MongoCollection<Document> coll = db.getCollection("accounts");

    /**
     * Returns Mongo Document based on account name.
     *
     * @param accountName
     * @return
     */
    public Document getAccountDocumentByName(String accountName) {
        Document doc = coll.find(eq("_id", accountName)).first();
        return doc;
    }

    /**
     * Needs to be finished
     * Creates an account in the db.
     *
     * @return
     */
    public Account create(Account acc) {
        Gson gson = new Gson();
        Document document = Document.parse(gson.toJson(acc));
        coll.insertOne(document);
        return null;
    }

    /**
     * Returns list of Documents
     * FOR TESTINGS
     *
     * @return
     */
    public MongoCollection<Document> getCollectionAccounts() {
        return this.coll;
    }

}
