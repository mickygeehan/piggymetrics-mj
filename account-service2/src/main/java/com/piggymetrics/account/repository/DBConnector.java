package com.piggymetrics.account.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.domain.Account;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

/**
 * This class deals with the connection between this service and mongodb
 */
public class DBConnector {


    private MongoClient mongoClient = Repository.INSTANCE.getMongoClient();
    private MongoDatabase db = mongoClient.getDatabase("piggymetrics");
    private MongoCollection<Document> coll = db.getCollection("accounts");

    /**
     * Returns Mongo Document based on account name.
     * @param accountName
     * @return
     */
    public Document getAccountDocumentByName(String accountName){
        Document doc = coll.find(eq("_id",accountName)).first();
        return doc;
    }

    /**
     * Returns list of Documents
     * FOR TESTINGS
     * @return
     */
    public MongoCollection<Document> getCollectionAccounts(){
        return this.coll;
    }

    /**
     * Needs to be finished
     * Creates an account in the db.
     * @return
     */
    public Account create(){
        return null;
    }

}
