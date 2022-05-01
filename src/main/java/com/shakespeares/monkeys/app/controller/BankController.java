package com.shakespeares.monkeys.app.controller;

import com.shakespeares.monkeys.app.banking.Balance;
import com.shakespeares.monkeys.app.user.BankUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Returns Index View
 */

@Controller
public class BankController {

    @GetMapping("/")
    public String index(Model model) {
            List<Balance> balanceList = new ArrayList<>();

            //Generate 10 Users
            for (int i = 0; i < 10; i++) {
                Balance balance = new Balance(
                        new Timestamp(System.currentTimeMillis()),
                        "deposit",
                        100,
                        100);
                balanceList.add(balance);
            }
            System.out.println(balanceList);
            model.addAttribute("balanceList", balanceList);

            return "index";
        }

}

