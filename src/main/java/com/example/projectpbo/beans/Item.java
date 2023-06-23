package com.example.projectpbo.beans;

import com.example.projectpbo.dao.KategoriDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Item {
    private SimpleStringProperty namaItem;
    private SimpleIntegerProperty hargaItem;
    private SimpleIntegerProperty idItem;
    private Kategori kategori;

    // Constructor
    public Item(String namaItem, int hargaItem, int idKategori) {
        this.namaItem = new SimpleStringProperty(namaItem);
        this.hargaItem = new SimpleIntegerProperty(hargaItem);
        this.kategori = getKategoriItem(idKategori);
    }

    public Item(int idItem, String namaItem, int hargaItem, int idKategori) {
        this.idItem = new SimpleIntegerProperty(idItem);
        this.namaItem = new SimpleStringProperty(namaItem);
        this.hargaItem = new SimpleIntegerProperty(hargaItem);
        this.kategori = getKategoriItem(idKategori);
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

    public int getIdItem() {
        return idItem.get();
    }

    public SimpleIntegerProperty idItemProperty() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem.set(idItem);
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public void setKategori(int idKategori) {
        for (Kategori k : KategoriDAO.getAllKategori()) {
            if (k.getIdKategori() == idKategori) {
                this.kategori = k;
            }
        }
    }

    // Methods
    private Kategori getKategoriItem(int idKategori) {
        for (Kategori k : KategoriDAO.getAllKategori())
            if (k.getIdKategori() == idKategori) {
                return k;
            }
        return null;
    }
}
