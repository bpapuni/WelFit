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
    private static String FIRST_NAME_COL = "firstname";
    private static String LAST_NAME_COL = "lastname";
    private static String EMAIL_COL = "email";
    private static String PW_COL = "password";
    private static String LOGGED_COL = "false";

    public void insertUserDetails(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, user.getFirstName());
        values.put(LAST_NAME_COL, user.getLastName());
        values.put(EMAIL_COL, user.getEmail());
        values.put(PW_COL, user.getPassword());
        values.put(LOGGED_COL, user.isLoggedIn());
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
                String userFirstName = cursor.getString(1);
                String userLastName = cursor.getString(2);
                String userEmail = cursor.getString(3);
                String userPw = cursor.getString(4);
                String userIsLoggedIn = cursor.getString(5);

                User newUser = new User(userId, userFirstName, userLastName, userEmail, userPw, userIsLoggedIn);
                returnList.add(newUser);
            } while(cursor.moveToNext());
        }
        return returnList;
    }

    public User getLoggedInUser() {
        User loggedInUser = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + LOGGED_COL + " = 'true'";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int userId = cursor.getInt(0);
                String userFirstName = cursor.getString(1);
                String userLastName = cursor.getString(2);
                String userEmail = cursor.getString(3);
                String userPw = cursor.getString(4);
                String userIsLoggedIn = cursor.getString(5);

                loggedInUser = new User(userId, userFirstName, userLastName, userEmail, userPw, userIsLoggedIn);
            } while(cursor.moveToNext());
        }
        return loggedInUser;
    }

    public int updateUserDetails(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, user.getFirstName());
        values.put(LAST_NAME_COL, user.getLastName());
        values.put(EMAIL_COL, user.getEmail());
        values.put(PW_COL, user.getPassword());
        values.put(LOGGED_COL, user.isLoggedIn());
        int count = db.update(TABLE_NAME, values, ID_COL + " = ?", new String[] { Integer.toString(user.getId()) });
        return count;
    }

    public void deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, ID_COL + " = ?", new String[] {id});
        db.close();
    }

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PW_COL + " TEXT,"
                + LOGGED_COL + " TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
