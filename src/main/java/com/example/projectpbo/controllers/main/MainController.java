package com.example.projectpbo.controllers.main;

import com.example.projectpbo.controllers.login.LoginController;
import com.example.projectpbo.dao.AccountDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        AccountDAO.setLoginStatus(SharedData.getInstance().getAccount(), false);

        mainStage.setScene(loginScene);
        mainStage.setTitle("Was Wis Wus");
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
