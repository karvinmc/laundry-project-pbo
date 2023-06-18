package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.DetailPromo;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DetailPromoDAO {
    private static final String TABLE_NAME = "detail_promo_table";

    public static ArrayList<DetailPromo> getAllDetailPromo() {
        ArrayList<DetailPromo> detailPromoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetailPromo detailPromo = new DetailPromo(
                        rs.getDouble("diskon_promo"),
                        rs.getInt("id_promo"),
                        rs.getInt("id_kategori")
                );
                detailPromoList.add(detailPromo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return detailPromoList;
    }

    public static void insertDetailPromo(DetailPromo detailPromo) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(diskon_promo, id_promo, id_kategori) values(?, ?, ?)");
            ps.setDouble(1, detailPromo.getDiskonPromo());
            ps.setInt(2, detailPromo.getPromo().getIdPromo());
            ps.setInt(3, detailPromo.getKategori().getIdKategori());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updateDetailPromo(DetailPromo detailPromo) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `diskon_promo` = ?, SET `id_promo` = ?, SET `id_kategori` = ?"
                    + " WHERE `id_detail_promo` = ?");
            ps.setDouble(1, detailPromo.getDiskonPromo());
            ps.setInt(2, detailPromo.getPromo().getIdPromo());
            ps.setInt(3, detailPromo.getKategori().getIdKategori());
            ps.setInt(4, detailPromo.getIdDetailPromo());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteDetailPromo(DetailPromo detailPromo) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_detail_promo` = ?");
            ps.setInt(1, detailPromo.getIdDetailPromo());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
