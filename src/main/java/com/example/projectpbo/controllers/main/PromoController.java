package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Promo;
import com.example.projectpbo.dao.PromoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PromoController {
    @FXML
    private TextField promoTextField;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;

    public boolean isValid() {
        return !promoTextField.getText().isEmpty() && startDatePicker.getValue() != null && endDatePicker.getValue() != null;
    }

    @FXML
    public void onAdd() {
        if (isValid()) {
            PromoDAO.insertPromo(new Promo(promoTextField.getText(), startDatePicker.getValue().toString(), endDatePicker.getValue().toString()));
            // Set alert
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
}
