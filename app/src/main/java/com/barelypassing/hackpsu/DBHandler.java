package com.barelypassing.hackpsu;

/**
 * Created by Shannon on 11/5/2017.
 */

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "TutorsInfo";
    // Contacts table name
    private static final String TABLE_TUTORS = "Tutors";
    // Tutors Table Columns names
    private static final String KEY_DIGITS = "Digits";
    private static final String KEY_NAME = "Name";
    private static final String KEY_CLASS = "Class Name";
    private static final String MAJOR = "Major";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_TUTORS + "("
            + KEY_DIGITS + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
            + KEY_CLASS + " TEXT" + MAJOR + "TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TUTORS);
        // Creating tables again
        onCreate(db);
    }

    // Adding new Tutor
    public void addTutor(Tutor tutor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, tutor.getName()); // Tutor Name
        values.put(KEY_DIGITS, tutor.getDigits()); // Tutor Phone Number
        values.put(KEY_CLASS, tutor.getClassCode());
        values.put(MAJOR, tutor.getMajor());

        // Inserting Row
        db.insert(TABLE_TUTORS, null, values);
        db.close(); // Closing database connection
    }
    // Getting one Tutor
    public Tutor getTutor(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TUTORS, new String[]{KEY_DIGITS,
                KEY_NAME, KEY_CLASS}, KEY_CLASS + "=?",
                new String[]{String.valueOf(name)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Tutor contact = new Tutor(cursor.getString(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        // return shop
        return contact;
    }
    // Getting All Classes
    public List<String> getAllClasses() {
        List<String> classList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TUTORS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Tutor tutor = new Tutor();
                tutor.setName(cursor.getString(0));
                tutor.setDigits(cursor.getString(1));
                tutor.setClassCode(cursor.getString(2));
                tutor.setMajor(cursor.getString(3));

                // Adding contact to list
                classList.add(tutor.getClassCode());
            } while (cursor.moveToNext());
        }

        // return contact list
        return classList;
    }

    // Getting tutors Count
    public int getTutorsCount() {
        String countQuery = "SELECT * FROM " + TABLE_TUTORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Deleting a shop
    public void deleteTutor(Tutor tutor) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TUTORS, KEY_NAME + " = ?",
            new String[] { String.valueOf(tutor.getName()) });
        db.close();
    }
}
