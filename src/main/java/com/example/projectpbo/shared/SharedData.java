package com.example.projectpbo.shared;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.dao.AccountDAO;

import java.sql.SQLException;

public class SharedData {
    private static SharedData instance;
    private boolean isLogggedIn;
    private Account account;
    private Account authenticatedAccount;

    // Constructor
    public SharedData() {
        this.isLogggedIn = false;
    }

    // Getter setters
    public boolean isLogggedIn() {
        return isLogggedIn;
    }

    public void setLogggedIn(boolean logggedIn) {
        isLogggedIn = logggedIn;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAuthenticatedAccount() {
        return authenticatedAccount;
    }

    public void setAuthenticatedAccount(Account authenticatedAccount) {
        this.authenticatedAccount = authenticatedAccount;
    }

    /**
     * Use static getInstance() to return the same SharedData object without creating a new empty
     * SharedData so all other Class uses the same populated SharedData
     * @return
     */
    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public void updateLoggedAccount() throws SQLException {
        this.account = AccountDAO.getLoggedAccount();
    }
}
