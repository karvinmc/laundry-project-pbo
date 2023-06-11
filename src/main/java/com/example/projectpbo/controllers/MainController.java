package com.example.projectpbo.controllers;

import com.example.projectpbo.controllers.login.CodeController;
import com.example.projectpbo.controllers.login.LoginController;
import com.example.projectpbo.dao.AccountDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MainController {
    private Scene mainScene;
    private Stage mainStage = new Stage();

    private SharedData sharedData = SharedData.getInstance();

    public void initialize() {
    }

    @FXML
    public void onResetCodeMenuItem() throws IOException {
        Stage popupStage = new Stage();

        // Set the modality to WINDOW_MODAL and specify the main stage as the owner
        popupStage.initOwner(mainScene.getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);

        // Adjust stage size
        popupStage.setWidth(470);
        popupStage.setHeight(350);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/projectpbo/login/reset-code-view.fxml"
        ));
        Parent root = loader.load();
        Scene popupScene = new Scene(root);
        CodeController codeController = loader.getController();
        codeController.setCodeScene(popupScene);
        codeController.setCodeStage(popupStage);
        popupStage.setScene(popupScene);
        popupStage.sizeToScene();
        popupStage.setTitle("Reset Code");

        popupStage.show();
    }

    @FXML
    public void onLogoutMenuItem() throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/projectpbo/login/login-view.fxml"
        ));
        Parent root = loader.load();
        Scene loginScene = new Scene(root);
        LoginController loginController = loader.getController();
        loginController.setLoginScene(loginScene);
        Stage previousStage = (Stage) mainScene.getWindow();
        previousStage.close();

        // Logout
        SharedData.getInstance().updateLoggedAccount();
        AccountDAO.setLoginStatus(SharedData.getInstance().getAccount(), 0);

        mainStage.setScene(loginScene);
        mainStage.setTitle("Login");
        mainStage.sizeToScene();
        mainStage.show();
    }

    // Getter setters
    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public Scene getMainScene() {
        return mainScene;
    }

    public void setMainScene(Scene mainScene) {
        this.mainScene = mainScene;
    }
}
