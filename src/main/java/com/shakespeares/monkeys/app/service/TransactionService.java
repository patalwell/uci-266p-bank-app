package com.shakespeares.monkeys.app.service;


import java.util.List;

import com.shakespeares.monkeys.app.dto.TransactionDto;
import com.shakespeares.monkeys.app.model.TransactionInfo;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
  TransactionInfo save(TransactionDto txnInfo);
  List<TransactionInfo> getTxnsByUser(String username);
}
