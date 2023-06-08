package com.example.projectpbo.beans;

import javafx.beans.property.SimpleStringProperty;

public class DetailPromo {
    private SimpleStringProperty diskonPromo;

    // Constructor
    public DetailPromo(String diskonPromo) {
        this.diskonPromo = new SimpleStringProperty(diskonPromo);
    }

    // Getter setters
    public String getDiskonPromo() {
        return diskonPromo.get();
    }

    public SimpleStringProperty diskonPromoProperty() {
        return diskonPromo;
    }

    public void setDiskonPromo(String diskonPromo) {
        this.diskonPromo.set(diskonPromo);
    }
}
