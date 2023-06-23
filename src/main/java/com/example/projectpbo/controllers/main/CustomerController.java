package com.example.projectpbo.controllers.main;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CustomerController {
    // TODO: RadioButton, onAdd masih belum
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

    public void initialize() {
    }

    public Boolean isValid() {
        return !namaTextField.getText().isBlank() && !alamatTextField.getText().isBlank() &&
                !noTelpTextField.getText().isBlank();
    }

    @FXML
    public void onAdd() {
        if (isValid()) {

        } else {

        }
    }
}
