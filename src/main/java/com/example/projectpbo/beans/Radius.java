package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Radius {
    private SimpleIntegerProperty minRadius;
    private SimpleIntegerProperty maxRadius;
    private SimpleIntegerProperty harga;
    private SimpleIntegerProperty idRadius;

    // Constructor
    public Radius(int minRadius, int maxRadius, int harga) {
        this.minRadius = new SimpleIntegerProperty(minRadius);
        this.maxRadius = new SimpleIntegerProperty(maxRadius);
        this.harga = new SimpleIntegerProperty(harga);
    }

    public Radius(int minRadius, int harga) {
        this.minRadius = new SimpleIntegerProperty(minRadius);
        this.harga = new SimpleIntegerProperty(harga);
    }

    public Radius(int idRadius ,int minRadius, int maxRadius, int harga) {
        this.idRadius = new SimpleIntegerProperty(idRadius);
        this.minRadius = new SimpleIntegerProperty(minRadius);
        this.maxRadius = new SimpleIntegerProperty(maxRadius);
        this.harga = new SimpleIntegerProperty(harga);
    }

    // Getter setters
    public int getMinRadius() {
        return minRadius.get();
    }

    public SimpleIntegerProperty minRadiusProperty() {
        return minRadius;
    }

    public void setMinRadius(int minRadius) {
        this.minRadius.set(minRadius);
    }

    public int getMaxRadius() {
        return maxRadius.get();
    }

    public SimpleIntegerProperty maxRadiusProperty() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
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

    public int getIdRadius() {
        return idRadius.get();
    }

    public SimpleIntegerProperty idRadiusProperty() {
        return idRadius;
    }

    public void setIdRadius(int idRadius) {
        this.idRadius.set(idRadius);
    }
}
