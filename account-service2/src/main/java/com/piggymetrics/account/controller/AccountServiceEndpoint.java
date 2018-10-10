package com.piggymetrics.account.controller;

import com.mongodb.Block;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.piggymetrics.account.repository.Repository;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/")
public class AccountServiceEndpoint {

    @GET
    @Path("/current")
    @Produces("text/plain")
    public Response getCurrentAccount() {
        return Response.ok("Here is the current account!").build();
    }

    @GET
    @Path("/test")
    @Produces("text/plain")
    public Response testDbConnection() {
        MongoClient mongoClient = Repository.INSTANCE.getMongoClient();
        MongoDatabase db = mongoClient.getDatabase("piggymetrics");
        for (String name : db.listCollectionNames()) {
            System.out.println(name);
        }
        MongoCollection<Document> coll = db.getCollection("accounts");
        coll.find(new Document()).forEach(printBlock);
        return Response.ok("Here is the current account!").build();
    }

    Block<Document> printBlock = new Block<Document>() {
        @Override
        public void apply(final Document document) {
            System.out.println(document.toJson());
        }
    };
}
