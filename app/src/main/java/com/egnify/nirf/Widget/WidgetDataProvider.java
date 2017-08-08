package com.egnify.nirf.Widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.egnify.nirf.MainScreen.college_pojo;
import com.egnify.nirf.Provider.FavsDataSource;
import com.egnify.nirf.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * WidgetDataProvider acts as the adapter for the collection view widget,
 * providing RemoteViews to the widget in the getViewAt method.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<college_pojo> mCollection = new ArrayList<>();
    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDataSetChanged() {
        try {
            initData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        college_pojo college_info = mCollection.get(position);
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                R.layout.widget_view);
        //String url = app_url.image_url + college_info.getCollege_id() + ".png";


        view.setTextViewText(R.id.college_id, college_info.getCollege_id());
        view.setTextViewText(R.id.name, college_info.getInstitute_name());
        view.setTextViewText(R.id.location, college_info.getCity());
        view.setTextViewText(R.id.all_india, String.valueOf(college_info.getRank()));
        view.setTextViewText(R.id.overall_score, college_info.getOverall_score());

        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() throws SQLException {
        mCollection.clear();
        FavsDataSource dataSource = new FavsDataSource(mContext);
        dataSource.open();
        mCollection = dataSource.getAllComments();
        dataSource.close();

    }

}
