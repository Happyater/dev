package com.fc.dao;

import com.fc.entity.Account;
import com.fc.entity.TAccount;

import java.util.List;

public interface AccountDao {
    List<Account> findAll();

    Account findById(Integer id);

    List<TAccount> findAllCamelCase();
}
