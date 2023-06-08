package com.example.projectpbo.beans;

import javafx.beans.property.SimpleStringProperty;

public class MetodePembayaran {
    private SimpleStringProperty namaMetode;

    // Constructor
    public MetodePembayaran(String namaMetode) {
        this.namaMetode = new SimpleStringProperty(namaMetode);
    }

    // Getter setters
    public String getNamaMetode() {
        return namaMetode.get();
    }

    public SimpleStringProperty namaMetodeProperty() {
        return namaMetode;
    }

    public void setNamaMetode(String namaMetode) {
        this.namaMetode.set(namaMetode);
    }
}
