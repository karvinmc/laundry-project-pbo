package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Item;
import com.example.projectpbo.dao.ItemDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    @FXML
    private TableView<Item> itemTableView;

    public void initialize() {
        // Initialize columns
        TableColumn<Item, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getIdItem()));
        TableColumn<Item, String> nama = new TableColumn<>("Nama");
        nama.setCellValueFactory
                (cellData -> cellData.getValue().namaItemProperty());
        TableColumn<Item, Integer> kategori = new TableColumn<>("ID Kategori");
        kategori.setCellValueFactory
                (cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getKategori().getIdKategori()));
        TableColumn<Item, Integer> harga = new TableColumn<>("Harga");
        harga.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getHargaItem()));

        // Adjust width
        id.setMinWidth(60);
        nama.setMinWidth(60);
        kategori.setMinWidth(60);
        harga.setMinWidth(60);

        // Add all columns
        itemTableView.getColumns().clear();
        itemTableView.getColumns().add(id);
        itemTableView.getColumns().add(nama);
        itemTableView.getColumns().add(kategori);
        itemTableView.getColumns().add(harga);

        // Initialize ChoiceBox
        kategoriChoiceBox.setItems(SharedData.getInstance().getKategoriObservableList());

        // Refresh ChoiceBox
        SharedData.getInstance().refreshKategoriChoiceBox();

        // Get the selected items
        itemTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelectedData(newSelection);
            }
        });

        itemTableView.setItems(SharedData.getInstance().getItemTable());
        SharedData.getInstance().refreshItemTable();
    }

    public boolean isValid() {
        return !namaItemTextField.getText().isBlank() && kategoriChoiceBox.getValue() != null && !hargaTextField.getText().isBlank();
    }

    public void clearInputs() {
        namaItemTextField.clear();
        kategoriChoiceBox.setValue(null);
        hargaTextField.clear();
    }

    public void getSelectedData(Item selectedItem) {
        namaItemTextField.setText(selectedItem.getNamaItem());
        kategoriChoiceBox.setValue(selectedItem.getKategori().getIdKategori() + " - " + selectedItem.getKategori().getNamaKategori());
        hargaTextField.setText(String.valueOf(selectedItem.getHargaItem()));
    }

    @FXML
    public void onSave() {
        if (isValid()) {
            // Getting the idKategori
            String selectedKategori = kategoriChoiceBox.getValue();
            int idKategori = Integer.parseInt(selectedKategori.split(" - ")[0]);

            if (itemTableView.getSelectionModel().getSelectedItem() == null) {
                ItemDAO.insertItem(new Item(namaItemTextField.getText(), Integer.parseInt(hargaTextField.getText()), idKategori));
            } else {
                Item selectedItem = itemTableView.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you want to save?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    selectedItem.setNamaItem(namaItemTextField.getText());
                    selectedItem.setKategori(idKategori);
                    selectedItem.setHargaItem(Integer.parseInt(hargaTextField.getText()));
                    ItemDAO.updateItem(selectedItem);
                }
            }
            // Set alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

            clearInputs();
            SharedData.getInstance().refreshItemChoiceBox();
            SharedData.getInstance().refreshItemTable();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Data invalid!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void onClear() {
        itemTableView.getSelectionModel().clearSelection();
        clearInputs();
    }

    @FXML
    public void onDelete() {
        if (itemTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to delete?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                ItemDAO.deleteItem(itemTableView.getSelectionModel().getSelectedItem());
                SharedData.getInstance().refreshItemTable();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText("Data successfully deleted!");
                alert2.getButtonTypes().setAll(ButtonType.OK);
                alert2.showAndWait();
            }
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
