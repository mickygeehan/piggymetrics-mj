package com.piggymetrics.account.repository;

import static com.mongodb.client.model.Filters.eq;

import javax.enterprise.inject.Model;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import com.piggymetrics.account.parser.GsonParser;
import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.domain.Account;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;

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
    public Account getAccountDocumentByName(String accountName) {
        Document doc = coll.find(eq("_id", accountName)).first();
        return GsonParser.convertMongoDocumentToAccount(doc);
    }

    /**
     * Needs to be finished
     * Creates an account in the db.
     *
     * @return
     */
    public void create(Account acc) {
        Document document = GsonParser.convertAccountToMongoDocument(acc);
        coll.insertOne(document);
    }

    /**
     * Updates the database when changes are made
     * @param account
     */
    public void saveChanges(Account account) {
        Document docReplacement = GsonParser.convertAccountToMongoDocument(account);
        coll.deleteOne(new Document("_id", account.getName()));
        coll.insertOne(docReplacement);
    }

    /**
     * Returns list of Documents
     * FOR TESTINGS
     *
     * @return
     */
    public ArrayList<Account> getCollectionAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        coll.find(new Document()).forEach((Block<? super Document>) document -> accounts.add(GsonParser.convertMongoDocumentToAccount(document)));
        for(Account a : accounts){
            System.out.println(a.getName()+a.getSaving().getAmount());
        }
        return accounts;
    }

}
