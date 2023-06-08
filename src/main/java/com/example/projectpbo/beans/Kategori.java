package com.example.projectpbo.beans;

import javafx.beans.property.SimpleStringProperty;

public class Kategori {
    private SimpleStringProperty namaKategori;

    // Constructor
    public Kategori(String namaKategori) {
        this.namaKategori = new SimpleStringProperty(namaKategori);
    }

    // Getter setters
    public String getNamaKategori() {
        return namaKategori.get();
    }

    public SimpleStringProperty namaKategoriProperty() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori.set(namaKategori);
    }
}
