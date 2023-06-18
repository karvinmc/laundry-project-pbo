package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MetodePembayaran {
    private SimpleStringProperty namaMetode;
    private SimpleIntegerProperty idMetodePembayaran;

    // Constructor
    public MetodePembayaran(String namaMetode) {
        this.namaMetode = new SimpleStringProperty(namaMetode);
    }

    public MetodePembayaran(int idMetodePembayaran, String namaMetode) {
        this.idMetodePembayaran = new SimpleIntegerProperty(idMetodePembayaran);
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

    public int getIdMetodePembayaran() {
        return idMetodePembayaran.get();
    }

    public SimpleIntegerProperty idMetodePembayaranProperty() {
        return idMetodePembayaran;
    }

    public void setIdMetodePembayaran(int idMetodePembayaran) {
        this.idMetodePembayaran.set(idMetodePembayaran);
    }
}
