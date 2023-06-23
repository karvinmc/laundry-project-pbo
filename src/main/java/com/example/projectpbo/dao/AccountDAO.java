package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.shared.SharedData;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountDAO {
    private static final String TABLE_NAME = "accounts_table";

    public static ArrayList<Account> getAllAccount() {
        ArrayList<Account> accountList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
                accountList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return accountList;
    }

    public static void insertAccount(Account account) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(username, password, email) values(?, ?, ?)");
            ps.setString(1, account.getUsernameAccount());
            ps.setString(2, account.getPasswordAccount());
            ps.setString(3, account.getEmailAccount());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void resetPassword(Account account) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `password` = ?"
                    + " WHERE `username` = ?");
            ps.setString(1, account.getPasswordAccount());
            ps.setString(2, account.getUsernameAccount());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteAccount(Account account) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `username` = ?");
            ps.setString(1, account.getUsernameAccount());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void setLoginStatus(Account account, boolean status) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `login_status` = ?"
                    + " WHERE `username` = ?");
            ps.setBoolean(1, status);
            ps.setString(2, account.getUsernameAccount());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static Account getLoggedAccount() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME + " WHERE `login_status` = 1");
            rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
        return account;
    }

    public static boolean getLoginStatus() {
        boolean status = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Account account = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME + " WHERE `login_status` = 1");
            rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }

        if (account != null) {
            status = true;
        }

        return status;
    }
}
