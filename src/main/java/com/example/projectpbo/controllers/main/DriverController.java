package com.example.projectpbo.controllers.main;

import com.example.projectpbo.beans.Driver;
import com.example.projectpbo.dao.DriverDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class DriverController {
    @FXML
    private TextField namaDriverTextField;
    @FXML
    private TextField noTelpTextField;

    public boolean isValid() {
        return !namaDriverTextField.getText().isBlank() && !noTelpTextField.getText().isBlank();
    }

    @FXML
    public void onAdd() {
        if (isValid()) {
            DriverDAO.insertDriver(new Driver(namaDriverTextField.getText(), Integer.parseInt(noTelpTextField.getText())));
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
