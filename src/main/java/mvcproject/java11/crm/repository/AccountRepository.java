package mvcproject.java11.crm.repository;

import mvcproject.java11.crm.model.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends AbstractRepository<Account> {

    public boolean existedByEmail(String email) {

        final String query = "SELECT email FROM accounts where email = ? ";
        return existedBy(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);

            ResultSet results = statement.executeQuery();

            return results.next();
        });
    }

    public void insertAccount(Account account) {

        final String query = "INSERT INTO accounts (email, password, fullname, phone, address, avatar, role_id) VALUES  ( ?,?,?,?,?,?,?)";

        excuteQueryUpdate(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getPassword());
            statement.setString(3, account.getFullname());
            statement.setString(4, account.getPhone());
            statement.setString(5, account.getAddress());
            statement.setString(6, account.getAvatar());
            statement.setInt(7, account.getRole_id());

            return statement.executeUpdate();
        });
    }

    public void updateAccount(Account account) {

        final String query = "UPDATE accounts SET  email=?, fullname=?, phone=?, address=?, avatar=?, role_id=? WHERE id = ?";

        excuteQueryUpdate(connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, account.getEmail());
            statement.setString(2, account.getFullname());
            statement.setString(3, account.getPhone());
            statement.setString(4, account.getAddress());
            statement.setString(5, account.getAvatar());
            statement.setInt(6, account.getRole_id());
            statement.setInt(7, account.getId());

            return statement.executeUpdate();
        });
    }

    public void deleteAccount(int id) {
        final String query = "DELETE FROM accounts WHERE id=?";

        excuteQueryUpdate(connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            return statement.executeUpdate();
        });
    }

    public List<Account> getAllAccount() {

        final String query = "SELECT * FROM accounts";

        return excuteQuery(connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet res = statement.executeQuery();

            List<Account> accounts = new ArrayList<>();
            Account account;

            while (res.next()) {
                account = new Account();
                account.setId(res.getInt("id"));
                account.setEmail(res.getString("email"));
                account.setPassword(res.getString("password"));
                account.setFullname(res.getString("fullname"));
                account.setPhone(res.getString("phone"));
                account.setAddress(res.getString("address"));
                account.setRole_id(res.getInt("role_id"));

                accounts.add(account);
            }

            return accounts;
        });
    }

    public Account getAccountById(int id) {

        final String query = "SELECT * FROM accounts WHERE id=? ";

        return excuteQuerySingle(connection -> {

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet res = statement.executeQuery();

            Account account = null;
            if (res.next()) {
                account = new Account();
                account.setId(res.getInt("id"));
                account.setFullname(res.getString("fullname"));
                account.setEmail(res.getString("email"));
                account.setPassword(res.getString("password"));
                account.setAddress(res.getString("address"));
                account.setPhone(res.getString("phone"));
                account.setRole_id(res.getInt("role_id"));
            }

            return account;
        });
    }

    public int getTotalRecordAccount(String keyword) {

        final String query = "SELECT COUNT(*) AS total_record  FROM accounts WHERE fullname LIKE '%" + keyword + "%'";

        return excuteQueryInteger(connnection -> {
            PreparedStatement statement = connnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();


            if (!resultSet.next()) return 0;
            return resultSet.getInt("total_record");
        });
    }

    public List<Account> getAccountByKeyword(String keyword, int index, int record_on_page) {
        StringBuilder query = new StringBuilder("SELECT * FROM accounts WHERE fullname LIKE '%");
        query.append(keyword).append("%' LIMIT ").append(index).append(",").append(record_on_page);

        return excuteQuery(connection -> {
            PreparedStatement statement = connection.prepareStatement(query.toString());
            ResultSet res = statement.executeQuery();

            List<Account> accounts = new ArrayList<>();
            Account account;

            while (res.next()) {
                account = new Account();
                account.setId(res.getInt("id"));
                account.setEmail(res.getString("email"));
                account.setPassword(res.getString("password"));
                account.setFullname(res.getString("fullname"));
                account.setPhone(res.getString("phone"));
                account.setAddress(res.getString("address"));
                account.setRole_id(res.getInt("role_id"));

                accounts.add(account);
            }

            return accounts;
        });
    }

    public Account getAccountByEmail(String email) {
        final String query = "SELECT * FROM accounts WHERE email= ? ";


        return excuteQuerySingle(connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet res = statement.executeQuery();


            Account account = null;

            if (res.next()) {
                account = new Account();
                account.setId(res.getInt("id"));
                account.setEmail(res.getString("email"));
                account.setPassword(res.getString("password"));
                account.setFullname(res.getString("fullname"));
                account.setPhone(res.getString("phone"));
                account.setAddress(res.getString("address"));
                account.setRole_id(res.getInt("role_id"));

            }

            return account;
        });
    }


}
