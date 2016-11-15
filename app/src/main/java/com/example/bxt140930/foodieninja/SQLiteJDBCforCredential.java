package com.example.bxt140930.foodieninja;

import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by jxj050100 on 11/14/2016.
 */

public class SQLiteJDBCforCredential {
    Statement stmt = null;
    Connection c = null;
    boolean db=false;
    public static void main( String args[] ) {
    }
    public boolean createDB(String tableName)
    {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:credential.db");
        }catch(Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            db = false;
        }
        System.out.println("Opened database successfully");
        db = true;
        return db;
    }
    public boolean createTable(String tableName) {
        try {

            stmt = c.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                    " (ID TEXT PRIMARY KEY NOT NULL," +
                    " PASSWORD TEXT NOT NULL)";

            stmt.executeUpdate(sql);
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
            db = false;
        }
        System.out.println("Table created successfully");
        db = true;
        return db;
    }
    public void insertIntoTable(String tableName, String user, String password)
    {
        try{
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "INSERT INTO " + tableName + " (ID,PASSWORD) " +
                    "VALUES ('" + user + "'," + "'" + password + "');";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        }
        catch (Exception e)
        {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }
}
