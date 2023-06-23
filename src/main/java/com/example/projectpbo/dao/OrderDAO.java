package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Order;
import com.example.projectpbo.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;

public class OrderDAO {
    private static final String TABLE_NAME = "order_table";

    public static ArrayList<Order> getAllOrder() {
        ArrayList<Order> orderList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("id_order"),
                        rs.getDate("tanggal_order").toLocalDate(),
                        rs.getInt("total_harga"),
                        rs.getInt("ongkos_kirim_delivery"),
                        rs.getInt("id_item"),
                        rs.getInt("id_customer"),
                        rs.getInt("id_promo"),
                        rs.getInt("id_driver"),
                        rs.getInt("id_metode_pembayaran"),
                        rs.getString("lama_penyelesaian"),
                        rs.getString("service")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return orderList;
    }

    public static void insertOrder(Order order) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(total_harga, ongkos_kirim_delivery, id_customer, id_promo, id_driver, " +
                    "id_metode_pembayaran, tanggal_order, id_item, lama_penyelesaian, service) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, order.getTotalHarga());
            ps.setInt(2, order.getOngkosKirimDelivery());
            ps.setInt(3, order.getCustomer().getIdCustomer());
            ps.setInt(4, order.getPromoOrder().getIdPromo());
            ps.setInt(5, order.getDriver().getIdDriver());
            ps.setInt(6, order.getMetodePembayaran().getIdMetodePembayaran());
            ps.setDate(7, Date.valueOf(order.getTanggalOrder()));
            ps.setInt(8, order.getItem().getIdItem());
            ps.setString(9, order.getLamaPeyelesaian());
            ps.setString(10, order.getService());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updateOrder(Order order) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `total_harga` = ?, `ongkos_kirim_delivery` = ?, `id_customer` = ?, `id_promo` = ?, " +
                    "`id_driver` = ?, `id_metode_pembayaran` = ?, `tanggal_order` = ?, `id_item` = ?, `lama_penyelesaian` = ?, `service` = ?"
                    + " WHERE `id_order` = ?");
            ps.setInt(1 , order.getTotalHarga());
            ps.setInt(2, order.getOngkosKirimDelivery());
            ps.setInt(3, order.getCustomer().getIdCustomer());
            ps.setInt(4, order.getPromoOrder().getIdPromo());
            ps.setInt(5, order.getDriver().getIdDriver());
            ps.setInt(6, order.getMetodePembayaran().getIdMetodePembayaran());
            ps.setDate(7, Date.valueOf(order.getTanggalOrder()));
            ps.setInt(8, order.getItem().getIdItem());
            ps.setString(9, order.getLamaPeyelesaian());
            ps.setString(10, order.getService());
            ps.setInt(11, order.getIdOrder());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void setMetodePembayaran(Order order) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `id_metode_pembayaran` = ?"
                    + " WHERE `id_order` = ?");
            ps.setInt(1, order.getMetodePembayaran().getIdMetodePembayaran());
            ps.setInt(2, order.getIdOrder());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteOrder(Order order) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_order` = ?");
            ps.setInt(1, order.getIdOrder());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
