package com.example.projectpbo.beans;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private SimpleStringProperty namaCustomer;
    private SimpleStringProperty alamatCustomer;
    private SimpleStringProperty noTelpCustomer;

    // Constructor
    public Customer(String namaCustomer, String alamatCustomer, String noTelpCustomer) {
        this.namaCustomer = new SimpleStringProperty(namaCustomer);
        this.alamatCustomer = new SimpleStringProperty(alamatCustomer);
        this.noTelpCustomer = new SimpleStringProperty(noTelpCustomer);
    }

    // Getter setters
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

    public String getNoTelpCustomer() {
        return noTelpCustomer.get();
    }

    public SimpleStringProperty noTelpCustomerProperty() {
        return noTelpCustomer;
    }

    public void setNoTelpCustomer(String noTelpCustomer) {
        this.noTelpCustomer.set(noTelpCustomer);
    }
}
