package com.piggymetrics.account.service;

import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.repository.DBConnector;
import org.bson.BSONObject;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;

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
//        Document databaseDoc = new Document();
////        databaseDoc.append("_id","demo");
////        databaseDoc.append("saving")

        when(dbService.getAccountDocumentByName("demo")).thenReturn(doc);
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
    public void checkGsonMapping(){
        Account receivedAccount = accountService.findByName("demo");
        assertEquals("Check name was parsed", "demo", receivedAccount.getName());
        assertEquals("Check saving was parsed",(Double)5900.0, receivedAccount.getSaving().getAmount());
        assertEquals("Check date was parsed",new Date().toString(), receivedAccount.getLastSeen().getDate().toString());
    }

    @Test
    public void getAccounts() {
    }
}