package com.barelypassing.hackpsu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.Contacts.SettingsColumns.KEY;
import static org.xmlpull.v1.XmlPullParser.TEXT;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TUTOR";
    private static final int DB_VERSION =  1;
    private static final String TABLE_NAME = "TUTOR_TABLE";
    private static final String COL_CLASS = "CLASS";
    private static final String COL_NAME = "NAME";
    private static final String COL_DIGITS = "DIGITS";
    private static final String COL_MAJOR = "MAJOR";

    SQLiteDatabase mSqlDatabase;
    public DBHelper(Context context) {super(context, DB_NAME, null, DB_VERSION);}
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE" + TABLE_NAME + "(" + COL_CLASS + "TEXT PRIMARY KEY,"
            + COL_NAME + " TEXT PRIMARY KEY," + COL_DIGITS + "INTEGER PRIMARY KEY," + COL_MAJOR + " TEXT);";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTutor(String classCode, String name, String digits, String major){
        mSqlDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_DIGITS, digits);
        values.put(COL_CLASS, classCode);
        values.put(COL_MAJOR, major);

        mSqlDatabase.insert(TABLE_NAME,null,values);
        mSqlDatabase.close();
    }

}