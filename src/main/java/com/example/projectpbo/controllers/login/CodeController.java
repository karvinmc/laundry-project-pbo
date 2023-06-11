package com.example.projectpbo.controllers.login;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.dao.AccountDAO;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CodeController {

    @FXML
    private TextField codeTextField;
    private Account account;
    private Stage codeStage;
    private Scene codeScene;

    public void initialize() throws SQLException {
        // Getting reset code from SharedData
        codeTextField.setText(AccountDAO.getLoggedAccount().getResetCodeAccount());
    }

    @FXML
    public void onOkButton() {
        codeStage.close();
    }

    // Getter setters
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Stage getCodeStage() {
        return codeStage;
    }

    public void setCodeStage(Stage codeStage) {
        this.codeStage = codeStage;
    }

    public Scene getCodeScene() {
        return codeScene;
    }

    public void setCodeScene(Scene codeScene) {
        this.codeScene = codeScene;
    }
}
