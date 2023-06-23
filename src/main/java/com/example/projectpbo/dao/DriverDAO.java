package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.Driver;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DriverDAO {
    private static final String TABLE_NAME = "driver_table";

    public static ArrayList<Driver> getAllDriver() {
        ArrayList<Driver> driverList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver(
                        rs.getInt("id_driver"),
                        rs.getString("nama_driver"),
                        rs.getInt("no_telp_driver")
                );
                driverList.add(driver);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return driverList;
    }

    public static void insertDriver(Driver driver) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(nama_driver, no_telp_driver) values(?, ?)");
            ps.setString(1, driver.getNamaDriver());
            ps.setInt(2, driver.getNoTelpDriver());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updateDriver(Driver driver) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `nama_driver` = ?, SET `no_telp_driver` = ?"
                    + " WHERE `id_driver` = ?");
            ps.setString(1, driver.getNamaDriver());
            ps.setInt(2, driver.getNoTelpDriver());
            ps.setInt(3, driver.getIdDriver());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteDriver(Driver driver) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_driver` = ?");
            ps.setInt(1, driver.getIdDriver());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
