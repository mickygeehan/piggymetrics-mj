package com.piggymetrics.account.parser;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.piggymetrics.account.domain.Account;
import com.piggymetrics.account.domain.Expense;
import com.piggymetrics.account.domain.Income;
import com.piggymetrics.account.domain.Saving;
import org.bson.Document;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import sun.misc.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GsonParserTest {

    private Document mongoDocument;
    private Account account;

    @Before
    public void setUp() {
        mongoDocument = initialiseMongoDocument();
        account = initialiseAccount();
    }

    @Test
    public void convertMongoDocumentToAccount() {
        Account account = GsonParser.convertMongoDocumentToAccount(mongoDocument);
        assertNotNull("Check not null",account);
        assertEquals("Check name mapped", "demo" ,account.getName());
        assertNotNull("Check date mapped", account.getLastSeen().getDate().toString());
    }

    @Test
    public void convertAccountToMongoDocument() {
        Document document = GsonParser.convertAccountToMongoDocument(account);

        assertNotNull("Check document was not null",document);
        assertEquals("Check name was parsed", "test", document.get("_id"));

        assertEquals("Check array was parsed", 1000.0, document.get("expenses"));
    }

    private Document initialiseMongoDocument()  {
        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = new ClassPathResource("document.json").getInputStream();
            result = CharStreams.toString(new InputStreamReader(
                    inputStream, Charsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Document.parse(result);
    }

    private Account initialiseAccount(){
        Account accountToReturn = new Account();
        accountToReturn.setName("test");
        accountToReturn.setNote("My Note");

        Saving saving = new Saving();
        saving.setAmount(1000.0);
        saving.setCapitalization(true);
        accountToReturn.setSaving(saving);

        List<Expense> expenseList = new ArrayList<>();
        Expense e = new Expense();
        e.setAmount(1000.0);
        e.setCurrency("EUR");
        expenseList.add(e);
        accountToReturn.setExpenses(expenseList);

        List<Income> incomeList = new ArrayList<>();
        Income i = new Income();
        i.setAmount(100.0);
        i.setCurrency("EUR");
        accountToReturn.setIncomes(incomeList);

        return accountToReturn;
    }
}