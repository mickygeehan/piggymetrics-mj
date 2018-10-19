package com.piggymetrics.account.service;

import com.piggymetrics.account.repository.DBConnector;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;


import static org.mockito.Mockito.when;

public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    DBConnector dbService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Document databaseDoc = new Document();
        databaseDoc.append("_id","demo");
        when(dbService.getAccountDocumentByName("demo")).thenReturn(databaseDoc);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void create() {
    }

    /**
     * Checks that we receive an Account object back from the Service
     */
    @Test
    public void findByName() {
        assertNotNull("Check account name was parsed correctly", accountService.findByName("demo"));
    }

    @Test
    public void getAccounts() {
    }
}