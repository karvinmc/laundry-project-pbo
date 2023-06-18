package com.example.projectpbo.shared;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.dao.AccountDAO;

import java.sql.SQLException;

public class SharedData {
    private static SharedData instance;
    private Account account;

    // Constructor
    public SharedData() {
    }

    // Getter setters

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Use static getInstance() to return the same SharedData object without creating a new empty
     * SharedData so all other Class uses the same populated SharedData
     * @return the instance of SharedData
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
