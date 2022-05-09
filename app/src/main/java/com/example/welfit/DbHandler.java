package com.example.welfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static String DB_NAME = "userdb";
    private static int DB_VERSION = 1;
    private static String TABLE_NAME = "users";
    private static String ID_COL = "id";
    private static String EMAIL_COL = "email";
    private static String PW_COL = "password";

    public void insertUserDetails(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(EMAIL_COL, user.getEmail());
        values.put(PW_COL, user.getPassword());
        long newRowId = db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public ArrayList<User> getUserDetails() {
        ArrayList<User> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int userId = cursor.getInt(0);
                String userEmail = cursor.getString(1);
                String userPw = cursor.getString(2);

                User newUser = new User(userId, userEmail, userPw);
                returnList.add(newUser);
            } while(cursor.moveToNext());
        }
        return returnList;
    }

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + EMAIL_COL + " TEXT,"
                + PW_COL + " TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
