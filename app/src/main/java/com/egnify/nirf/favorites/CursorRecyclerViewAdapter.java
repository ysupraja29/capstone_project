package com.egnify.nirf.favorites;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.egnify.nirf.Provider.MySQLiteHelper;
import com.egnify.nirf.R;

/**
 * Created by janardhanyerranagu on 11/08/17.
 */

public class CursorRecyclerViewAdapter extends RecyclerView.Adapter<CursorRecyclerViewAdapter.ViewHolder> {
    Cursor dataCursor;
    Context context;

    public CursorRecyclerViewAdapter(Activity mContext, Cursor cursor) {
        dataCursor = cursor;
        context = mContext;
    }

    @Override
    public CursorRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cardview = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.widget_view, parent, false);
        return new ViewHolder(cardview);
    }

    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dataCursor.moveToPosition(position);

        String college_id = dataCursor.getString(dataCursor.getColumnIndex(MySQLiteHelper.COLUMN_COLLEGEID));
        String intitute = dataCursor.getString(dataCursor.getColumnIndex(MySQLiteHelper.COLUMN_INSTITUTENAME));
        String city = dataCursor.getString(dataCursor.getColumnIndex(MySQLiteHelper.COLUMN_CITY));
        String rank = dataCursor.getString(dataCursor.getColumnIndex(MySQLiteHelper.COLUMN_RANK));
        String overallscore = dataCursor.getString(dataCursor.getColumnIndex(MySQLiteHelper.COLUMN_OVERALLSCORE));

        holder.college_id.setText(college_id);
        holder.name.setText(intitute);
        holder.location.setText(city);
        holder.all_india.setText(rank);
        holder.overall_score.setText(overallscore);

    }

    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView college_id;
        public TextView name;
        public TextView location;
        public TextView all_india;
        public TextView overall_score;

        public ViewHolder(View v) {
            super(v);
            college_id = (TextView) v.findViewById(R.id.college_id);
            name = (TextView) v.findViewById(R.id.name);
            location = (TextView) v.findViewById(R.id.location);

            overall_score = (TextView) v.findViewById(R.id.overall_score);
            all_india = (TextView) v.findViewById(R.id.all_india);
        }
    }
}