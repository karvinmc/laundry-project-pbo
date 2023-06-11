package com.example.projectpbo.controllers.login;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.controllers.MainController;
import com.example.projectpbo.dao.AccountDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Hyperlink forgotPasswordHyperLink;
    @FXML
    private Hyperlink createAccountHyperLink;
    @FXML
    private Label invalidLogin;
    private Stage loginStage = new Stage();
    private Scene loginScene;
    private SharedData sharedData = SharedData.getInstance();

    public void initialize() {
        invalidLogin.setVisible(false);

        usernameTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                invalidLogin.setVisible(false);
            }
        });
        passwordField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (!oldValue.equals(newValue)) {
                invalidLogin.setVisible(false);
            }
        });
    }

    public boolean isValidLogin(String username, String password) {
        boolean isValid = false;
        ArrayList<Account> accountList = AccountDAO.getAllAccount();

        for (Account a : accountList) {
            if (a.getUsernameAccount().equals(username) &&
                    a.getPasswordAccount().equals(password)) {
                AccountDAO.setLoginStatus(a, 1);
                SharedData.getInstance().setAccount(a);
                isValid = true;
                break;
            }
        }

        if (!isValid) invalidLogin.setVisible(true);
        return isValid;
    }

    @FXML
    public void onLogin() throws IOException {
        if (isValidLogin(usernameTextField.getText(), passwordField.getText())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/projectpbo/main-view.fxml"
            ));
            Parent root = loader.load();
            Scene mainScene = new Scene(root);
            MainController mainController = loader.getController();
            mainController.setMainScene(mainScene);
            Stage previousStage = (Stage) loginScene.getWindow();
            previousStage.close();

            loginStage.setScene(mainScene);
            loginStage.setTitle("Was Wis Wus");
            loginStage.sizeToScene();

            loginStage.show();
        }
    }

    @FXML
    public void onForgotPasswordClicked() {
    }

    @FXML
    public void onCreateAccountClicked() throws IOException {
        Stage popupStage = new Stage();

        // Set the modality to WINDOW_MODAL and specify the main stage as the owner
        popupStage.initOwner(createAccountHyperLink.getScene().getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);

        // Adjust stage size
        popupStage.setWidth(470);
        popupStage.setHeight(350);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/projectpbo/login/create-account-form.fxml"
        ));
        Parent root = loader.load();
        Scene popupScene = new Scene(root);
        CreateAccountController createAccountController = loader.getController();
        createAccountController.setCreateAccountScene(popupScene);
        createAccountController.setCreateAccountStage(popupStage);
        popupStage.setScene(popupScene);
        popupStage.setTitle("Create Account");

        popupStage.show();
    }

    // Getter setters
    public Stage getLoginStage() {
        return loginStage;
    }

    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }

    public Scene getLoginScene() {
        return loginScene;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }
}
