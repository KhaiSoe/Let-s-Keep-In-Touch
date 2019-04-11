package com.pursuit.letskeepintouch.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pursuit.letskeepintouch.textdata.TextList;

import java.util.ArrayList;
import java.util.List;

public class TextDatabase extends SQLiteOpenHelper {

    private static TextDatabase instance;
    private static final String DATABASE_NAME = "ScannedText.db";
    private static final String TABLE_NAME = "ScannedText";
    private static final int SCHEMA_VERSION = 1;
    List<String> dataList;

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
                        "cropped_text TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addText (String croppedText) {
        getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                "(cropped_text) VALUES('" +
                croppedText + "');");
    }

    public List<String> getTextList() {
        List<String> textList = new ArrayList<>();
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + ";", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String croppedText =
                            cursor.getString(cursor.getColumnIndex("cropped_text"));

                    textList.add(croppedText);
                } while (cursor.moveToNext());
            }
        }
        dataList = textList;
        return textList;
    }
}
