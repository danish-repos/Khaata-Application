package com.example.khaataapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

public class KhattaDB {


    // things that can't be changed
    // name of your tables and databases etc

    private final String DATABASE_NAME = "KhattaDB";
    private final String DATABASE_TABLE = "KhattaTable";
    private final int  DATABASE_VERSION = 1;


    // things that can be changed
    // names of your columns


    public static final String ROW_ID = "_id";
    public static final String ROW_TITLE = "_title";
    public static final String ROW_DESCRIPTION = "_description";
    public static final String ROW_DATE = "_date";
    public static final String ROW_PRICE = "_price";


    public Context myContext;
    private DBHelper ourHelper;
    private SQLiteDatabase ourDatabase;

    public KhattaDB(Context context){

        myContext = context;
    }

    class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context){
            super(context,DATABASE_NAME,null,DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase database) {

            // creates all the table here as it will only run once

            String query = "CREATE TABLE "+ DATABASE_TABLE +"("
                    + ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + ROW_TITLE + " TEXT NOT NULL,"
                    + ROW_DESCRIPTION + " TEXT NOT NULL,"
                    + ROW_DATE + " TEXT NOT NULL,"
                    + ROW_PRICE + " INTEGER NOT NULL);";

            database.execSQL(query);

        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {


            // if there should be any back-up of your previous database it should be here
            // used if there is some new verion avaiable

            database.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(database);

        }
    }



    // first make a connection between our database and our app
    public void open() throws SQLException {
        ourHelper = new DBHelper(myContext);
        ourDatabase = ourHelper.getWritableDatabase();
    }

    public void close(){
        ourHelper.close();
    }

    public long addNewKhatta(String title, String description, String date, String price){

        ContentValues cv = new ContentValues();

        cv.put(ROW_TITLE,title);
        cv.put(ROW_DESCRIPTION,description);
        cv.put(ROW_DATE,date);
        cv.put(ROW_PRICE,price);

        return ourDatabase.insert(DATABASE_TABLE,null,cv);
    }

    public int deleteKhatta(String ID){
        return ourDatabase.delete(DATABASE_TABLE,ROW_ID + "=?", new String[]{ID});

    }

    public int updateKhatta (String ID, String title, String description, String date, String price){

        ContentValues cv = new ContentValues();

        cv.put(ROW_TITLE,title);
        cv.put(ROW_DESCRIPTION,description);
        cv.put(ROW_DATE,date);
        cv.put(ROW_PRICE,price);

        return ourDatabase.update(DATABASE_TABLE, cv, ROW_ID+"=?", new String[]{ID});
    }

    public List<String> getAllKhattas(){

        List<String> resultList = new ArrayList<String>();


        String[] columns = new String[]{ROW_ID, ROW_TITLE, ROW_DESCRIPTION, ROW_DATE, ROW_PRICE};
        //Cursor cursor = ourDatabase.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);
        //cursor.close();
//
//
//        if (c != null) {
//
//            int indexID = c.getColumnIndex(ROW_ID);
//            int indexTitle = c.getColumnIndex(ROW_TITLE);
//            int indexDescription = c.getColumnIndex(ROW_DESCRIPTION);
//            int indexDate = c.getColumnIndex(ROW_DATE);
//            int indexPrice = c.getColumnIndex(ROW_PRICE);
//
//
//            String result = "";
//
//            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
//
//                result = result + c.getString(indexID) + " "
//                        + c.getString(indexTitle) + " "
//                        + c.getString(indexDescription) + " "
//                        + c.getString(indexDate) + " "
//                        + c.getString(indexPrice);
//
//                resultList.add(result);
//
//
//            }
//        }
//        c.close();


        resultList.add("1 Danish Pizza 3-9-2003 500");
        resultList.add("2 Abdullah Pizza 3-9-2003 500");
        return resultList;

    }


    public void eraseAllKhattas() {
        ourDatabase.delete(DATABASE_TABLE, null, null);

    }

}
