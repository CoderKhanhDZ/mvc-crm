package mvcproject.java11.crm.services;

import java.util.List;

import mvcproject.java11.crm.model.Account;

public interface iAccountService {
	
	void insertAccount(Account account);
	void updateAccount(Account account);
	void deleteAccount(int id);
	int getTotalRecordAccount(String keyword);
	boolean existedByEmail(String email);
	Account getAccountById(int id);
	List<Account> getAllAccount();
	List<Account> getAccountByKeyword(String keyword, int index, int limit);
	
}
