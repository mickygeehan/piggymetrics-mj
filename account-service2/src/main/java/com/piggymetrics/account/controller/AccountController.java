package com.piggymetrics.account.controller;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.User;
import com.piggymetrics.account.service.AccountServiceImp;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("/")
public class AccountController {

    @Inject
    private AccountServiceImp accountService;

    @GET
    public Account getAccountByName(@QueryParam("name")String accountName){
        return accountService.findByName(accountName);
    }

    @GET
    @Path("/current")
    public Account getCurrentAccount(){
        return null;
    }

    @PUT
    @Path("/current")
    public void saveCurrentAccount(){

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Account createAccount(User user){
        return accountService.create(user);
    }

}
