package com.example.projectpbo.dao;

import com.example.projectpbo.beans.Account;
import com.example.projectpbo.beans.Radius;
import com.example.projectpbo.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RadiusDAO {
    private static final String TABLE_NAME = "radius_table";

    public static ArrayList<Radius> getAllRadius() {
        ArrayList<Radius> radiusList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBUtils.createConnection();
            ps = con.prepareStatement("SELECT * FROM "
                    + TABLE_NAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                Radius radius = new Radius(
                        rs.getInt("min_radius"),
                        rs.getInt("max_radius"),
                        rs.getInt("harga")
                );
                radiusList.add(radius);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(ps, rs);
            DBUtils.closeConnection(con);
        }
        return radiusList;
    }
}
