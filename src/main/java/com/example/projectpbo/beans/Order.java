package com.example.projectpbo.beans;

import com.example.projectpbo.dao.*;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private SimpleStringProperty tanggalOrder;
    private SimpleIntegerProperty totalHarga;
    private SimpleIntegerProperty ongkosKirimDelivery;
    private SimpleIntegerProperty idOrder;
    private Item item;
    private Customer customer;
    private Promo promoOrder;
    private Driver driver;
    private MetodePembayaran metodePembayaran;
    private SimpleStringProperty lamaPeyelesaian;

    // Constructor
    public Order(LocalDate tanggalOrder, int totalHarga, int ongkosKirimDelivery, int idCustomer, int idItem,
                 int idPromo, int idDriver, int idMetodePembayaran, String lamaPenyelesaian) {
        this.tanggalOrder = new SimpleStringProperty(tanggalOrder.toString());
        this.totalHarga = new SimpleIntegerProperty(totalHarga);
        this.ongkosKirimDelivery = new SimpleIntegerProperty(ongkosKirimDelivery);
        this.item = getItem(idItem);
        this.customer = getCustomer(idCustomer);
        this.promoOrder = getPromoOrder(idPromo);
        this.driver = getDriver(idDriver);
        this.metodePembayaran = getMetodePembayaran(idMetodePembayaran);
        this.lamaPeyelesaian = new SimpleStringProperty(lamaPenyelesaian);
    }

    public Order(int idOrder, LocalDate tanggalOrder, int totalHarga, int ongkosKirimDelivery, int idCustomer, int idItem,
                 int idPromo, int idDriver, int idMetodePembayaran, String lamaPeyelesaian) {
        this.idOrder = new SimpleIntegerProperty(idOrder);
        this.tanggalOrder = new SimpleStringProperty(tanggalOrder.toString());
        this.totalHarga = new SimpleIntegerProperty(totalHarga);
        this.ongkosKirimDelivery = new SimpleIntegerProperty(ongkosKirimDelivery);
        this.item = getItem(idItem);
        this.customer = getCustomer(idCustomer);
        this.promoOrder = getPromoOrder(idPromo);
        this.driver = getDriver(idDriver);
        this.metodePembayaran = getMetodePembayaran(idMetodePembayaran);
        this.lamaPeyelesaian = new SimpleStringProperty(lamaPeyelesaian);
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getLamaPeyelesaian() {
        return lamaPeyelesaian.get();
    }

    public SimpleStringProperty lamaPeyelesaianProperty() {
        return lamaPeyelesaian;
    }

    public void setLamaPeyelesaian(String lamaPeyelesaian) {
        this.lamaPeyelesaian.set(lamaPeyelesaian);
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
    private Item getItem(int idItem) {
        ArrayList<Item> itemList = ItemDAO.getAllItem();
        for (Item i : itemList) {
            if (i.getIdItem() == idItem) {
                return i;
            }
        }
        return null;
    }
}
