package com.piggymetrics.account.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.LastSeen;
import com.piggymetrics.account.domain.Saving;
import com.piggymetrics.account.repository.DBConnector;

public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    DBConnector dbService;

    /**
     * Sets up the db connector method to return the a mongo Document
     */
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Document doc = Document.parse("{\n" +
                "    \"_id\": \"demo\",\n" +
                "    \"lastSeen\": new Date(),\n" +
                "    \"note\": \"demo note\",\n" +
                "    \"expenses\": [\n" +
                "        {\n" +
                "            \"amount\": 1300,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"home\",\n" +
                "            \"period\": \"MONTH\",\n" +
                "            \"title\": \"Rent\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 120,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"utilities\",\n" +
                "            \"period\": \"MONTH\",\n" +
                "            \"title\": \"Utilities\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 20,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"meal\",\n" +
                "            \"period\": \"DAY\",\n" +
                "            \"title\": \"Meal\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 240,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"gas\",\n" +
                "            \"period\": \"MONTH\",\n" +
                "            \"title\": \"Gas\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 3500,\n" +
                "            \"currency\": \"EUR\",\n" +
                "            \"icon\": \"island\",\n" +
                "            \"period\": \"YEAR\",\n" +
                "            \"title\": \"Vacation\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 30,\n" +
                "            \"currency\": \"EUR\",\n" +
                "            \"icon\": \"phone\",\n" +
                "            \"period\": \"MONTH\",\n" +
                "            \"title\": \"Phone\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 700,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"sport\",\n" +
                "            \"period\": \"YEAR\",\n" +
                "            \"title\": \"Gym\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"incomes\": [\n" +
                "        {\n" +
                "            \"amount\": 42000,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"wallet\",\n" +
                "            \"period\": \"YEAR\",\n" +
                "            \"title\": \"Salary\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"amount\": 500,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"icon\": \"edu\",\n" +
                "            \"period\": \"MONTH\",\n" +
                "            \"title\": \"Scholarship\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"saving\": {\n" +
                "            \"amount\": 5900,\n" +
                "            \"capitalization\": false,\n" +
                "            \"currency\": \"USD\",\n" +
                "            \"deposit\": true,\n" +
                "            \"interest\": 3.32\n" +
                "        }\n" +
                "    }");
        when(dbService.getAccountDocumentByName("demo")).thenReturn(doc);
    }

    @Test
    public void create() {
    	 
		Saving saving = new Saving();
		saving.setAmount(0.0);
		saving.setCurrency("USD");
		saving.setInterest(0.0);
		saving.setDeposit(false);
		saving.setCapitalization(false);

		Account account = new Account();
		account.setName("testName");
		account.setLastSeen(new LastSeen());
		account.setSaving(saving);
		
		//dbService.create(account);
		
	   // assertNotNull("Check account was created correctly", accountService.findByName("testName"));

    }

    /**
     * Checks that we receive an Account object back from the Service
     */
    @Test
    public void findByName() {
        assertNotNull("Check account name was parsed correctly", accountService.findByName("demo"));
    }

    /**
     * Should be a unit test on a method
     */
    @Test
    public void checkGsonMapping(){
        Account receivedAccount = accountService.findByName("demo");
        assertEquals("Check name was parsed", "demo", receivedAccount.getName());
        System.out.println(receivedAccount.getSaving().getAmount());
    }

    @After
    public void tearDown() throws Exception {
    }
}