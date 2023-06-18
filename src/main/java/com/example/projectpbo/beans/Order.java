package com.example.projectpbo.beans;

import com.example.projectpbo.dao.CustomerDAO;
import com.example.projectpbo.dao.DriverDAO;
import com.example.projectpbo.dao.MetodePembayaranDAO;
import com.example.projectpbo.dao.PromoDAO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private SimpleStringProperty tanggalOrder;
    private SimpleIntegerProperty totalHarga;
    private SimpleIntegerProperty downPayment;
    private SimpleBooleanProperty isPickup;
    private SimpleBooleanProperty isDelivery;
    private SimpleIntegerProperty ongkosKirimPickup; // ???
    private SimpleIntegerProperty ongkosKirimDelivery;
    private SimpleIntegerProperty idOrder;
    private Customer customer;
    private Promo promoOrder;
    private Driver driver;
    private MetodePembayaran metodePembayaran;

    // Constructor
    public Order(LocalDate tanggalOrder, int totalHarga, int downPayment, boolean isPickUp, boolean isDelivery,
                 int ongkosKirimPickup, int ongkosKirimDelivery, int idCustomer, int idPromo, int idDriver, int idMetodePembayaran) {
        this.tanggalOrder = new SimpleStringProperty(tanggalOrder.toString());
        this.totalHarga = new SimpleIntegerProperty(totalHarga);
        this.downPayment = new SimpleIntegerProperty(downPayment);
        this.isPickup = new SimpleBooleanProperty(isPickUp);
        this.isDelivery = new SimpleBooleanProperty(isDelivery);
        this.ongkosKirimPickup = new SimpleIntegerProperty(ongkosKirimPickup);
        this.ongkosKirimDelivery = new SimpleIntegerProperty(ongkosKirimDelivery);
        this.customer = getCustomer(idCustomer);
        this.promoOrder = getPromoOrder(idPromo);
        this.driver = getDriver(idDriver);
        this.metodePembayaran = getMetodePembayaran(idMetodePembayaran);
    }

    public Order(int idOrder, LocalDate tanggalOrder, int totalHarga, int downPayment, boolean isPickUp, boolean isDelivery,
                 int ongkosKirimPickup, int ongkosKirimDelivery, int idCustomer, int idPromo, int idDriver, int idMetodePembayaran) {
        this.idOrder = new SimpleIntegerProperty(idOrder);
        this.tanggalOrder = new SimpleStringProperty(tanggalOrder.toString());
        this.totalHarga = new SimpleIntegerProperty(totalHarga);
        this.downPayment = new SimpleIntegerProperty(downPayment);
        this.isPickup = new SimpleBooleanProperty(isPickUp);
        this.isDelivery = new SimpleBooleanProperty(isDelivery);
        this.ongkosKirimPickup = new SimpleIntegerProperty(ongkosKirimPickup);
        this.ongkosKirimDelivery = new SimpleIntegerProperty(ongkosKirimDelivery);
        this.customer = getCustomer(idCustomer);
        this.promoOrder = getPromoOrder(idPromo);
        this.driver = getDriver(idDriver);
        this.metodePembayaran = getMetodePembayaran(idMetodePembayaran);
    }

    // Getter setters
    public String getTanggalOrder() {
        return tanggalOrder.get();
    }

    public SimpleStringProperty tanggalOrderProperty() {
        return tanggalOrder;
    }

    public void setTanggalOrder(String tanggalOrder) {
        this.tanggalOrder.set(tanggalOrder);
    }

    public int getTotalHarga() {
        return totalHarga.get();
    }

    public SimpleIntegerProperty totalHargaProperty() {
        return totalHarga;
    }

    public void setTotalHarga(int totalHarga) {
        this.totalHarga.set(totalHarga);
    }

    public int getDownPayment() {
        return downPayment.get();
    }

    public SimpleIntegerProperty downPaymentProperty() {
        return downPayment;
    }

    public void setDownPayment(int downPayment) {
        this.downPayment.set(downPayment);
    }

    public boolean isIsPickup() {
        return isPickup.get();
    }

    public SimpleBooleanProperty isPickupProperty() {
        return isPickup;
    }

    public void setIsPickup(boolean isPickup) {
        this.isPickup.set(isPickup);
    }

    public boolean isIsDelivery() {
        return isDelivery.get();
    }

    public SimpleBooleanProperty isDeliveryProperty() {
        return isDelivery;
    }

    public void setIsDelivery(boolean isDelivery) {
        this.isDelivery.set(isDelivery);
    }

    public int getOngkosKirimPickup() {
        return ongkosKirimPickup.get();
    }

    public SimpleIntegerProperty ongkosKirimPickupProperty() {
        return ongkosKirimPickup;
    }

    public void setOngkosKirimPickup(int ongkosKirimPickup) {
        this.ongkosKirimPickup.set(ongkosKirimPickup);
    }

    public int getOngkosKirimDelivery() {
        return ongkosKirimDelivery.get();
    }

    public SimpleIntegerProperty ongkosKirimDeliveryProperty() {
        return ongkosKirimDelivery;
    }

    public void setOngkosKirimDelivery(int ongkosKirimDelivery) {
        this.ongkosKirimDelivery.set(ongkosKirimDelivery);
    }

    public int getIdOrder() {
        return idOrder.get();
    }

    public SimpleIntegerProperty idOrderProperty() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder.set(idOrder);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Promo getPromoOrder() {
        return promoOrder;
    }

    public void setPromoOrder(Promo promoOrder) {
        this.promoOrder = promoOrder;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public MetodePembayaran getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(MetodePembayaran metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    // Methods
    private Customer getCustomer(int idCustomer) {
        ArrayList<Customer> customerList = CustomerDAO.getAllCustomer();
        for (Customer c : customerList) {
            if (c.getIdCustomer() == idCustomer) {
                return c;
            }
        }
        return null;
    }

    private Promo getPromoOrder(int idPromo) {
        ArrayList<Promo> promoList = PromoDAO.getAllPromo();
        for (Promo p : promoList) {
            if (p.getIdPromo() == idPromo) {
                return p;
            }
        }
        return null;
    }

    private Driver getDriver(int idDriver) {
        ArrayList<Driver> driverList = DriverDAO.getAllDriver();
        for (Driver d : driverList) {
            if (d.getIdDriver() == idDriver) {
                return d;
            }
        }
        return null;
    }

    private MetodePembayaran getMetodePembayaran(int idMetodePembayaran) {
        ArrayList<MetodePembayaran> metodePembayaranList = MetodePembayaranDAO.getAllMetodePembayaran();
        for (MetodePembayaran mp : metodePembayaranList) {
            if (mp.getIdMetodePembayaran() == idMetodePembayaran) {
                return mp;
            }
        }
        return null;
    }
}
