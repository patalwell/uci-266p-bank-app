package com.shakespeares.monkeys.app.service;


import java.util.List;

import com.shakespeares.monkeys.app.model.TransactionInfo;
import com.shakespeares.monkeys.app.repository.TxnInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

  private final TxnInfoRepository txnInfoRepository;

  public TransactionService(TxnInfoRepository txnInfoRepository) {
    this.txnInfoRepository = txnInfoRepository;
  }

  public List<TransactionInfo> getTxns() {
    return txnInfoRepository.findAll();
  }

  public TransactionInfo createTxn(TransactionInfo txnInfo) {
    return txnInfoRepository.save(txnInfo);
  }

}
