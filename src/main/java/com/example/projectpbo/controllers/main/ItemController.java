package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Item;
import com.example.projectpbo.controllers.login.CreateAccountController;
import com.example.projectpbo.dao.ItemDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemController {
    @FXML
    private TextField namaItemTextField;
    @FXML
    private ChoiceBox<String> kategoriChoiceBox;
    @FXML
    private TextField hargaTextField;

    public void initialize() {
        // Refresh ChoiceBox
        SharedData.getInstance().refreshKategoriChoiceBox();

        // Initialize ChoiceBox
        kategoriChoiceBox.setItems(SharedData.getInstance().getKategoriObservableList());
    }

    public boolean isValid() {
        return !namaItemTextField.getText().isBlank() && kategoriChoiceBox.getValue() != null && hargaTextField.getText().isBlank();
    }

    @FXML
    public void onAdd() {
        if (isValid()) {
            // Getting the idKategori
            String selectedKategori = kategoriChoiceBox.getValue();
            int idKategori = Integer.parseInt(selectedKategori.split(" - ")[0]);

            ItemDAO.insertItem(new Item(namaItemTextField.getText(), idKategori, Integer.parseInt(hargaTextField.getText())));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Data invalid!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void onAddKategori() throws IOException {
        Stage popupStage = new Stage();

        // Set the modality to WINDOW_MODAL and specify the main stage as the owner
        popupStage.initOwner(namaItemTextField.getScene().getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);

        // Adjust stage size
        popupStage.setWidth(325);
        popupStage.setHeight(160);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/projectpbo/main/kategori-view.fxml"
        ));
        Parent root = loader.load();
        Scene popupScene = new Scene(root);
        KategoriController kategoriController = loader.getController();
        kategoriController.setKategoriScene(popupScene);
        kategoriController.setKategoriStage(popupStage);
        popupStage.setScene(popupScene);
        popupStage.setTitle("Add Kategori");

        popupStage.show();
    }
}
