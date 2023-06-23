package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Driver;
import com.example.projectpbo.dao.DriverDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class DriverController {
    @FXML
    private TextField namaDriverTextField;
    @FXML
    private TextField noTelpTextField;
    @FXML
    private TableView<Driver> driverTableView;

    public void initialize() {
        // Initialize columns
        TableColumn<Driver, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getIdDriver()));
        TableColumn<Driver, String> nama = new TableColumn<>("Nama");
        nama.setCellValueFactory
                (cellData -> cellData.getValue().namaDriverProperty());
        TableColumn<Driver, Integer> noTelp = new TableColumn<>("No Telp");
        noTelp.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getNoTelpDriver()));

        // Adjust width
        id.setMinWidth(60);
        nama.setMinWidth(60);
        noTelp.setMinWidth(60);

        // Add all columns
        driverTableView.getColumns().clear();
        driverTableView.getColumns().add(id);
        driverTableView.getColumns().add(nama);
        driverTableView.getColumns().add(noTelp);

        // Get the selected items
        driverTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelectedData(newSelection);
            }
        });

        driverTableView.setItems(SharedData.getInstance().getDriverTable());
        SharedData.getInstance().refreshDriverTable();
    }

    public boolean isValid() {
        return !namaDriverTextField.getText().isBlank() && !noTelpTextField.getText().isBlank();
    }

    public void clearInputs() {
        namaDriverTextField.clear();
        noTelpTextField.clear();
    }

    public void getSelectedData(Driver selectedDriver) {
        namaDriverTextField.setText(selectedDriver.getNamaDriver());
        noTelpTextField.setText(String.valueOf(selectedDriver.getNoTelpDriver()));
    }

    @FXML
    public void onSave() {
        if (isValid()) {
            if (driverTableView.getSelectionModel().getSelectedItem() == null) {
                DriverDAO.insertDriver(new Driver(namaDriverTextField.getText(), Integer.parseInt(noTelpTextField.getText())));
            } else {
                Driver selectedDriver = driverTableView.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you want to save?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    selectedDriver.setNamaDriver(namaDriverTextField.getText());
                    selectedDriver.setNoTelpDriver(Integer.parseInt(noTelpTextField.getText()));
                    DriverDAO.updateDriver(selectedDriver);
                }
            }

            // Set alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

            clearInputs();
            SharedData.getInstance().refreshDriverChoiceBox();
            SharedData.getInstance().refreshDriverTable();
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
        driverTableView.getSelectionModel().clearSelection();
        clearInputs();
    }

    @FXML
    public void onDelete() {
        if (driverTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to delete?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                DriverDAO.deleteDriver(driverTableView.getSelectionModel().getSelectedItem());
                SharedData.getInstance().refreshDriverTable();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText("Data successfully deleted!");
                alert2.getButtonTypes().setAll(ButtonType.OK);
                alert2.showAndWait();
            }
        }
    }
}
