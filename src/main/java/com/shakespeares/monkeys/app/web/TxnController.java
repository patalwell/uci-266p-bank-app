package com.shakespeares.monkeys.app.web;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.shakespeares.monkeys.app.dto.TransactionDto;
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

  @ModelAttribute("txn")
  public TransactionDto transactionDto() {
    return new TransactionDto();
  }

  @GetMapping(value = "/")
  public String getIndex(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(auth.getName());
    if (user == null){
      return "login";
    }

    List<TransactionInfo> txns = txnService.getTxnsByUser(user.getUsername());
    model.addAttribute("firstName", user.getFirstName());
    model.addAttribute("userName", user.getUsername());
    model.addAttribute("accountId", user.getId());
    model.addAttribute("userBalance", user.getBalance());
    model.addAttribute("txns", txns);
    model.addAttribute("txnInfo", new TransactionInfo());
    return "transactions";

  }


  @PostMapping(value = "/transactions/")
  public String createTxn(Model model,
                           @ModelAttribute TransactionDto txnInfo) {

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByUsername(auth.getName());
    if (user == null) {
      return "login";
    }
    //update table with username, so we can find it in the method above
    txnInfo.setUsername(user.getUsername());
    txnInfo.setCreatedAt(LocalDateTime.now());

    //local cache for currentBalance
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
      txnService.save(txnInfo);
      return "redirect:/";
    }
    else{
      return "redirect:/?notValidAmount";}
  }
}
