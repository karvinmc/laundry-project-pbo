package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.Kategori;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KategoriDAO {
    private static final String TABLE_NAME = "kategori_table";

    public static ArrayList<Kategori> getAllKategori() {
        ArrayList<Kategori> kategoriList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Kategori kategori = new Kategori(
                        rs.getInt("id_kategori"),
                        rs.getString("nama_kategori")
                );
                kategoriList.add(kategori);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return kategoriList;
    }

    public static void insertKategori(Kategori kategori) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(nama_kategori) values(?)");
            ps.setString(1, kategori.getNamaKategori());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updateCustomer(Kategori kategori) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `nama_kategori` = ?"
                    + " WHERE `id_kategori` = ?");
            ps.setString(1, kategori.getNamaKategori());
            ps.setInt(2, kategori.getIdKategori());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteKategori(Kategori kategori) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_kategori` = ?");
            ps.setInt(1, kategori.getIdKategori());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
