package com.swe6673.finance.repository;

import com.swe6673.finance.resource.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends MongoRepository<BankAccount, String> {

}
