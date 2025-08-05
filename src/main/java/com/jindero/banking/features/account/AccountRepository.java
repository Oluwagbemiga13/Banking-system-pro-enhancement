package com.jindero.banking.features.account;

import com.jindero.banking.features.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

  Account findByAccountNumber(String accountNumber);
  Account findByUser(User user);
  Optional<Account> findByAccountNumberAndUserId(String accountNumber, Long user_id);

}
