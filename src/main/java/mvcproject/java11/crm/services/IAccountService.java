package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Account;

import java.util.List;

public interface IAccountService {

    Account checkLogin(String email, String password);

    void insertAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(int id);

    int getTotalRecordAccount(String keyword);

    boolean existedByEmail(String email);

    Account getAccountById(int id);

    List<Account> getAllAccount();

    List<Account> getAccountByKeyword(String keyword, int index, int limit);

}
