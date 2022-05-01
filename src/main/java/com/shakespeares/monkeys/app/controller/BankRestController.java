package com.shakespeares.monkeys.app.controller;

import com.shakespeares.monkeys.app.banking.Balance;
import com.shakespeares.monkeys.app.user.BankUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankRestController {

    @RequestMapping("/get-transactions")
    public List<Balance> getTransactionList() {
        List<Balance> balanceList = new ArrayList<>();

        //Generate 10 Users
        for (int i=0; i < 10; i++) {
            Balance balance = new Balance(
                    new Timestamp(System.currentTimeMillis()),
                    "deposit",
                    100,
                    100);
            balanceList.add(balance);
        }
        System.out.println(balanceList);
        return balanceList;
    }
}
