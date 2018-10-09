package com.piggymetrics.account.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterSettings;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Arrays;

import static java.util.Arrays.asList;

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
//        MongoClient mongoClient = MongoClients.create(
//                MongoClientSettings.builder()
//                        .applyToClusterSettings(builder ->
//                                builder.hosts(Arrays.asList(new ServerAddress("172.18.0.6", 27018))))
//                        .build());
//        MongoDatabase db = mongoClient.getDatabase("piggymetrics");

        MongoClient mongoClient = MongoClients.create(
                new ConnectionString("mongodb://user:sd@account-mongodb/piggymetrics"));
        MongoDatabase db = mongoClient.getDatabase("piggymetrics");
        for (String name : db.listCollectionNames()) {
            System.out.println(name);
        }

        return Response.ok("Here is the current account!").build();
    }
}
