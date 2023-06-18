package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Driver {
    private SimpleStringProperty namaDriver;
    private SimpleIntegerProperty noTelpDriver;
    private SimpleIntegerProperty idDriver;


    // Constructor
    public Driver(String namaDriver, int noTelpDriver) {
        this.namaDriver = new SimpleStringProperty(namaDriver);
        this.noTelpDriver = new SimpleIntegerProperty(noTelpDriver);
    }

    public Driver(int idDriver, String namaDriver, int noTelpDriver) {
        this.idDriver = new SimpleIntegerProperty(idDriver);
        this.namaDriver = new SimpleStringProperty(namaDriver);
        this.noTelpDriver = new SimpleIntegerProperty(noTelpDriver);
    }

    // Getter setters
    public String getNamaDriver() {
        return namaDriver.get();
    }

    public SimpleStringProperty namaDriverProperty() {
        return namaDriver;
    }

    public void setNamaDriver(String namaDriver) {
        this.namaDriver.set(namaDriver);
    }

    public int getNoTelpDriver() {
        return noTelpDriver.get();
    }

    public SimpleIntegerProperty noTelpDriverProperty() {
        return noTelpDriver;
    }

    public void setNoTelpDriver(int noTelpDriver) {
        this.noTelpDriver.set(noTelpDriver);
    }

    public int getIdDriver() {
        return idDriver.get();
    }

    public SimpleIntegerProperty idDriverProperty() {
        return idDriver;
    }

    public void setIdDriver(int idDriver) {
        this.idDriver.set(idDriver);
    }
}
