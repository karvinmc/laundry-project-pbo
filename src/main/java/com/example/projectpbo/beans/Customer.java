package com.example.projectpbo.beans;

import com.example.projectpbo.dao.RadiusDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Customer {
    private SimpleStringProperty namaCustomer;
    private SimpleStringProperty alamatCustomer;
    private SimpleIntegerProperty noTelpCustomer;
    private SimpleIntegerProperty idCustomer;
    private Radius radiusCustomer;

    // Constructor
    public Customer(String namaCustomer, String alamatCustomer, int noTelpCustomer, int idRadius) {
        this.namaCustomer = new SimpleStringProperty(namaCustomer);
        this.alamatCustomer = new SimpleStringProperty(alamatCustomer);
        this.noTelpCustomer = new SimpleIntegerProperty(noTelpCustomer);
        this.radiusCustomer = getRadiusCustomer(idRadius);
    }

    public Customer(int idCustomer, String namaCustomer, String alamatCustomer, int noTelpCustomer, int idRadius) {
        this.idCustomer = new SimpleIntegerProperty(idCustomer);
        this.namaCustomer = new SimpleStringProperty(namaCustomer);
        this.alamatCustomer = new SimpleStringProperty(alamatCustomer);
        this.noTelpCustomer = new SimpleIntegerProperty(noTelpCustomer);
        this.radiusCustomer = getRadiusCustomer(idRadius);
    }

    // Getter setters

    public int getIdCustomer() {
        return idCustomer.get();
    }

    public SimpleIntegerProperty idCustomerProperty() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer.set(idCustomer);
    }

    public String getNamaCustomer() {
        return namaCustomer.get();
    }

    public SimpleStringProperty namaCustomerProperty() {
        return namaCustomer;
    }

    public void setNamaCustomer(String namaCustomer) {
        this.namaCustomer.set(namaCustomer);
    }

    public String getAlamatCustomer() {
        return alamatCustomer.get();
    }

    public SimpleStringProperty alamatCustomerProperty() {
        return alamatCustomer;
    }

    public void setAlamatCustomer(String alamatCustomer) {
        this.alamatCustomer.set(alamatCustomer);
    }

    public int getNoTelpCustomer() {
        return noTelpCustomer.get();
    }

    public SimpleIntegerProperty noTelpCustomerProperty() {
        return noTelpCustomer;
    }

    public void setNoTelpCustomer(int noTelpCustomer) {
        this.noTelpCustomer.set(noTelpCustomer);
    }

    public Radius getRadiusCustomer() {
        return radiusCustomer;
    }

    public void setRadiusCustomer(Radius radiusCustomer) {
        this.radiusCustomer = radiusCustomer;
    }

    // Methods
    private Radius getRadiusCustomer(int idRadius) {
        ArrayList<Radius> radiusList = RadiusDAO.getAllRadius();
        for (Radius r : radiusList) {
            if (r.getIdRadius() == idRadius) {
                return r;
            }
        }
        return null;
    }
}
