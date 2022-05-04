package com.shakespeares.monkeys.app.web;


import java.math.BigDecimal;
import java.util.List;

import com.shakespeares.monkeys.app.model.Status;
import com.shakespeares.monkeys.app.model.TransactionInfo;
import com.shakespeares.monkeys.app.model.TxnType;
import com.shakespeares.monkeys.app.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/transactions")
public class TxnController {

  private final TransactionService txnService;
  private BigDecimal balance = new BigDecimal("0.00");

  public TxnController(TransactionService txnService) {
    this.txnService = txnService;
  }

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String getTxns(Model model) {
    List<TransactionInfo> txns = txnService.getTxns();
    model.addAttribute("txns", txns);
    model.addAttribute("txnInfo", new TransactionInfo());
    return "transactions";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String createTxn(Model model,
                           @ModelAttribute TransactionInfo txnInfo) {

    if (txnInfo.getTxnType() == TxnType.DEPOSIT && txnInfo.getAmount().compareTo(new BigDecimal("0.0")) > 0 ){
      balance = balance.add(txnInfo.getAmount());
      txnInfo.setBalance(balance);
      txnInfo.setStatus(Status.APPROVED);
    }

    else if (txnInfo.getTxnType() == TxnType.WITHDRAW && txnInfo.getAmount().compareTo(balance) <= 0){
      balance = balance.subtract(txnInfo.getAmount());
      txnInfo.setBalance(balance);
      txnInfo.setStatus(Status.APPROVED);
    }

    else if (txnInfo.getTxnType() == TxnType.WITHDRAW && txnInfo.getAmount().compareTo(balance) < 0){
      txnInfo.setBalance(balance);
      txnInfo.setStatus(Status.DENIED);
    }

    TransactionInfo txn = txnService.createTxn(txnInfo);
    return "redirect:/transactions/";
  }
}
