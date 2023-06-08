package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Radius {
    private SimpleStringProperty minRadius;
    private SimpleStringProperty maxRadius;
    private SimpleIntegerProperty harga;

    // Constructor
    public Radius(String minRadius, String maxRadius, int harga) {
        this.minRadius = new SimpleStringProperty(minRadius);
        this.maxRadius = new SimpleStringProperty(maxRadius);
        this.harga = new SimpleIntegerProperty(harga);
    }

    public Radius(String minRadius, int harga) {
        this.minRadius = new SimpleStringProperty(minRadius);
        this.harga = new SimpleIntegerProperty(harga);
    }

    // Getter setters
    public String getMinRadius() {
        return minRadius.get();
    }

    public SimpleStringProperty minRadiusProperty() {
        return minRadius;
    }

    public void setMinRadius(String minRadius) {
        this.minRadius.set(minRadius);
    }

    public String getMaxRadius() {
        return maxRadius.get();
    }

    public SimpleStringProperty maxRadiusProperty() {
        return maxRadius;
    }

    public void setMaxRadius(String maxRadius) {
        this.maxRadius.set(maxRadius);
    }

    public int getHarga() {
        return harga.get();
    }

    public SimpleIntegerProperty hargaProperty() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga.set(harga);
    }
}
