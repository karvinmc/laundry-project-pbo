package com.example.projectpbo.controllers.login;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.dao.AccountDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CreateAccountController {
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField reEnterPasswordField;
    @FXML
    private Button createAccountButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label invalidEmailLabel;
    @FXML
    private Label invalidUsernameLabel;
    @FXML
    private Label invalidPasswordLabel;
    @FXML
    private Label invalidPasswordLabel_2;
    @FXML
    private Label invalidReEnterPasswordLabel;
    @FXML
    private Button showPasswordButton;
    @FXML
    private Button showPasswordButton_2;
    @FXML
    private TextField showedPasswordTextField;
    @FXML
    private TextField showedReEnterPasswordTextField;
    private Scene createAccountScene;
    private Stage createAccountStage;

    public void initialize() {
        // Initialize label visibility
        invalidEmailLabel.setVisible(false);
        invalidUsernameLabel.setVisible(false);
        invalidPasswordLabel.setVisible(false);
        invalidPasswordLabel_2.setVisible(false);
        invalidReEnterPasswordLabel.setVisible(false);

        emailTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                invalidEmailLabel.setVisible(false);
                adjustStageSize();
            }
        });
        usernameTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                invalidUsernameLabel.setVisible(false);
                adjustStageSize();
            }
        });
        passwordField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                invalidPasswordLabel.setVisible(false);
                invalidPasswordLabel_2.setVisible(false);
                adjustStageSize();
            }
        });
        reEnterPasswordField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                invalidReEnterPasswordLabel.setVisible(false);
                adjustStageSize();
            }
        });
    }

    /**
     * Adjust stage size if the warnings are visible
     */
    public void adjustStageSize() {
        if (invalidEmailLabel.isVisible() || invalidUsernameLabel.isVisible() || invalidPasswordLabel.isVisible() ||
                invalidPasswordLabel_2.isVisible() || invalidReEnterPasswordLabel.isVisible()) {
            createAccountStage.setWidth(680);
        } else {
            createAccountStage.setWidth(470);
            createAccountStage.setHeight(350);
        }
    }

    public boolean isValid() {
        return !emailTextField.getText().isBlank() && !emailTextField.getText().isEmpty() &&
                !usernameTextField.getText().isBlank() && !usernameTextField.getText().isEmpty() &&
                !passwordField.getText().isBlank() && !passwordField.getText().isEmpty() &&
                !reEnterPasswordField.getText().isBlank() && !reEnterPasswordField.getText().isEmpty();
    }

    public boolean isEmailValid(Account account) {
        boolean isValid = true;
        ArrayList<Account> accountList = AccountDAO.getAllAccount();
        for (Account a : accountList) {
            if (a.getEmailAccount().equals(account.getEmailAccount())) {
                invalidEmailLabel.setVisible(true);
                adjustStageSize();
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public boolean isUsernameValid(Account account) {
        boolean isValid = true;
        ArrayList<Account> accountList = AccountDAO.getAllAccount();
        for (Account a : accountList) {
            if (a.getUsernameAccount().equals(account.getUsernameAccount())) {
                invalidUsernameLabel.setVisible(true);
                adjustStageSize();
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    public boolean isPasswordValid() {
        boolean isValid = true;
        String password = passwordField.getText();
        int characters = password.length();
        if (characters < 5 || characters > 20) {
            invalidPasswordLabel.setVisible(true);
            invalidPasswordLabel_2.setVisible(true);
            adjustStageSize();
            isValid = false;
        }
        return isValid;
    }

    public boolean isReEnterPasswordValid() {
        boolean isValid = true;
        if (!passwordField.getText().equals(reEnterPasswordField.getText())) {
            invalidReEnterPasswordLabel.setVisible(true);
            adjustStageSize();
            isValid = false;
        }
        return isValid;
    }

    @FXML
    public void showPassword() {
        showedPasswordTextField.setText(passwordField.getText());
        showedPasswordTextField.setVisible(true);
        passwordField.setVisible(false);
    }

    @FXML
    public void hidePassword() {
        showedPasswordTextField.setVisible(false);
        passwordField.setVisible(true);
    }

    @FXML
    public void reEnterShowPassword() {
        showedReEnterPasswordTextField.setText(passwordField.getText());
        showedReEnterPasswordTextField.setVisible(true);
        reEnterPasswordField.setVisible(false);
    }

    @FXML
    public void reEnterHidePassword() {
        showedReEnterPasswordTextField.setVisible(false);
        reEnterPasswordField.setVisible(true);
    }

    @FXML
    public void onCreateAccount() throws IOException {
        if (isValid()) {
            Account account = new Account(
                    usernameTextField.getText().toLowerCase(), passwordField.getText().toLowerCase(), emailTextField.getText()
            );
            if (isEmailValid(account) && isUsernameValid(account) && isPasswordValid() && isReEnterPasswordValid()) {
                AccountDAO.insertAccount(account);

                // Giving account to SharedData
                SharedData.getInstance().setAccount(account);

                // Set alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Successfully created an account!");
                alert.getButtonTypes().setAll(ButtonType.OK);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.OK) {
                    // Back to log in view
                    createAccountStage.close();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Invalid data!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void onCancelButton() {
        createAccountStage.close();
    }

    // Getter setters
    public Scene getCreateAccountScene() {
        return createAccountScene;
    }

    public void setCreateAccountScene(Scene createAccountScene) {
        this.createAccountScene = createAccountScene;
    }

    public Stage getCreateAccountStage() {
        return createAccountStage;
    }

    public void setCreateAccountStage(Stage createAccountStage) {
        this.createAccountStage = createAccountStage;
    }
}
