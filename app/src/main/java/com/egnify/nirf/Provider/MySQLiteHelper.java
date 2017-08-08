package com.egnify.nirf.Provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by janardhanyerranagu on 08/08/17.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_COLLEGEINFO = "collegeinfo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_COLLEGEID = "collegeid";
    public static final String COLUMN_INSTITUTENAME = "intitudename";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_OVERALLSCORE = "overallscore";
    public static final String COLUMN_RANK = "rank";


    private static final String DATABASE_NAME = "nirf.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_COLLEGEINFO + "( "
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_COLLEGEID + " text not null, "
            + COLUMN_INSTITUTENAME + " text not null, "
            + COLUMN_CITY + " text not null, "
            + COLUMN_STATE + " text not null, "
            + COLUMN_OVERALLSCORE + " text not null, "
            + COLUMN_RANK + " text not null "
            + ");";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLLEGEINFO);
        onCreate(db);
    }

}
