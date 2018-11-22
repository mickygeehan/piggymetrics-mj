package com.piggymetrics.account.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.piggymetrics.account.domain.Account;
import org.bson.Document;

import java.util.Date;

import static com.piggymetrics.account.parser.UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter;

public class GsonParser {

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = initGson();

    /**
     * Initialise Gson
     * @return
     */
    private static Gson initGson(){
        builder.registerTypeAdapter(Date.class,  getUnixEpochDateTypeAdapter());
        return gson = builder.create();
    }

    public static Account convertMongoDocumentToAccount(Document docToConvert){
        return gson.fromJson(docToConvert.toJson(), Account.class);
    }

    public static Document convertAccountToMongoDocument(Account accountToConvert){
        System.out.println(gson.toJson(accountToConvert));
        return Document.parse(gson.toJson(accountToConvert));
    }
}
