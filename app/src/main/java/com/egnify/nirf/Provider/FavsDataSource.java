package com.egnify.nirf.Provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.egnify.nirf.MainScreen.CollegePojo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by janardhanyerranagu on 08/08/17.
 */

public class FavsDataSource {
    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COLLEGEID, MySQLiteHelper.COLUMN_INSTITUTENAME, MySQLiteHelper.COLUMN_CITY,
            MySQLiteHelper.COLUMN_STATE, MySQLiteHelper.COLUMN_OVERALLSCORE, MySQLiteHelper.COLUMN_RANK};

    public FavsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public CollegePojo createComment(CollegePojo college_info) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_COLLEGEID, college_info.getCollege_id());
        values.put(MySQLiteHelper.COLUMN_INSTITUTENAME, college_info.getInstitute_name());
        values.put(MySQLiteHelper.COLUMN_CITY, college_info.getCity());
        values.put(MySQLiteHelper.COLUMN_STATE, college_info.getState());
        values.put(MySQLiteHelper.COLUMN_OVERALLSCORE, college_info.getOverall_score());
        values.put(MySQLiteHelper.COLUMN_RANK, college_info.getRank());

        long insertId = database.insert(MySQLiteHelper.TABLE_COLLEGEINFO, null,
                values);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COLLEGEINFO,
                allColumns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        System.out.println("Comment added with id: " + college_info.getCollege_id());

        CollegePojo newComment = cursorToComment(cursor);
        cursor.close();
        close();
        return newComment;
    }

    public void deleteComment(CollegePojo college_info) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String id = college_info.getCollege_id();
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_COLLEGEINFO, MySQLiteHelper.COLUMN_COLLEGEID
                + " = " + "'" + id + "'", null);
        close();
    }

    public boolean isFavorite(CollegePojo college_info) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String id = college_info.getCollege_id();
        String selectString = "SELECT * FROM " + MySQLiteHelper.TABLE_COLLEGEINFO + " WHERE " + MySQLiteHelper.COLUMN_COLLEGEID + " =?";

        System.out.println("Comment deleted with id: " + id);
        Cursor cursor = database.rawQuery(selectString, new String[]{id});

        boolean hasObject = false;
        if (cursor.moveToFirst()) {
            hasObject = true;

            //region if you had multiple records to check for, use this region.

            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            //here, count is records found
            Log.d(TAG, String.format("%d records found", count));

            //endregion

        }

        cursor.close();
        close();
        //clo// Dont forget to close your cursor
        return hasObject;
    }

    public List<CollegePojo> getAllComments() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<CollegePojo> college_infos = new ArrayList<CollegePojo>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_COLLEGEINFO,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CollegePojo college_info = cursorToComment(cursor);
            college_infos.add(college_info);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        close();
        return college_infos;
    }

    private CollegePojo cursorToComment(Cursor cursor) {
        CollegePojo college_info = new CollegePojo();
        college_info.setId(cursor.getLong(0));
        college_info.setCollege_id(cursor.getString(1));
        college_info.setInstitute_name(cursor.getString(2));
        college_info.setCity(cursor.getString(3));
        college_info.setState(cursor.getString(4));
        college_info.setOverall_score(cursor.getString(5));
        college_info.setRank(Integer.parseInt(cursor.getString(6)));

        return college_info;
    }
}
