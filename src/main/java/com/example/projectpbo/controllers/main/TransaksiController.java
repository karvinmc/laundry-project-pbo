package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Order;
import com.example.projectpbo.dao.OrderDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TransaksiController {
    @FXML
    private TableView<Order> orderTableView;

    public void initialize() {
        // Initialize Columns
        TableColumn<Order, Integer> idOrder = new TableColumn<>("ID Order");
        idOrder.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getIdOrder()));
        TableColumn<Order, String> service = new TableColumn<>("Service");
        service.setCellValueFactory
                (cellData -> cellData.getValue().serviceProperty());
        TableColumn<Order, Integer> ongkosKirim = new TableColumn<>("Ongkos Kirim");
        ongkosKirim.setCellValueFactory
                (cellData -> Bindings.createObjectBinding(() -> cellData.getValue().getOngkosKirimDelivery()));
        TableColumn<Order, Integer> totalHarga = new TableColumn<>("Total Harga");
        totalHarga.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getTotalHarga()));
        TableColumn<Order, String> tglOrder = new TableColumn<>("Tanggal Order");
        tglOrder.setCellValueFactory
                (cellData -> cellData.getValue().tanggalOrderProperty());
        TableColumn<Order, String> lamaPenyelesaian = new TableColumn<>("Lama Penyelesaian");
        lamaPenyelesaian.setCellValueFactory
                (cellData -> cellData.getValue().lamaPeyelesaianProperty());
        TableColumn<Order, String> metodePembayaran = new TableColumn<>("Metode Pembayaran");
        metodePembayaran.setCellValueFactory
                (cellData -> cellData.getValue().getMetodePembayaran().namaMetodeProperty());
        TableColumn<Order, Integer> idItem = new TableColumn<>("ID Item");
        idItem.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getItem().getIdItem()));
        TableColumn<Order, String> namaItem = new TableColumn<>("Nama Item");
        namaItem.setCellValueFactory
                (cellData -> cellData.getValue().getItem().namaItemProperty());
        TableColumn<Order, Integer> idCustomer = new TableColumn<>("ID Customer");
        idCustomer.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getCustomer().getIdCustomer()));
        TableColumn<Order, String> namaCustomer = new TableColumn<>("Nama Customer");
        namaCustomer.setCellValueFactory
                (cellData -> cellData.getValue().getCustomer().namaCustomerProperty());
        TableColumn<Order, Integer> idPromo = new TableColumn<>("ID Promo");
        idPromo.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getPromoOrder().getIdPromo()));
        TableColumn<Order, String> namaPromo = new TableColumn<>("Nama Promo");
        namaPromo.setCellValueFactory
                (cellData -> cellData.getValue().getPromoOrder().namaPromoProperty());
        TableColumn<Order, Integer> idDriver = new TableColumn<>("ID Driver");
        idDriver.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getDriver().getIdDriver()));
        TableColumn<Order, String> namaDriver = new TableColumn<>("Nama Driver");
        namaDriver.setCellValueFactory
                (cellData -> cellData.getValue().getDriver().namaDriverProperty());

        // Adjust column width
        idOrder.setMinWidth(60);
        service.setMinWidth(60);
        ongkosKirim.setMinWidth(60);
        totalHarga.setMinWidth(60);
        tglOrder.setMinWidth(60);
        lamaPenyelesaian.setMinWidth(60);
        metodePembayaran.setMinWidth(60);
        idItem.setMinWidth(60);
        namaItem.setMinWidth(60);
        idCustomer.setMinWidth(60);
        namaCustomer.setMinWidth(60);
        idPromo.setMinWidth(60);
        namaPromo.setMinWidth(60);
        idDriver.setMinWidth(60);
        namaDriver.setMinWidth(60);

        // Add all columns
        orderTableView.getColumns().clear();
        orderTableView.getColumns().add(idOrder);
        orderTableView.getColumns().add(service);
        orderTableView.getColumns().add(ongkosKirim);
        orderTableView.getColumns().add(totalHarga);
        orderTableView.getColumns().add(tglOrder);
        orderTableView.getColumns().add(lamaPenyelesaian);
        orderTableView.getColumns().add(metodePembayaran);
        orderTableView.getColumns().add(idItem);
        orderTableView.getColumns().add(namaItem);
        orderTableView.getColumns().add(idCustomer);
        orderTableView.getColumns().add(namaCustomer);
        orderTableView.getColumns().add(idPromo);
        orderTableView.getColumns().add(namaPromo);
        orderTableView.getColumns().add(idDriver);
        orderTableView.getColumns().add(namaDriver);

        orderTableView.setItems(SharedData.getInstance().getOrderTable());

        SharedData.getInstance().refreshOrderTable();
    }

    @FXML
    public void onAdd() throws IOException {
        Stage popupStage = new Stage();

        // Set the modality to WINDOW_MODAL and specify the main stage as the owner
        popupStage.initOwner(orderTableView.getScene().getWindow());
        popupStage.initModality(Modality.WINDOW_MODAL);

        // Adjust stage size
        popupStage.setWidth(665);
        popupStage.setHeight(535);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/projectpbo/main/order-view.fxml"
        ));
        Parent root = loader.load();
        Scene popupScene = new Scene(root);
        OrderController orderController = loader.getController();
        orderController.setOrderScene(popupScene);
        orderController.setOrderStage(popupStage);
        popupStage.setScene(popupScene);
        popupStage.setTitle("Add Order");
        popupStage.show();
    }

    @FXML
    public void onEdit() throws IOException {
        if (orderTableView.getSelectionModel().getSelectedItem() != null) {
            Order order = orderTableView.getSelectionModel().getSelectedItem();
            Stage popupStage = new Stage();

            // Set the modality to WINDOW_MODAL and specify the main stage as the owner
            popupStage.initOwner(orderTableView.getScene().getWindow());
            popupStage.initModality(Modality.WINDOW_MODAL);

            // Adjust stage size
            popupStage.setWidth(665);
            popupStage.setHeight(535);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/projectpbo/main/order-view.fxml"
            ));
            Parent root = loader.load();
            Scene popupScene = new Scene(root);
            OrderController orderController = loader.getController();
            orderController.setOrderScene(popupScene);
            orderController.setOrderStage(popupStage);
            orderController.setEdit(true);
            orderController.setEditableOrder(order);
            orderController.loadEditData();
            popupStage.setScene(popupScene);
            popupStage.setTitle("Edit Order");
            popupStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("No data selected!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void onDelete() {
        if (orderTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to delete?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                OrderDAO.deleteOrder(orderTableView.getSelectionModel().getSelectedItem());
                SharedData.getInstance().refreshOrderTable();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText("Data successfully deleted!");
                alert2.getButtonTypes().setAll(ButtonType.OK);
                alert2.showAndWait();
            }
        }
    }
}
