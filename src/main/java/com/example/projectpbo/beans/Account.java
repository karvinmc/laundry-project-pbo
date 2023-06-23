package com.example.projectpbo.beans;

public class Account {
    private String emailAccount;
    private String usernameAccount;
    private String passwordAccount;

    // Constructor
    public Account(String usernameAccount, String passwordAccount, String emailAccount) {
        this.usernameAccount = usernameAccount;
        this.passwordAccount = passwordAccount;
        this.emailAccount = emailAccount;
    }

    // Getter setters
    public String getEmailAccount() {
        return emailAccount;
    }

    public void setEmailAccount(String emailAccount) {
        this.emailAccount = emailAccount;
    }

    public String getUsernameAccount() {
        return usernameAccount;
    }

    public void setUsernameAccount(String usernameAccount) {
        this.usernameAccount = usernameAccount;
    }

    public String getPasswordAccount() {
        return passwordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }
}
