package com.example.monogram;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class UsersDB extends SQLiteOpenHelper{

    private SQLiteDatabase database;

    private static final String TABLE_RECORD = "UsersTable";
    private static final String DATABASE_NAME = "MONOGRAM.DB";
    private static final int DATABASE_VERSION = 1;

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_TOKEN = "token";

    private static final String[] ALL_COLUMNS = {COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD, COLUMN_TOKEN};

    private static final String CREATE_DATABASE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_RECORD + "(" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COLUMN_USERNAME + " TEXT," +
            COLUMN_PASSWORD + " TEXT,"+
            COLUMN_TOKEN + " TEXT)";

    public UsersDB(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_RECORD);
        onCreate(sqLiteDatabase);
    }


    public User getUserByName(String username){
        database = getReadableDatabase();
        ContentValues values = new ContentValues();
        String[] UsernameCheck = {username};
        Cursor selectNameId = database.query(TABLE_RECORD, ALL_COLUMNS, COLUMN_USERNAME + " =?", UsernameCheck, null, null, null);
        if (selectNameId.getCount() > 0){
            selectNameId.moveToFirst();
            long id = selectNameId.getLong(selectNameId.getColumnIndexOrThrow(COLUMN_ID));
            String name = selectNameId.getString(selectNameId.getColumnIndexOrThrow(COLUMN_USERNAME));
            String password  =selectNameId.getString(selectNameId.getColumnIndexOrThrow(COLUMN_PASSWORD));
            String token = selectNameId.getString(selectNameId.getColumnIndexOrThrow(COLUMN_TOKEN));
            User user = new User(id, name, password, token);
            return user; //return an object of the user with all the information if he exists
        }
        else{
            return null; //return null if he couldn't find the name of this user
        }
    }

    @RequiresApi(api= Build.VERSION_CODES.O)
    public long insert(User user){
        database = getWritableDatabase();
        ContentValues values = new ContentValues();
        long id;
        if (getUserByName(user.getName()) == null){
            values.put(COLUMN_USERNAME, user.getName());
            values.put(COLUMN_PASSWORD, user.getPassword());
            values.put(COLUMN_TOKEN, user.getPassword());
            id = database.insert(TABLE_RECORD, null, values);
            database.close();
        }
        else{
            id = -1;
        }
        return id;
    }



}
