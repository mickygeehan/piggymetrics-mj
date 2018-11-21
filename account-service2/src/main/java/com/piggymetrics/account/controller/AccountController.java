package com.piggymetrics.account.controller;

import com.google.gson.Gson;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.service.AccountService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/")
public class AccountController {

    @Inject
    private AccountService accountService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Account getAccountByName(@PathParam("name") String accountName) {
        return accountService.findByName(accountName);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount(User user) {
        return accountService.create(user);
    }

    @GET
    @Path("/current")
    public Account getCurrentAccount() {
        return null;
    }

    @PUT
    @Path("/current")
    public void saveCurrentAccount() {

    }

    @GET
    @Path("/allaccounts")
    public String getAll() {
        Gson gson = new Gson();
        return gson.toJson(accountService.getAccounts());
    }
}
