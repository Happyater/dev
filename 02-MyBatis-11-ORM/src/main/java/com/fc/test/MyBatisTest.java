package com.fc.test;

import com.fc.dao.AccountDao;
import com.fc.entity.Account;
import com.fc.entity.TAccount;
import com.fc.util.MyBatisUtil;
import org.junit.Test;

import java.util.List;

public class MyBatisTest {
    @Test
    public void testFindAllCamelCase(){
        AccountDao accountDao = MyBatisUtil.getMapper(AccountDao.class);

        List<TAccount> allCamelCase = accountDao.findAllCamelCase();

        for (TAccount tAccount : allCamelCase) {
            System.out.println(tAccount);
        }

        MyBatisUtil.commit();
    }


    @Test
    public void testFindById(){
        AccountDao accountDao = MyBatisUtil.getMapper(AccountDao.class);

        Account account = accountDao.findById(1);

        System.out.println(account);

        MyBatisUtil.commit();
    }


    @Test
    public void testFindAll(){
        AccountDao accountDao = MyBatisUtil.getMapper(AccountDao.class);

        List<Account> accounts = accountDao.findAll();

        for (Account account : accounts) {
            System.out.println(account);
        }
        MyBatisUtil.commit();
    }
}
