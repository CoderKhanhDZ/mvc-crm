package mvcproject.java11.crm.services;

import mvcproject.java11.crm.model.Account;
import mvcproject.java11.crm.repository.AccountRepository;

import java.util.List;

public class AccountServiceImp implements IAccountService {

    private static AccountRepository accountRepository;
    private static AccountServiceImp INSTANCE;

    private AccountServiceImp() {
        accountRepository = new AccountRepository();
    }

    public static AccountServiceImp getInstance() {

        if (INSTANCE == null) {
            INSTANCE = new AccountServiceImp();
        }
        return INSTANCE;
    }


    @Override
    public void deleteAccount(int id) {
        accountRepository.deleteAccount(id);
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> accounts = accountRepository.getAllAccount();

        // set role name cho account
        for (Account account : accounts) {
            account.setRole_name(RoleServiceImp.getInstance().getRoleById(account.getRole_id()).getName());
        }
        return accounts;
    }

    @Override
    public int getTotalRecordAccount(String keyword) {
        if (keyword.equals("default")) {
            keyword = "";
        }
        return accountRepository.getTotalRecordAccount(keyword);
    }

    /**
     * @param keyword        : tu khoa
     * @param record_on_page : so trang tren page
     * @param current_page   : trang hien tai
     * @param index          : vi tri bat dau tren limit (index,record_on_page)
     */
    @Override
    public List<Account> getAccountByKeyword(String keyword, int record_on_page, int current_page) {

        int index = (current_page - 1) * record_on_page;

        if (keyword.equals("default")) {
            keyword = "";
        }

        List<Account> accounts = accountRepository.getAccountByKeyword(keyword, index, record_on_page);

        // set role name cho account
        for (Account account : accounts) {
            account.setRole_name(RoleServiceImp.getInstance().getRoleById(account.getRole_id()).getName());
        }
        return accounts;
    }

    @Override
    public void insertAccount(Account account) {

        accountRepository.insertAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountRepository.updateAccount(account);
    }

    @Override
    public Account getAccountById(int id) {
        Account account = accountRepository.getAccountById(id);
        account.setRole_name(RoleServiceImp.getInstance().getRoleById(account.getRole_id()).getName());
        return account;
    }

    @Override
    public boolean existedByEmail(String email) {
        return accountRepository.existedByEmail(email);
    }

    public Account checkLogin(String email, String password) {

        Account account = accountRepository.getAccountByEmail(email);

        if (account == null) {
            return null;

        }

        if (account.getPassword().equals(password)) {
            return account;
        }

        return null;
    }
}
