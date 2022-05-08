package com.shakespeares.monkeys.app.web;


import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.util.List;

import com.shakespeares.monkeys.app.model.Status;
import com.shakespeares.monkeys.app.model.TransactionInfo;
import com.shakespeares.monkeys.app.model.TxnType;
import com.shakespeares.monkeys.app.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.shakespeares.monkeys.app.util.Validation.validateNumericInput;

@Controller
public class TxnController {

  private final TransactionService txnService;
  private BigDecimal balance = new BigDecimal("0.00");

  public TxnController(TransactionService txnService) {
    this.txnService = txnService;
  }

  @GetMapping(value = "/")
  public String getIndex(Model model) {
    List<TransactionInfo> txns = txnService.getTxns();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("userName", auth.getName());
    model.addAttribute("accountId", auth.hashCode());
    model.addAttribute("userBalance",balance);
    model.addAttribute("txns", txns);
    model.addAttribute("txnInfo", new TransactionInfo());
    return "transactions";
  }


  @PostMapping(value = "/transactions/")
  public String createTxn(Model model,
                           @ModelAttribute TransactionInfo txnInfo) {

    if (validateNumericInput(txnInfo.getAmount())) {
      if (txnInfo.getTxnType() == TxnType.DEPOSIT && txnInfo.getAmount().compareTo(new BigDecimal("0.0")) >= 0) {
        balance = balance.add(txnInfo.getAmount());
        txnInfo.setBalance(balance);
        txnInfo.setStatus(Status.APPROVED);
      } else if (txnInfo.getTxnType() == TxnType.WITHDRAW && txnInfo.getAmount().compareTo(balance) <= 0) {
        balance = balance.subtract(txnInfo.getAmount());
        txnInfo.setBalance(balance);
        txnInfo.setStatus(Status.APPROVED);
      } else if (txnInfo.getTxnType() == TxnType.WITHDRAW && txnInfo.getAmount().compareTo(balance) == 1) {
        txnInfo.setBalance(balance);
        txnInfo.setStatus(Status.DENIED);
      }
      TransactionInfo txn = txnService.createTxn(txnInfo);
      return "redirect:/";
    }
    else{ return "redirect:/?notValidAmount";}
  }
}
