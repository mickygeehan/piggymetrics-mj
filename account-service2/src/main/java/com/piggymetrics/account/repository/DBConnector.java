package com.piggymetrics.account.repository;

import static com.mongodb.client.model.Filters.eq;

import javax.enterprise.inject.Model;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.mongodb.Block;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.domain.Account;
import org.bson.conversions.Bson;

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
        GsonBuilder builder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (json, typeOfT, context) -> new Date(json.getAsJsonPrimitive().getAsLong()));
        Gson gson = builder.create();
        Document doc = coll.find(eq("_id", accountName)).first();
        Account toReturn = gson.fromJson(doc.toJson(), Account.class);
        return toReturn;
    }

    /**
     * Needs to be finished
     * Creates an account in the db.
     *
     * @return
     */
    public void create(Account acc) {
        Gson gson = new Gson();
        Document document = Document.parse(gson.toJson(acc));
        coll.insertOne(document);
    }

    /**
     * Updates the database when changes are made
     * @param account
     */
    public void saveChanges(Account  account) {
        Gson gson = new Gson();
        //Document docToReplace = getAccountDocumentByName(account.getName());
        Document docReplacement = Document.parse(gson.toJson(account));
        coll.updateOne(eq("name", account.getName()), new Document("$set", docReplacement));
    }

    /**
     * Returns list of Documents
     * FOR TESTINGS
     *
     * @return
     */
    public ArrayList<Account> getCollectionAccounts() {
        Gson gson = new Gson();
        ArrayList<Account> accounts = new ArrayList<>();
        coll.find(new Document()).forEach((Block<? super Document>) document -> accounts.add(gson.fromJson(document.toJson(), Account.class)));
        return accounts;
    }

}
