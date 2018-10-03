package com.piggymetrics.account.controller;

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
}
