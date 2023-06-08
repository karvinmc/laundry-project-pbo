package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    private SimpleStringProperty namaItem;
    private SimpleIntegerProperty hargaItem;
    private SimpleStringProperty lamaSelesai;

    // Constructor
    public Item(String namaItem, int hargaItem, String lamaSelesai) {
        this.namaItem = new SimpleStringProperty(namaItem);
        this.hargaItem = new SimpleIntegerProperty(hargaItem);
        this.lamaSelesai = new SimpleStringProperty(lamaSelesai);
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
}
