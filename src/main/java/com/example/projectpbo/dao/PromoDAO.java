package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.Promo;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromoDAO {
    private static final String TABLE_NAME = "promo_table";

    public static ArrayList<Promo> getAllPromo() {
        ArrayList<Promo> promoList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Promo promo = new Promo(
                        rs.getInt("id_promo"),
                        rs.getString("nama_promo"),
                        rs.getString("start_date_promo"),
                        rs.getString("end_date_promo")
                );
                promoList.add(promo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return promoList;
    }

    public static void insertPromo(Promo promo) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(nama_promo, start_date_promo, end_date_promo) values(?, ?, ?)");
            ps.setString(1, promo.getNamaPromo());
            ps.setString(2, promo.getStartDatePromo());
            ps.setString(3, promo.getEndDatePromo());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updatePromo(Promo promo) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `nama_promo` = ?, SET `start_date_promo` = ?, SET `end_date_promo` = ?"
                    + " WHERE `id_promo` = ?");
            ps.setString(1, promo.getNamaPromo());
            ps.setString(2, promo.getStartDatePromo());
            ps.setString(3, promo.getEndDatePromo());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deletePromo(Promo promo) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_promo` = ?");
            ps.setInt(1, promo.getIdPromo());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
