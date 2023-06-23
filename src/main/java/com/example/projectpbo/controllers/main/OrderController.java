package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.Item;
import com.example.projectpbo.beans.MetodePembayaran;
import com.example.projectpbo.beans.Order;
import com.example.projectpbo.dao.CustomerDAO;
import com.example.projectpbo.dao.ItemDAO;
import com.example.projectpbo.dao.MetodePembayaranDAO;
import com.example.projectpbo.dao.OrderDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderController {
    private Scene orderScene;
    private Stage orderStage;
    @FXML
    private ChoiceBox<String> namaCustomerChoiceBox;
    @FXML
    private ChoiceBox<String> itemChoiceBox;
    @FXML
    private DatePicker tanggalOrder;
    @FXML
    private ChoiceBox<String> serviceChoiceBox;
    @FXML
    private TextField lamaSelesaiTextField;
    @FXML
    private ChoiceBox<String> promoChoiceBox;
    @FXML
    private ChoiceBox<String> driverChoiceBox;
    @FXML
    private ChoiceBox<String> metodePembayaranChoiceBox;
    private Order editableOrder;
    private boolean isEdit = false;

    public void initialize() {
        // Initialize ChoiceBox
        namaCustomerChoiceBox.setItems(SharedData.getInstance().getCustomerObservableList());
        itemChoiceBox.setItems(SharedData.getInstance().getItemObservableList());

        ObservableList<String> services = FXCollections.observableArrayList();
        services.add("Dry Clean");
        services.add("Laundry");
        serviceChoiceBox.setItems(services);

        promoChoiceBox.setItems(SharedData.getInstance().getPromoObservableList());
        driverChoiceBox.setItems(SharedData.getInstance().getDriverObservableList());
        metodePembayaranChoiceBox.setItems(SharedData.getInstance().getMetodeObservableList());

        // Refresh
        SharedData.getInstance().refreshCustomerChoiceBox();
        SharedData.getInstance().refreshItemChoiceBox();
        SharedData.getInstance().refreshPromoChoiceBox();
        SharedData.getInstance().refreshDriverChoiceBox();
        SharedData.getInstance().refreshMetodeChoiceBox();
    }

    public boolean isValid() {
        return namaCustomerChoiceBox.getValue() != null && itemChoiceBox.getValue() != null && tanggalOrder.getValue() != null &&
                serviceChoiceBox.getValue() != null && !lamaSelesaiTextField.getText().isBlank() && promoChoiceBox.getValue() != null &&
                driverChoiceBox.getValue() != null;
    }

    public void loadEditData() {
        namaCustomerChoiceBox.setValue(editableOrder.getCustomer().getIdCustomer() + " - " + editableOrder.getCustomer().getNamaCustomer());
        itemChoiceBox.setValue(editableOrder.getItem().getIdItem() + " - " + editableOrder.getItem().getNamaItem());
        tanggalOrder.setValue(LocalDate.parse(editableOrder.getTanggalOrder()));
        serviceChoiceBox.setValue(editableOrder.getService());
        lamaSelesaiTextField.setText(editableOrder.getLamaPeyelesaian());
        promoChoiceBox.setValue(editableOrder.getPromoOrder().getIdPromo() + " - " + editableOrder.getPromoOrder().getNamaPromo());
        driverChoiceBox.setValue(editableOrder.getDriver().getIdDriver() + " - " + editableOrder.getDriver().getNamaDriver());
        metodePembayaranChoiceBox.setValue(editableOrder.getMetodePembayaran().getIdMetodePembayaran() + " - " + editableOrder.getMetodePembayaran().getNamaMetode());
    }

    @FXML
    public void onSave() {
        if (isValid()) {
            // Getting the all id's

            String selectedCustomer = namaCustomerChoiceBox.getValue();
            int idCustomer = Integer.parseInt(selectedCustomer.split(" - ")[0]);
            String selectedItem = itemChoiceBox.getValue();
            int idItem = Integer.parseInt(selectedItem.split(" - ")[0]);
            String selectedPromo = promoChoiceBox.getValue();
            int idPromo = Integer.parseInt(selectedPromo.split(" - ")[0]);
            String selectedDriver = promoChoiceBox.getValue();
            int idDriver = Integer.parseInt(selectedDriver.split(" - ")[0]);
            String selectedMetode = metodePembayaranChoiceBox.getValue();
            int idMetode = Integer.parseInt(selectedMetode.split(" - ")[0]);

            if (!isEdit) {
                OrderDAO.insertOrder(new Order(tanggalOrder.getValue(), getTotalHarga(),
                        getOngkosKirim(), idCustomer, idItem, idPromo, idDriver, idMetode,
                        lamaSelesaiTextField.getText(), serviceChoiceBox.getValue()));
            } else {
                editableOrder.setCustomer(idCustomer);
                editableOrder.setItem(idItem);
                editableOrder.setTanggalOrder(String.valueOf(tanggalOrder.getValue()));
                editableOrder.setService(serviceChoiceBox.getValue());
                editableOrder.setLamaPeyelesaian(lamaSelesaiTextField.getText());
                editableOrder.setPromoOrder(idPromo);
                editableOrder.setDriver(idDriver);
                editableOrder.setMetodePembayaran(idMetode);
                OrderDAO.updateOrder(editableOrder);
            }
            // Set alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

            SharedData.getInstance().refreshOrderTable();
            orderStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Data invalid!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void onCancel() {
        orderStage.close();
    }

    public int getOngkosKirim() {
        String selectedCustomer = namaCustomerChoiceBox.getValue();
        int idCustomer = Integer.parseInt(selectedCustomer.split(" - ")[0]);

        for (Customer c : CustomerDAO.getAllCustomer()) {
            if (c.getIdCustomer() == idCustomer) {
                return c.getRadiusCustomer().getHarga();
            }
        }
        return 0;
    }

    public int getTotalHarga() {
        String selectedItem = itemChoiceBox.getValue();
        int idItem = Integer.parseInt(selectedItem.split(" - ")[0]);

        for (Item i : ItemDAO.getAllItem()) {
            if (i.getIdItem() == idItem) {
                return i.getHargaItem() + getOngkosKirim();
            }
        }
        return 0;
    }

    // Getter setters
    public Scene getOrderScene() {
        return orderScene;
    }

    public void setOrderScene(Scene orderScene) {
        this.orderScene = orderScene;
    }

    public Stage getOrderStage() {
        return orderStage;
    }

    public void setOrderStage(Stage orderStage) {
        this.orderStage = orderStage;
    }

    public Order getEditableOrder() {
        return editableOrder;
    }

    public void setEditableOrder(Order editableOrder) {
        this.editableOrder = editableOrder;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}
