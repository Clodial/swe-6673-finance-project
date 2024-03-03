package com.swe6673.finance.repository;

import com.swe6673.finance.resource.BankAccount;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountInterface extends MongoRepository<BankAccount, String> {

}
