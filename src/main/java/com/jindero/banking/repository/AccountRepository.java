package com.jindero.banking.repository;

import com.jindero.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Long> {

  Account findByAccountNumber(String accountNumber);
  Account findByOwnerName(String ownerName);

}
