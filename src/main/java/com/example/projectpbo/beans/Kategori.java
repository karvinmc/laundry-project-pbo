package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Kategori {
    private SimpleStringProperty namaKategori;
    private SimpleIntegerProperty idKategori;

    // Constructor
    public Kategori(String namaKategori) {
        this.namaKategori = new SimpleStringProperty(namaKategori);
    }

    public Kategori(int idKategori, String namaKategori) {
        this.idKategori = new SimpleIntegerProperty(idKategori);
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

    public int getIdKategori() {
        return idKategori.get();
    }

    public SimpleIntegerProperty idKategoriProperty() {
        return idKategori;
    }

    public void setIdKategori(int idKategori) {
        this.idKategori.set(idKategori);
    }
}
