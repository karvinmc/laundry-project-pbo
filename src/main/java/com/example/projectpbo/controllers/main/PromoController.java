package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.Promo;
import com.example.projectpbo.dao.CustomerDAO;
import com.example.projectpbo.dao.PromoDAO;
import com.example.projectpbo.shared.SharedData;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class PromoController {
    @FXML
    private TextField promoTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private TableView<Promo> promoTableView;

    public void initialize() {
        // Initialize columns
        TableColumn<Promo, Integer> id = new TableColumn<>("ID");
        id.setCellValueFactory(cellData ->
                Bindings.createObjectBinding(() -> cellData.getValue().getIdPromo()));
        TableColumn<Promo, String> nama = new TableColumn<>("Nama");
        nama.setCellValueFactory
                (cellData -> cellData.getValue().namaPromoProperty());
        TableColumn<Promo, String> tglMulai = new TableColumn<>("Tanggal Mulai");
        tglMulai.setCellValueFactory
                (cellData -> cellData.getValue().startDatePromoProperty());
        TableColumn<Promo, String> tglBerakhir = new TableColumn<>("Tanggal Berakhir");
        tglBerakhir.setCellValueFactory
                (cellData -> cellData.getValue().endDatePromoProperty());

        // Adjust width
        id.setMinWidth(60);
        nama.setMinWidth(60);
        nama.setMinWidth(60);
        tglMulai.setMinWidth(60);
        tglBerakhir.setMinWidth(60);

        // Add all columns
        promoTableView.getColumns().clear();
        promoTableView.getColumns().add(id);
        promoTableView.getColumns().add(nama);
        promoTableView.getColumns().add(tglMulai);
        promoTableView.getColumns().add(tglBerakhir);

        // Get the selected items
        promoTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                getSelectedData(newSelection);
            }
        });

        promoTableView.setItems(SharedData.getInstance().getPromoTable());
        SharedData.getInstance().refreshPromoTable();
    }

    public boolean isValid() {
        return !promoTextField.getText().isEmpty() && startDatePicker.getValue() != null && endDatePicker.getValue() != null;
    }

    public void clearInputs() {
        promoTextField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }

    public void getSelectedData(Promo selectedPromo) {
        promoTextField.setText(selectedPromo.getNamaPromo());
        startDatePicker.setValue(LocalDate.parse(selectedPromo.getStartDatePromo()));
        endDatePicker.setValue(LocalDate.parse(selectedPromo.getEndDatePromo()));
    }

    @FXML
    public void onSave() {
        if (isValid()) {
            if (promoTableView.getSelectionModel().getSelectedItem() == null) {
                PromoDAO.insertPromo(new Promo(promoTextField.getText(), startDatePicker.getValue().toString(), endDatePicker.getValue().toString()));
            } else {
                Promo selectedPromo = promoTableView.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Do you want to save?");
                alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
                alert.showAndWait();

                if (alert.getResult() == ButtonType.YES) {
                    selectedPromo.setNamaPromo(promoTextField.getText());
                    selectedPromo.setStartDatePromo(String.valueOf(startDatePicker.getValue()));
                    selectedPromo.setEndDatePromo(String.valueOf(endDatePicker.getValue()));
                    PromoDAO.updatePromo(selectedPromo);
                }
            }

            // Set alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText("Data successfully saved!");
            alert.getButtonTypes().setAll(ButtonType.OK);
            alert.showAndWait();

            clearInputs();
            SharedData.getInstance().refreshPromoChoiceBox();
            SharedData.getInstance().refreshPromoTable();
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
        promoTableView.getSelectionModel().clearSelection();
        clearInputs();
    }

    @FXML
    public void onDelete() {
        if (promoTableView.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to delete?");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                PromoDAO.deletePromo(promoTableView.getSelectionModel().getSelectedItem());
                SharedData.getInstance().refreshPromoTable();

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Information");
                alert2.setHeaderText("Data successfully deleted!");
                alert2.getButtonTypes().setAll(ButtonType.OK);
                alert2.showAndWait();
            }
        }
    }
}
