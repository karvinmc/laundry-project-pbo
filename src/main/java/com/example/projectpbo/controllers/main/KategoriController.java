package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Kategori;
import com.example.projectpbo.dao.KategoriDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class KategoriController {
    private Stage kategoriStage;
    private Scene kategoriScene;
    @FXML
    private TextField kategoriTextField;

    public boolean isValid() {
        return !kategoriTextField.getText().isBlank();
    }

    @FXML
    public void onAdd() {
        if (isValid()) {
            KategoriDAO.insertKategori(new Kategori(kategoriTextField.getText()));
            // Set alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.OK) {
                // Refresh kategori ChoiceBox
                SharedData.getInstance().refreshKategoriChoiceBox();

                // Back to log in view
                kategoriStage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Data invalid!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    // Getter setters
    public Stage getKategoriStage() {
        return kategoriStage;
    }

    public void setKategoriStage(Stage kategoriStage) {
        this.kategoriStage = kategoriStage;
    }

    public Scene getKategoriScene() {
        return kategoriScene;
    }

    public void setKategoriScene(Scene kategoriScene) {
        this.kategoriScene = kategoriScene;
    }
}
