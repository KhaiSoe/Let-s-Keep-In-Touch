package com.pursuit.letskeepintouch.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TextDatabase extends SQLiteOpenHelper {

    private static TextDatabase instance;
    private static final String DATABASE_NAME = "ScannedText.db";
    private static final String TABLE_NAME = "ScannedText";
    private static final int SCHEMA_VERSION = 1;

    public TextDatabase(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    public static synchronized TextDatabase getInstance(Context context){
        if(instance == null){
            instance = new TextDatabase(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABLE_NAME +
                        " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "date TEXT, scanned_text TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addText () {
//        Cursor cursor = getReadableDatabase().rawQuery(
//                "SELECT * FROM " + TABLE_NAME + " WHERE date = '" + fellow.getFirstName() +
//                        "' AND scanned_text = '" + fellow.getLastName() +
//                        "';", null);
//        if (cursor.getCount() == 0) {
//            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
//                    "(date, scanned_text) VALUES('" +
//                    fellow.getLastName() + "', '" +
//                    fellow.getCompany() + "');");
//        }
//        cursor.close();
    }
//
//    public List<Fellow> getFellowList() {
//        List<Fellow> fellowList = new ArrayList<>();
//        Cursor cursor = getReadableDatabase().rawQuery(
//                "SELECT * FROM " + TABLE_NAME + ";", null);
//        if(cursor != null) {
//            if(cursor.moveToFirst()) {
//                do {
//                    Fellow fellow = new Fellow(
//                            cursor.getString(cursor.getColumnIndex("last_name")),
//                            cursor.getString(cursor.getColumnIndex("first_name")),
//                            cursor.getString(cursor.getColumnIndex("company")));
//                    fellowList.add(fellow);
//                } while (cursor.moveToNext());
//            }
//        }
//        return fellowList;
//    }
}
