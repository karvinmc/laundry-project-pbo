package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.dao.CustomerDAO;
import com.example.projectpbo.dao.OrderDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerController {
    @FXML
    private TextField namaTextField;
    @FXML
    private TextField alamatTextField;
    @FXML
    private TextField noTelpTextField;
    @FXML
    private RadioButton radius_1;
    @FXML
    private RadioButton radius_2;
    @FXML
    private RadioButton radius_3;
    @FXML
    private TableView<Customer> customerTableView;
    private ToggleGroup radiusToggleGroup;
    private String radius;


    public void initialize() {
        // Initialize columns
        TableColumn<Customer, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getIdCustomer()));
        TableColumn<Customer, String> nama = new TableColumn<>("Nama");
        nama.setCellValueFactory
                (cellData -> cellData.getValue().namaCustomerProperty());
        TableColumn<Customer, String> alamat = new TableColumn<>("Alamat");
        alamat.setCellValueFactory
                (cellData -> cellData.getValue().alamatCustomerProperty());
        TableColumn<Customer, Integer> noTelp = new TableColumn<>("No Telp");
        noTelp.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getNoTelpCustomer()));
        TableColumn<Customer, Integer> radius = new TableColumn<>("ID Radius");
        radius.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getRadiusCustomer().getIdRadius()));

        // Adjust width
        id.setMinWidth(60);
        nama.setMinWidth(60);
        alamat.setMinWidth(60);
        noTelp.setMinWidth(60);
        radius.setMinWidth(60);

        // Add all columns
        customerTableView.getColumns().clear();
        customerTableView.getColumns().add(id);
        customerTableView.getColumns().add(nama);
        customerTableView.getColumns().add(alamat);
        customerTableView.getColumns().add(noTelp);
        customerTableView.getColumns().add(radius);

        // Radius RadioButton
        radiusToggleGroup = new ToggleGroup();
        radius_1.setToggleGroup(radiusToggleGroup);
        radius_2.setToggleGroup(radiusToggleGroup);
        radius_3.setToggleGroup(radiusToggleGroup);
        radiusToggleGroup.selectedToggleProperty().addListener((obs, oldValue, newValue) -> radiusSelection());

        // Get the selected items
        customerTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelectedData(newSelection);
            }
        });

        customerTableView.setItems(SharedData.getInstance().getCustomerTable());
        SharedData.getInstance().refreshCustomerTable();
    }

    public void radiusSelection() {
        RadioButton selectedRadius = (RadioButton) radiusToggleGroup.getSelectedToggle();
        if (selectedRadius != null) {
            radius = selectedRadius.getText();
        }
    }

    public Boolean isValid() {
        return !namaTextField.getText().isBlank() && !alamatTextField.getText().isBlank() &&
                !noTelpTextField.getText().isBlank() && !radius.isBlank();
    }

    public void clearInputs() {
        namaTextField.clear();
        alamatTextField.clear();
        noTelpTextField.clear();
        radiusToggleGroup.getToggles().clear();
        radius_1.setToggleGroup(radiusToggleGroup);
        radius_2.setToggleGroup(radiusToggleGroup);
        radius_3.setToggleGroup(radiusToggleGroup);
    }

    public void getSelectedData(Customer selectedCustomer) {
        namaTextField.setText(selectedCustomer.getNamaCustomer());
        alamatTextField.setText(selectedCustomer.getAlamatCustomer());
        noTelpTextField.setText(String.valueOf(selectedCustomer.getNoTelpCustomer()));
        switch (selectedCustomer.getRadiusCustomer().getIdRadius()) {
            case 1 -> radiusToggleGroup.selectToggle(radius_1);
            case 2 -> radiusToggleGroup.selectToggle(radius_2);
            case 3 -> radiusToggleGroup.selectToggle(radius_3);
        }
    }

    @FXML
    public void onSave() {
        if (isValid()) {
            if (customerTableView.getSelectionModel().getSelectedItem() == null) {
                CustomerDAO.insertCustomer(new Customer(namaTextField.getText(), alamatTextField.getText(),
                        Integer.parseInt(noTelpTextField.getText()), getRadius()));
            } else {
                Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you want to save?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    selectedCustomer.setNamaCustomer(namaTextField.getText());
                    selectedCustomer.setAlamatCustomer(alamatTextField.getText());
                    selectedCustomer.setNoTelpCustomer(Integer.parseInt(noTelpTextField.getText()));
                    selectedCustomer.setRadiusCustomer(getRadius());
                    CustomerDAO.updateCustomer(selectedCustomer);
                }
            }
            // Set alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

            clearInputs();
            SharedData.getInstance().refreshCustomerChoiceBox();
            SharedData.getInstance().refreshCustomerTable();
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
        customerTableView.getSelectionModel().clearSelection();
        clearInputs();
    }

    @FXML
    public void onDelete() {
        if (customerTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to delete?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                CustomerDAO.deleteCustomer(customerTableView.getSelectionModel().getSelectedItem());
                SharedData.getInstance().refreshCustomerTable();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText("Data successfully deleted!");
                alert2.getButtonTypes().setAll(ButtonType.OK);
                alert2.showAndWait();
            }
        }
    }

    public int getRadius() {
        switch (radius) {
            case "Radius 0-5 km = Rp 10.000" -> {
                return 1;
            }
            case "Radius 5-10 km = Rp 20.000" -> {
                return 2;
            }
            case "Radius >10 km = Rp 30.000" -> {
                return 3;
            }
        }
        return 0;
    }
}
