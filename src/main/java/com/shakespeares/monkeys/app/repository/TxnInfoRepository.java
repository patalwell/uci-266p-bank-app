package com.shakespeares.monkeys.app.repository;


import com.shakespeares.monkeys.app.model.TransactionInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TxnInfoRepository extends JpaRepository<TransactionInfo,
        Integer> {

}
