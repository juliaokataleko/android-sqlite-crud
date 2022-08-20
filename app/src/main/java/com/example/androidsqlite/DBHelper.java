package com.example.androidsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int versao = 1;
    private static String nomeBanco = "ExemploDB.db";

    String[] sql = {
            "CREATE TABLE utilizadores (username TEXT PRIMARY KEY, name TEXT, email TEXT, password TEXT, phone TEXT, address TEXT);",
            "INSERT INTO utilizadores ('username', 'name', 'password') VALUES ('juliao', 'Julião Kataleko', '123456')",
            "INSERT INTO utilizadores ('username', 'name', 'password') VALUES ('isabel', 'Isabel Kataleko', '123456')",
            "INSERT INTO utilizadores ('username', 'name', 'password') VALUES ('florinda', 'Florinda Kataleko', '123456')"
    };

    public DBHelper(@Nullable Context context) {
        super(context, nomeBanco, null, versao);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i = 0; i < sql.length; i++) {
            sqLiteDatabase.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        versao++;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS utilizadores");
        onCreate(sqLiteDatabase);
    }

    //============================INSERT===================================
    public long insertUser(String name, String username, String address, String phone, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("username", username);
        values.put("address", address);
        values.put("phone", phone);
        values.put("password", password);

        return db.insert("utilizadores", null, values);
    }

    //============================UPDATE===================================
    public long updateUser(String name, String username, String address, String phone, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        // values.put("username", username);
        values.put("address", address);
        values.put("phone", phone);
        values.put("password", password);

        return db.update("utilizadores", values, "username=?", new String[]{username});
    }

    //============================DELETE===================================
    public long deleteUser(String username) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("utilizadores", "username=?", new String[]{username});
    }

    //============================FIND===================================
    public Cursor find(String username) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM utilizadores WHERE username=?", new String[]{username});
    }

    //============================SELECT ALL===================================
    public Cursor allUsers() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM utilizadores", null);
    }
}
