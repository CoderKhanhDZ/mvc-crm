package mvcproject.java11.crm.services;

import java.util.List;

import mvcproject.java11.crm.model.Account;

public interface iAccountService {
	
	int insertAccount(Account account);
	int updateAccount(Account account);
	int deleteAccount(int id);
	int getTotalRecordAccount(String keyword);
	Account getAccountById(int id);
	List<Account> getAllAccount();
	List<Account> getAccountByKeyword(String keyword, int index, int limit);
	
}
