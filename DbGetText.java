/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.*;
import java.sql.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Alt
 */
public class DbGetText {

    String noteText = "";
    Boolean conf = false;

    public DbGetText(String id) {

        Connection c;
        Statement stmt;

        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "pwd");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT NOTES.NOTE FROM NOTES WHERE ID =" + id + ";";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                this.conf = true;
                this.noteText = rs.getString("NOTE");
            }
            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

}
