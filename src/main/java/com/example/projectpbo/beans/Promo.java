package com.example.projectpbo.beans;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Promo {
    private SimpleStringProperty namaPromo;
    private SimpleStringProperty startDatePromo;
    private SimpleStringProperty endDatePromo;
    private SimpleIntegerProperty idPromo;
    
    // Constructor
    public Promo(String namaPromo, String startDate, String endDate) {
        this.namaPromo = new SimpleStringProperty(namaPromo);
        this.startDatePromo = new SimpleStringProperty(startDate);
        this.endDatePromo = new SimpleStringProperty(endDate);
    }

    public Promo(int idPromo, String namaPromo, String startDate, String endDate) {
        this.idPromo = new SimpleIntegerProperty(idPromo);
        this.namaPromo = new SimpleStringProperty(namaPromo);
        this.startDatePromo = new SimpleStringProperty(startDate);
        this.endDatePromo = new SimpleStringProperty(endDate);
    }

    // Getter setters
    public String getNamaPromo() {
        return namaPromo.get();
    }

    public SimpleStringProperty namaPromoProperty() {
        return namaPromo;
    }

    public void setNamaPromo(String namaPromo) {
        this.namaPromo.set(namaPromo);
    }

    public String getStartDatePromo() {
        return startDatePromo.get();
    }

    public SimpleStringProperty startDatePromoProperty() {
        return startDatePromo;
    }

    public void setStartDatePromo(String startDatePromo) {
        this.startDatePromo.set(startDatePromo);
    }

    public String getEndDatePromo() {
        return endDatePromo.get();
    }

    public SimpleStringProperty endDatePromoProperty() {
        return endDatePromo;
    }

    public void setEndDatePromo(String endDatePromo) {
        this.endDatePromo.set(endDatePromo);
    }

    public int getIdPromo() {
        return idPromo.get();
    }

    public SimpleIntegerProperty idPromoProperty() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo.set(idPromo);
    }
}
