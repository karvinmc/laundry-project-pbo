package com.example.projectpbo.beans;

import com.example.projectpbo.dao.KategoriDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Item {
    private SimpleStringProperty namaItem;
    private SimpleIntegerProperty hargaItem;
    private SimpleStringProperty lamaSelesai;
    private SimpleIntegerProperty idItem;
    private Kategori kategoriItem;

    // Constructor
    public Item(String namaItem, int hargaItem, String lamaSelesai, int idKategori) {
        this.namaItem = new SimpleStringProperty(namaItem);
        this.hargaItem = new SimpleIntegerProperty(hargaItem);
        this.lamaSelesai = new SimpleStringProperty(lamaSelesai);
        this.kategoriItem = getKategoriItem(idKategori);
    }

    public Item(int idItem, String namaItem, int hargaItem, String lamaSelesai, int idKategori) {
        this.idItem = new SimpleIntegerProperty(idItem);
        this.namaItem = new SimpleStringProperty(namaItem);
        this.hargaItem = new SimpleIntegerProperty(hargaItem);
        this.lamaSelesai = new SimpleStringProperty(lamaSelesai);
        this.kategoriItem = getKategoriItem(idKategori);
    }


    // Getter setters
    public String getNamaItem() {
        return namaItem.get();
    }

    public SimpleStringProperty namaItemProperty() {
        return namaItem;
    }

    public void setNamaItem(String namaItem) {
        this.namaItem.set(namaItem);
    }

    public int getHargaItem() {
        return hargaItem.get();
    }

    public SimpleIntegerProperty hargaItemProperty() {
        return hargaItem;
    }

    public void setHargaItem(int hargaItem) {
        this.hargaItem.set(hargaItem);
    }

    public String getLamaSelesai() {
        return lamaSelesai.get();
    }

    public SimpleStringProperty lamaSelesaiProperty() {
        return lamaSelesai;
    }

    public void setLamaSelesai(String lamaSelesai) {
        this.lamaSelesai.set(lamaSelesai);
    }

    public int getIdItem() {
        return idItem.get();
    }

    public SimpleIntegerProperty idItemProperty() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem.set(idItem);
    }

    public Kategori getKategoriItem() {
        return kategoriItem;
    }

    public void setKategoriItem(Kategori kategoriItem) {
        this.kategoriItem = kategoriItem;
    }

    // Methods
    private Kategori getKategoriItem(int idKategori) {
        ArrayList<Kategori> kategoriList = KategoriDAO.getAllKategori();
        for (Kategori k : kategoriList) {
            if (k.getIdKategori() == idKategori) {
                return k;
            }
        }
        return null;
    }
}
