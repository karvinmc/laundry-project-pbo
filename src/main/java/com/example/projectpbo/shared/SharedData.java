package com.example.projectpbo.shared;

import com.example.projectpbo.beans.*;
import com.example.projectpbo.dao.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class SharedData {
    private static SharedData instance;
    private Account account;
    private ObservableList<String> customerObservableList;
    private ObservableList<String> driverObservableList;
    private ObservableList<String> promoObservableList;
    private ObservableList<String> itemObservableList;
    private ObservableList<String> kategoriObservableList;
    private ObservableList<String> metodeObservableList;
    private ObservableList<Order> orderTable;
    private ObservableList<Customer> customerTable;
    private ObservableList<Item> itemTable;
    private ObservableList<Promo> promoTable;
    private ObservableList<Driver> driverTable;

    // Constructor
    public SharedData() {
        this.customerObservableList = FXCollections.observableArrayList();
        this.driverObservableList = FXCollections.observableArrayList();
        this.promoObservableList = FXCollections.observableArrayList();
        this.itemObservableList = FXCollections.observableArrayList();
        this.kategoriObservableList = FXCollections.observableArrayList();
        this.orderTable = FXCollections.observableArrayList();
        this.metodeObservableList = FXCollections.observableArrayList();
        this.customerTable = FXCollections.observableArrayList();
        this.itemTable = FXCollections.observableArrayList();
        this.promoTable = FXCollections.observableArrayList();
        this.driverTable = FXCollections.observableArrayList();
    }

    // Getter setters

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public static void setInstance(SharedData instance) {
        SharedData.instance = instance;
    }

    public ObservableList<String> getCustomerObservableList() {
        return customerObservableList;
    }

    public void setCustomerObservableList(ObservableList<String> customerObservableList) {
        this.customerObservableList = customerObservableList;
    }

    public ObservableList<String> getDriverObservableList() {
        return driverObservableList;
    }

    public void setDriverObservableList(ObservableList<String> driverObservableList) {
        this.driverObservableList = driverObservableList;
    }

    public ObservableList<String> getPromoObservableList() {
        return promoObservableList;
    }

    public void setPromoObservableList(ObservableList<String> promoObservableList) {
        this.promoObservableList = promoObservableList;
    }

    public ObservableList<String> getItemObservableList() {
        return itemObservableList;
    }

    public void setItemObservableList(ObservableList<String> itemObservableList) {
        this.itemObservableList = itemObservableList;
    }

    public ObservableList<String> getKategoriObservableList() {
        return kategoriObservableList;
    }

    public void setKategoriObservableList(ObservableList<String> kategoriObservableList) {
        this.kategoriObservableList = kategoriObservableList;
    }

    public ObservableList<Order> getOrderTable() {
        return orderTable;
    }

    public void setOrderTable(ObservableList<Order> orderTable) {
        this.orderTable = orderTable;
    }

    public ObservableList<String> getMetodeObservableList() {
        return metodeObservableList;
    }

    public void setMetodeObservableList(ObservableList<String> metodeObservableList) {
        this.metodeObservableList = metodeObservableList;
    }

    public ObservableList<Customer> getCustomerTable() {
        return customerTable;
    }

    public void setCustomerTable(ObservableList<Customer> customerTable) {
        this.customerTable = customerTable;
    }

    public ObservableList<Item> getItemTable() {
        return itemTable;
    }

    public void setItemTable(ObservableList<Item> itemTable) {
        this.itemTable = itemTable;
    }

    public ObservableList<Promo> getPromoTable() {
        return promoTable;
    }

    public void setPromoTable(ObservableList<Promo> promoTable) {
        this.promoTable = promoTable;
    }

    public ObservableList<Driver> getDriverTable() {
        return driverTable;
    }

    public void setDriverTable(ObservableList<Driver> driverTable) {
        this.driverTable = driverTable;
    }

    /**
     * Use static getInstance() to return the same SharedData object without creating a new empty
     * SharedData so all other Class uses the same populated SharedData
     * @return the instance of SharedData
     */
    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public void updateLoggedAccount() throws SQLException {
        this.account = AccountDAO.getLoggedAccount();
    }

    public void refreshCustomerChoiceBox() {
        ArrayList<Customer> customerList = CustomerDAO.getAllCustomer();
        ArrayList<String> customerOptions = new ArrayList<>();

        for (Customer c : customerList) {
            customerOptions.add(c.getIdCustomer() + " - " + c.getNamaCustomer());
        }

        customerObservableList.setAll(customerOptions);
    }

    public void refreshDriverChoiceBox() {
        ArrayList<Driver> driverList = DriverDAO.getAllDriver();
        ArrayList<String> customerOptions = new ArrayList<>();

        for (Driver d : driverList) {
            customerOptions.add(d.getIdDriver() + " - " + d.getNamaDriver());
        }

        driverObservableList.setAll(customerOptions);
    }

    public void refreshPromoChoiceBox() {
        ArrayList<Promo> promoList = PromoDAO.getAllPromo();
        ArrayList<String> promoOptions = new ArrayList<>();

        for (Promo p : promoList) {
            promoOptions.add(p.getIdPromo() + " - " + p.getNamaPromo());
        }

        promoObservableList.setAll(promoOptions);
    }

    public void refreshItemChoiceBox() {
        ArrayList<Item> itemList = ItemDAO.getAllItem();
        ArrayList<String> itemOptions = new ArrayList<>();

        for (Item i : itemList) {
            itemOptions.add(i.getIdItem() + " - " + i.getNamaItem());
        }

        itemObservableList.setAll(itemOptions);
    }

    public void refreshKategoriChoiceBox() {
        ArrayList<Kategori> kategoriList = KategoriDAO.getAllKategori();
        ArrayList<String> promoOptions = new ArrayList<>();

        for (Kategori k : kategoriList) {
            promoOptions.add(k.getIdKategori() + " - " + k.getNamaKategori());
        }

        kategoriObservableList.setAll(promoOptions);
    }

    public void refreshMetodeChoiceBox() {
        ArrayList<String> metodeOptions = new ArrayList<>();

        for (MetodePembayaran mp : MetodePembayaranDAO.getAllMetodePembayaran()) {
            metodeOptions.add(mp.getIdMetodePembayaran() + " - " + mp.getNamaMetode());
        }

        metodeObservableList.setAll(metodeOptions);
    }

    public void refreshOrderTable() {
        orderTable.setAll(OrderDAO.getAllOrder());
    }

    public void refreshCustomerTable() {
        customerTable.setAll(CustomerDAO.getAllCustomer());
    }

    public void refreshItemTable() {
        itemTable.setAll(ItemDAO.getAllItem());
    }

    public void refreshPromoTable() {
        promoTable.setAll(PromoDAO.getAllPromo());
    }

    public void refreshDriverTable() {
        driverTable.setAll(DriverDAO.getAllDriver());
    }
}
