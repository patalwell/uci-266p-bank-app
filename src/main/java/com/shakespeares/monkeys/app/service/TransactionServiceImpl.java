package com.shakespeares.monkeys.app.service;

import com.shakespeares.monkeys.app.dto.TransactionDto;
import com.shakespeares.monkeys.app.dto.UserRegistrationDto;
import com.shakespeares.monkeys.app.model.Role;
import com.shakespeares.monkeys.app.model.TransactionInfo;
import com.shakespeares.monkeys.app.model.User;
import com.shakespeares.monkeys.app.repository.TxnInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

	private TxnInfoRepository txnInfoRepository;


	public TransactionServiceImpl( TxnInfoRepository txnInfoRepository) {
		super();
		this.txnInfoRepository = txnInfoRepository;
	}

	@Override
	public TransactionInfo save(TransactionDto transactionDto) {

		TransactionInfo txnInfo = new TransactionInfo(transactionDto.getUsername(), transactionDto.getAmount(), transactionDto.getBalance(),
				transactionDto.getTxnType(), transactionDto.getStatus(),transactionDto.getCreatedAt());

		return txnInfoRepository.save(txnInfo);
	}

	@Override
	public List<TransactionInfo> getTxnsByUser(String username) {

		return txnInfoRepository.findByUserName(username);
	}

}
