package com.example.projectpbo;

import com.example.projectpbo.controllers.MainController;
import com.example.projectpbo.controllers.login.LoginController;
import com.example.projectpbo.dao.AccountDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LaundryApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        if (AccountDAO.getLoginStatus()) {
            FXMLLoader fxmlLoader = new FXMLLoader(LaundryApplication.class.getResource(
                    "/com/example/projectpbo/main-view.fxml"
            ));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            fxmlLoader.<MainController>getController().setMainScene(scene);
            stage.setTitle("Was Wis Wus");
            stage.setScene(scene);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(LaundryApplication.class.getResource(
                    "/com/example/projectpbo/login/login-view.fxml"
            ));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            fxmlLoader.<LoginController>getController().setLoginScene(scene);
            stage.setTitle("Was Wis Wus");
            stage.setScene(scene);
        }

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}