package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DetailOrder {
    private SimpleStringProperty kondisiItem;
    private SimpleStringProperty jenisLayanan;
    private SimpleStringProperty diskon;
    private SimpleIntegerProperty hargaSetelahDiskon;
    private SimpleStringProperty tglPengembalian;

    // Constructor
    public DetailOrder(String kondisiItem, String jenisLayanan, String diskon, int hargaSetelahDiskon,
                       String tglPengembalian) {
        this.kondisiItem = new SimpleStringProperty(kondisiItem);
        this.jenisLayanan = new SimpleStringProperty(jenisLayanan);
        this.diskon = new SimpleStringProperty(diskon);
        this.hargaSetelahDiskon = new SimpleIntegerProperty(hargaSetelahDiskon);
        this.tglPengembalian = new SimpleStringProperty(tglPengembalian);
    }

    public DetailOrder(String kondisiItem, String jenisLayanan, String tglPengembalian) {
        this.kondisiItem = new SimpleStringProperty(kondisiItem);
        this.jenisLayanan = new SimpleStringProperty(jenisLayanan);
        this.tglPengembalian = new SimpleStringProperty(tglPengembalian);
    }

    // Getter setters
    public String getKondisiItem() {
        return kondisiItem.get();
    }

    public SimpleStringProperty kondisiItemProperty() {
        return kondisiItem;
    }

    public void setKondisiItem(String kondisiItem) {
        this.kondisiItem.set(kondisiItem);
    }

    public String getJenisLayanan() {
        return jenisLayanan.get();
    }

    public SimpleStringProperty jenisLayananProperty() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan.set(jenisLayanan);
    }

    public String getDiskon() {
        return diskon.get();
    }

    public SimpleStringProperty diskonProperty() {
        return diskon;
    }

    public void setDiskon(String diskon) {
        this.diskon.set(diskon);
    }

    public int getHargaSetelahDiskon() {
        return hargaSetelahDiskon.get();
    }

    public SimpleIntegerProperty hargaSetelahDiskonProperty() {
        return hargaSetelahDiskon;
    }

    public void setHargaSetelahDiskon(int hargaSetelahDiskon) {
        this.hargaSetelahDiskon.set(hargaSetelahDiskon);
    }

    public String getTglPengembalian() {
        return tglPengembalian.get();
    }

    public SimpleStringProperty tglPengembalianProperty() {
        return tglPengembalian;
    }

    public void setTglPengembalian(String tglPengembalian) {
        this.tglPengembalian.set(tglPengembalian);
    }
}
