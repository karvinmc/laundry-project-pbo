package com.example.projectpbo.controllers.main;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class OrderController {
    @FXML
    private ChoiceBox<String> namaCustomerChoiceBox;
    @FXML
    private ChoiceBox<String> namaItem;
    @FXML
    private DatePicker tanggalOrder;
    @FXML
    private ChoiceBox<String> serviceChoiceBox;
    @FXML
    private TextField lamaSelesaiTextField;
    @FXML
    private ChoiceBox<String> promoChoiceBox;
    @FXML
    private TextField ongkosKirim;
    @FXML
    private TextField totalHarga;

    public void initialize() {

    }

    @FXML
    public void onAdd() {
    }
}
