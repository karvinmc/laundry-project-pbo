package com.example.projectpbo.beans;

import com.example.projectpbo.dao.KategoriDAO;
import com.example.projectpbo.dao.PromoDAO;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

public class DetailPromo {
    private SimpleDoubleProperty diskonPromo;
    private SimpleIntegerProperty idDetailPromo;
    private Promo promo;
    private Kategori kategori;

    // Constructor
    public DetailPromo(double diskonPromo, int idPromo, int idKategori) {
        this.diskonPromo = new SimpleDoubleProperty(diskonPromo);
        this.promo = getPromo(idPromo);
        this.kategori = getKategori(idKategori);
    }

    public DetailPromo(int idPromo, int idKategori) {
        this.promo = getPromo(idPromo);
        this.kategori = getKategori(idKategori);
    }

    public DetailPromo(int idDetailPromo, double diskonPromo, int idPromo, int idKategori) {
        this.idDetailPromo = new SimpleIntegerProperty(idDetailPromo);
        this.diskonPromo = new SimpleDoubleProperty(diskonPromo);
        this.promo = getPromo(idPromo);
        this.kategori = getKategori(idKategori);
    }


    // Getter setters
    public double getDiskonPromo() {
        return diskonPromo.get();
    }

    public SimpleDoubleProperty diskonPromoProperty() {
        return diskonPromo;
    }

    public void setDiskonPromo(double diskonPromo) {
        this.diskonPromo.set(diskonPromo);
    }

    public int getIdDetailPromo() {
        return idDetailPromo.get();
    }

    public SimpleIntegerProperty idDetailPromoProperty() {
        return idDetailPromo;
    }

    public void setIdDetailPromo(int idDetailPromo) {
        this.idDetailPromo.set(idDetailPromo);
    }

    public Promo getPromo() {
        return promo;
    }

    public void setPromo(Promo promo) {
        this.promo = promo;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    // Methods
    private Promo getPromo(int idPromo) {
        ArrayList<Promo> promoList = PromoDAO.getAllPromo();
        for (Promo p : promoList) {
            if (p.getIdPromo() == idPromo) {
                return p;
            }
        }
        return null;
    }

    private Kategori getKategori(int idKategori) {
        ArrayList<Kategori> kategoriList = KategoriDAO.getAllKategori();
        for (Kategori k : kategoriList) {
            if (k.getIdKategori() == idKategori) {
                return k;
            }
        }
        return null;
    }
}
