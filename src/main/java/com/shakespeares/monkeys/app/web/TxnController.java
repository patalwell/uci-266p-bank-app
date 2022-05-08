package com.shakespeares.monkeys.app.web;


import java.math.BigDecimal;
import java.util.List;

import com.shakespeares.monkeys.app.dto.UserRegistrationDto;
import com.shakespeares.monkeys.app.model.Status;
import com.shakespeares.monkeys.app.model.TransactionInfo;
import com.shakespeares.monkeys.app.model.TxnType;
import com.shakespeares.monkeys.app.model.User;
import com.shakespeares.monkeys.app.repository.UserRepository;
import com.shakespeares.monkeys.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.shakespeares.monkeys.app.util.Validation.validateNumericInput;

@Controller
public class TxnController {

  private final TransactionService txnService;
  @Autowired
  UserRepository userRepository;

  public TxnController(TransactionService txnService) {
    this.txnService = txnService;
  }

  @GetMapping(value = "/")
  public String getIndex(Model model) {
    List<TransactionInfo> txns = txnService.getTxns();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(auth.getName());
    if (user == null){
      return "login";
    }
    model.addAttribute("userName", user.getUsername());
    model.addAttribute("accountId", user.getId());
    model.addAttribute("userBalance", user.getBalance());
    model.addAttribute("txns", txns);
    model.addAttribute("txnInfo", new TransactionInfo());
    return "transactions";

  }


  @PostMapping(value = "/transactions/")
  public String createTxn(Model model,
                           @ModelAttribute TransactionInfo txnInfo) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(auth.getName());
    if (user == null) {
      return "login";
    }
    BigDecimal currentBalance;

    if (validateNumericInput(txnInfo.getAmount())) {
      if (txnInfo.getTxnType() == TxnType.DEPOSIT && txnInfo.getAmount().compareTo(new BigDecimal("0.0")) >= 0) {
        currentBalance = user.getBalance().add(txnInfo.getAmount());
        user.setBalance(currentBalance);
        txnInfo.setBalance(currentBalance);
        txnInfo.setStatus(Status.APPROVED);
      } else if (txnInfo.getTxnType() == TxnType.WITHDRAW && txnInfo.getAmount().compareTo(user.getBalance()) <= 0) {
        currentBalance = user.getBalance().subtract(txnInfo.getAmount());
        user.setBalance(currentBalance);
        txnInfo.setBalance(currentBalance);
        txnInfo.setStatus(Status.APPROVED);
      } else if (txnInfo.getTxnType() == TxnType.WITHDRAW && txnInfo.getAmount().compareTo(user.getBalance()) == 1) {
        txnInfo.setBalance(user.getBalance());
        txnInfo.setStatus(Status.DENIED);
      }
      TransactionInfo txn = txnService.createTxn(txnInfo);
      return "redirect:/";
    }
    else{
      return "redirect:/?notValidAmount";}
  }
}
