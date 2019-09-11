package org.chilon.test_diffrent_solutions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "city";

    public DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, CITY TEXT, COUNTRY TEXT, DATE TEXT) ";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String city, String country, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CITY",city);
        contentValues.put("COUNTRY", country);
        contentValues.put("DATE", date );

        if (db.insert(TABLE_NAME, null, contentValues)==-1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getDataFromDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getFirstRowFromDb(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY ROWID ASC LIMIT 1";
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public Cursor getDataById(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = " + id;
        Cursor data = db.rawQuery(query, null);

        return data;
    }

    public boolean updateDb(String id, String city, String country, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("CITY", city);
        contentValues.put("COUNTRY", country);
        contentValues.put("DATE", date);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return true;
    }

    public boolean deleteItemFromDB(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        if (db.delete(TABLE_NAME, "ID = ?",new String[] {id})>0)
            return true;
        else
            return false;
    }

}
