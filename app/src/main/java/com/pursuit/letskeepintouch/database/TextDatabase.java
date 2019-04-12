package com.pursuit.letskeepintouch.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TextDatabase extends SQLiteOpenHelper implements DatabaseFields{

    private static TextDatabase instance;

    private static final int SCHEMA_VERSION = 1;


    public TextDatabase(Context context) {

        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
        Log.i("Created a DB", "DB");
    }

    public static void createInstance(Context context){
        instance=new TextDatabase(context);

    }

    public static TextDatabase getInstance(){
      //  if(instance == null){
      //      instance = new TextDatabase(context);
      //  }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String createString = "CREATE TABLE " + TABLE_NAME + " (" + ID_COLUMN_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  CROPPED_COLUMN_NAME + " TEXT NOT NULL );";
      sqLiteDatabase.execSQL(createString);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addText (String croppedText) {
        String insertString = "INSERT INTO " + TABLE_NAME +"  ("+CROPPED_COLUMN_NAME+") VALUES('" +  croppedText +" ');";
        Log.i("Inserting: ", insertString);

        TextDatabase td = getInstance();
        td.getWritableDatabase().execSQL(insertString);
//        getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
//              "  ("+CROPPED_COLUMN_NAME+") VALUES('" +
//                croppedText + "');");
        getTextList();
    }


    public static List<String> getTextList() {
        List<String> textList = new ArrayList<>();

        Cursor cursor = getInstance().getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + ";", null);

        if (cursor != null) {

            int rowCount=cursor.getCount();

            Log.i("getting Row Count: ", Integer.toString(rowCount));

            if (cursor.moveToFirst()) {
                do {
                    String croppedText =
                            cursor.getString(cursor.getColumnIndex(CROPPED_COLUMN_NAME));

                    Log.i("cropped text?", croppedText);

                    textList.add(croppedText);
                } while (cursor.moveToNext());
            }
        } else {

            Log.e("null?", "No cursor");

        }
        return textList;
    }

    public void delete(String tableName, String s) {
    }
}
