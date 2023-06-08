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

    public DetailOrder(SimpleStringProperty kondisiItem, SimpleStringProperty jenisLayanan, SimpleStringProperty tglPengembalian) {
        this.kondisiItem = kondisiItem;
        this.jenisLayanan = jenisLayanan;
        this.tglPengembalian = tglPengembalian;
    }
}
