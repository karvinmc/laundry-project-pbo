package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.MetodePembayaran;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetodePembayaranDAO {
    private static final String TABLE_NAME = "metode_pembayaran_table";

    public static ArrayList<MetodePembayaran> getAllMetodePembayaran() {
        ArrayList<MetodePembayaran> metodePembayaranList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                MetodePembayaran metodePembayaran = new MetodePembayaran(
                        rs.getInt("id_metode_pembayaran"),
                        rs.getString("nama_metode_pembayaran")
                );
                metodePembayaranList.add(metodePembayaran);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return metodePembayaranList;
    }
}
