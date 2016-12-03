package com.example.bxt140930.Foodieninja.Other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.bxt140930.Foodieninja.Entities.Credential;

public class DBAdapter extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "userandpassword";
    private static String TABLE_CREDENTIAL = "credential";
    private static String KEY_ID = "username";
    private static String KEY_PASSWORD = "password";

    public DBAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CREDENTIAL_TABLE = "CREATE TABLE " + TABLE_CREDENTIAL + "("
                + KEY_ID + " TEXT PRIMARY KEY UNIQUE," + KEY_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_CREDENTIAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CREDENTIAL);
        onCreate(sqLiteDatabase);
    }

    public void addCredential(Credential credential) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, credential.getUsername());
        values.put(KEY_PASSWORD, credential.getPassword());
        db.insert(TABLE_CREDENTIAL, null, values);
        db.close();
    }

    public Boolean CheckCredentials() {
        Credential credential = new Credential();
        String selectQuery = "SELECT  * FROM " + TABLE_CREDENTIAL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                credential.setUsername(cursor.getString(0));
                credential.setPassword(cursor.getString(1));
                break;
            } while (cursor.moveToNext());
            return true;
        } else
            return false;
    }

    public void LogOut() {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_CREDENTIAL, null, null);
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserID() {
        Credential credential = new Credential();
        String selectQuery = "SELECT  * FROM " + TABLE_CREDENTIAL;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            return cursor.getString(0);
        } else
            return "";
    }
}
