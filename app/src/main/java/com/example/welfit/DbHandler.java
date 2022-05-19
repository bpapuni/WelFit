package com.example.welfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {
    private static String DB_NAME = "userdb";
    private static int DB_VERSION = 1;
    private static String USERS_TABLE = "users";
    private static String RESERVATIONS_TABLE = "reservations";
    private static String ID_COL = "id";
    private static String FIRST_NAME_COL = "firstname";
    private static String LAST_NAME_COL = "lastname";
    private static String EMAIL_COL = "email";
    private static String PW_COL = "password";
    private static String LOGGED_COL = "false";
    private static String USER_ID_COL = "userId";
    private static String CLASS_NAME_COL = "classname";
    private static String CLASS_DATE_COL = "classdate";
    private static String CLASS_TIME_COL = "classtime";

    public void insertUserDetails(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FIRST_NAME_COL, user.getFirstName());
        values.put(LAST_NAME_COL, user.getLastName());
        values.put(EMAIL_COL, user.getEmail());
        values.put(PW_COL, user.getPassword());
        values.put(LOGGED_COL, user.isLoggedIn());
        long newRowId = db.insert(USERS_TABLE, null, values);
        db.close();
    }

    public void insertReservationDetails(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_ID_COL, reservation.getUserId());
        values.put(CLASS_NAME_COL, reservation.getClassName());
        values.put(CLASS_DATE_COL, reservation.getClassDate().toString());
        values.put(CLASS_TIME_COL, reservation.getClassTime().toString());
        long newRowId = db.insert(RESERVATIONS_TABLE, null, values);
        db.close();
    }

    public ArrayList<User> getUserDetails() {
        ArrayList<User> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE;
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

    public ArrayList<Reservation> getReservationDetails(User user) {
        ArrayList<Reservation> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + RESERVATIONS_TABLE + " WHERE " + USER_ID_COL + " = " + user.getId();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int userId = cursor.getInt(1);
                String className = cursor.getString(2);
                String classDate = cursor.getString(3);
                String classTime = cursor.getString(4);

                Reservation reservation = new Reservation(id, userId, className, classDate, classTime);
                returnList.add(reservation);
            } while(cursor.moveToNext());
        }
        return returnList;
    }

    public User getLoggedInUser() {
        User loggedInUser = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + USERS_TABLE + " WHERE " + LOGGED_COL + " = 'true'";
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
        int count = db.update(USERS_TABLE, values, ID_COL + " = ?", new String[] { Integer.toString(user.getId()) });
        return count;
    }

    public int updateReservationDetails(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLASS_DATE_COL, reservation.getClassDate());
        values.put(CLASS_TIME_COL, reservation.getClassTime());
        int count = db.update(RESERVATIONS_TABLE, values, ID_COL + " = ?", new String[] { Integer.toString(reservation.getId()) });
        return count;
    }

    public void deleteUser(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(USERS_TABLE, ID_COL + " = ?", new String[] {id});
        db.close();
    }

    public void deleteReservation(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(RESERVATIONS_TABLE, ID_COL + " = ?", new String[] {Integer.toString(id)});
        db.close();
    }

    public DbHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.e("DB", "Called");
        // Create Users table
        String query = "CREATE TABLE " + USERS_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + PW_COL + " TEXT,"
                + LOGGED_COL + " TEXT )";
        db.execSQL(query);

        query = "INSERT INTO " + USERS_TABLE + " VALUES ( -1, 'Admin', 'Istrator', 'admin@gmail.com', 'admin1', 'false' )";
        db.execSQL(query);

        // Create Reservations table
        query = "CREATE TABLE " + RESERVATIONS_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_ID_COL + " INTEGER,"
                + CLASS_NAME_COL + " TEXT,"
                + CLASS_DATE_COL + " TEXT,"
                + CLASS_TIME_COL + " TEXT )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + RESERVATIONS_TABLE);
        onCreate(db);
    }

    public void deleteTables() {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM " + USERS_TABLE);
        db.execSQL("DELETE FROM " + RESERVATIONS_TABLE);
    }
}
