package com.exemple.rafael.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 1/30/18.
 */

public class ClienteOpenHelper extends SQLiteOpenHelper {

    public ClienteOpenHelper(Context context){
    super(context,"CLIENTE",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(ScriptDDL.getCreateTableCliente());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
