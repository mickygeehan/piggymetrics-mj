package com.piggymetrics.account.controller;

import com.google.gson.Gson;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.service.AccountService;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PutMapping;

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
    @Produces(MediaType.APPLICATION_JSON)
    public Account createAccount(User user) {
        return accountService.create(user);
    }

    @Path("/current")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveCurrentAccount(Account account) {
        accountService.saveChanges(account);
    }

    @GET
    @Path("/allaccounts")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        Gson gson = new Gson();
        return gson.toJson(accountService.getAccounts());
    }
}
