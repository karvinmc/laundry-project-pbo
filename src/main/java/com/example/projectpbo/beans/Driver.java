package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Driver {
    private SimpleStringProperty namaDriver;
    private SimpleStringProperty noTelpDriver;

    // Constructor
    public Driver(String namaDriver, String noTelpDriver) {
        this.namaDriver = new SimpleStringProperty(namaDriver);
        this.noTelpDriver = new SimpleStringProperty(noTelpDriver);
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

    public String getNoTelpDriver() {
        return noTelpDriver.get();
    }

    public SimpleStringProperty noTelpDriverProperty() {
        return noTelpDriver;
    }

    public void setNoTelpDriver(String noTelpDriver) {
        this.noTelpDriver.set(noTelpDriver);
    }
}
