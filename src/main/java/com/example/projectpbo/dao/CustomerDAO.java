package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAO {
    private static final String TABLE_NAME = "customer_table";

    public static ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> customerList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id_customer"),
                        rs.getString("nama_customer"),
                        rs.getString("alamat_customer"),
                        rs.getInt("no_telp_customer"),
                        rs.getInt("id_radius")
                );
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return customerList;
    }

    public static void insertCustomer(Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(nama_customer, alamat_customer, no_telp_customer, id_radius) values(?, ?, ?, ?)");
            ps.setString(1, customer.getNamaCustomer());
            ps.setString(2, customer.getAlamatCustomer());
            ps.setInt(3, customer.getNoTelpCustomer());
            ps.setInt(4, customer.getRadiusCustomer().getIdRadius());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updateCustomer(Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `nama_customer` = ?, SET `alamat_customer` = ?, SET `no_telp_customer` = ?, SET `id_radius` = ?"
                    + " WHERE `id_customer` = ?");
            ps.setString(1, customer.getNamaCustomer());
            ps.setString(2, customer.getAlamatCustomer());
            ps.setInt(3, customer.getNoTelpCustomer());
            ps.setInt(4, customer.getRadiusCustomer().getIdRadius());
            ps.setInt(5, customer.getIdCustomer());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteCustomer(Customer customer) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_customer` = ?");
            ps.setInt(1, customer.getIdCustomer());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
