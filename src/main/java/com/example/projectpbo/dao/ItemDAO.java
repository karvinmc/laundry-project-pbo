package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Customer;
import com.example.projectpbo.beans.Item;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAO {
    private static final String TABLE_NAME = "item_table";

    public static ArrayList<Item> getAllItem() {
        ArrayList<Item> itemList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Item item = new Item(
                        rs.getString("nama_item"),
                        rs.getInt("harga_item"),
                        rs.getString("lama_selesai"),
                        rs.getInt("id_kategori")
                );
                itemList.add(item);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return itemList;
    }

    public static void insertItem(Item item) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("INSERT INTO "
                    + TABLE_NAME + "(nama_item, harga_item, lama_selesai, id_kategori) values(?, ?, ?, ?)");
            ps.setString(1, item.getNamaItem());
            ps.setInt(2, item.getHargaItem());
            ps.setString(3, item.getLamaSelesai());
            ps.setInt(4, item.getKategoriItem().getIdKategori());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void updateItem(Item item) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("UPDATE "
                    + TABLE_NAME + " SET `nama_item` = ?, SET `harga_item` = ?, SET `lama_selesai` = ?, SET `id_kategori` = ?"
                    + " WHERE `id_item` = ?");
            ps.setString(1, item.getNamaItem());
            ps.setInt(2, item.getHargaItem());
            ps.setString(3, item.getLamaSelesai());
            ps.setInt(4, item.getKategoriItem().getIdKategori());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }

    public static void deleteItem(Item item) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("DELETE FROM " + TABLE_NAME + " WHERE `id_item` = ?");
            ps.setInt(1, item.getIdItem());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps);
            DBUtils.closeConnection(con);
        }
    }
}
