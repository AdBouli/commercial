package com.example.aboulineau.commercial.Core;

import android.database.sqlite.SQLiteDatabase;

import com.example.aboulineau.commercial.Core.SQLite;

/**
 * Created by aboulineau on 30/05/2016.
 */
public class Database
{

    protected SQLiteDatabase DB;
    protected SQLite SQL;

    public void read()
    {
        DB = SQL.getReadableDatabase();
    }

    public int delete(String table, String primary, int id)
    {
        return DB.delete(table, primary + " = " + id, null);
    }

    public void write()
    {
        DB = SQL.getWritableDatabase();
    }

    public void close()
    {
        DB.close();
    }
}
