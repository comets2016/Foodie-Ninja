package com.example.bxt140930.foodieninja;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by jxj050100 on 11/14/2016.
 */

public class SQLiteJDBCforCredential extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "userandpassword";

    // Contacts table name
    private static String TABLE_CREDENTIAL = "";

    // Contacts Table Columns names
    private static String KEY_ID = "Username";
    private static String KEY_PASSWORD = "Password";

    public SQLiteJDBCforCredential(Context context, String tableName, String userid, String password) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        TABLE_CREDENTIAL = tableName;
    }

//    public boolean createDB(String tableName)
//    {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:credential.db");
//        }catch(Exception e)
//        {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//            db = false;
//        }
//        System.out.println("Opened database successfully");
//        db = true;
//        return db;
//    }

//    public boolean createTable(String tableName) {
//        try {
//
//            stmt = c.createStatement();
//
//            String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
//                    " (ID TEXT PRIMARY KEY NOT NULL," +
//                    " PASSWORD TEXT NOT NULL)";
//
//            stmt.executeUpdate(sql);
//            stmt.close();
//            c.close();
//
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//            db = false;
//        }
//        System.out.println("Table created successfully");
//        db = true;
//        return db;
//    }
//    public void insertIntoTable(String tableName, String user, String password)
//    {
//        try{
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//            String sql = "INSERT INTO " + tableName + " (ID,PASSWORD) " +
//                    "VALUES ('" + user + "'," + "'" + password + "');";
//            stmt.executeUpdate(sql);
//            stmt.close();
//            c.commit();
//            c.close();
//        }
//        catch (Exception e)
//        {
//            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
//            System.exit(0);
//        }
//        System.out.println("Records created successfully");
//    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CREDENTIAL_TABLE = "CREATE TABLE " + TABLE_CREDENTIAL + "("
                + KEY_ID + " TEXT PRIMARY KEY," + KEY_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CREDENTIAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIAL);

        onCreate(sqLiteDatabase);
    }

    public void addCredential(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, credential.get_id()); // User's id
        values.put(KEY_PASSWORD, credential.getPassword()); // User's password

        // Inserting Row
        db.insert(TABLE_CREDENTIAL, null, values);
        db.close(); // Closing database connection
    }
}
