package com.egnify.nirf;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.egnify.nirf.MainScreen.CollegePojo;
import com.egnify.nirf.MainScreen.GoPojo;
import com.egnify.nirf.MainScreen.OiPojo;
import com.egnify.nirf.MainScreen.PerceptionPojo;
import com.egnify.nirf.MainScreen.RpcPojo;
import com.egnify.nirf.MainScreen.SubMetricPojo;
import com.egnify.nirf.MainScreen.TlrPojo;
import com.egnify.nirf.Provider.FavsDataSource;
import com.egnify.nirf.Provider.MySQLiteHelper;
import com.egnify.nirf.Widget.CollectionWidget;
import com.egnify.nirf.favorites.FavsContentProvider;
import com.egnify.nirf.utils.AppUrl;
import com.egnify.nirf.utils.MyCustomTextViewBold;
import com.egnify.nirf.utils.MyCustomTextViewMbold;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_CITY;
import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_COLLEGEID;
import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_ID;
import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_INSTITUTENAME;
import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_OVERALLSCORE;
import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_RANK;
import static com.egnify.nirf.Provider.MySQLiteHelper.COLUMN_STATE;

public class CollegeDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int[] scale_chart = {Color.rgb(155, 200, 106), Color.rgb(90, 110, 176), Color.rgb(69, 168, 214), Color.rgb(251, 177, 78), Color.rgb(266, 87, 71)};
    LinearLayout all, tlr, rpc, go, oi, per;
    ImageView all_iv, tlr_iv, rpc_iv, go_iv, oi_iv, per_iv;
    MyCustomTextViewMbold all_tv, tlr_tv, rpc_tv, go_tv, oi_tv, per_tv;
    Toolbar toolbar;
    String[] scale_hex = {"#9BC86A", "#5A6EB0", "#45A8D6", "#FBB14E", "#E25747"};
    MyCustomTextViewBold all_india, state_rank, overall_score;
    //  p_MyCustomTextView_mbold metric_heading;
    int i = 0;
    private CollegePojo clg_pojo;
    private Uri todoUri;
    private FavsDataSource datasource;

    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.overall_score);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        clg_pojo = (CollegePojo) getIntent().getSerializableExtra(getString(R.string.clg_pojo));
        MyCustomTextViewMbold name = (MyCustomTextViewMbold) findViewById(R.id.name);
        MyCustomTextViewMbold college_id = (MyCustomTextViewMbold) findViewById(R.id.college_id);
        MyCustomTextViewMbold location = (MyCustomTextViewMbold) findViewById(R.id.location);
        datasource = new FavsDataSource(this);
        ImageView logo = (ImageView) findViewById(R.id.logo);
        name.setText(clg_pojo.getInstitute_name());
        college_id.setText(clg_pojo.getCollege_id());
        location.setText(clg_pojo.getCity() + "," + clg_pojo.getState());
        String url = AppUrl.image_url + clg_pojo.getCollege_id() + ".png";
        Glide.with(CollegeDetailsActivity.this).load(url).into(logo);

        Bundle extras = getIntent().getExtras();

        // check from the saved Instance
        todoUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
                .getParcelable(FavsContentProvider.CONTENT_ITEM_TYPE);

        // Or passed from the other activity
        if (extras != null) {
            todoUri = extras
                    .getParcelable(FavsContentProvider.CONTENT_ITEM_TYPE);
        }
        all_india = (MyCustomTextViewBold) findViewById(R.id.all_india);
        state_rank = (MyCustomTextViewBold) findViewById(R.id.state_rank);
        overall_score = (MyCustomTextViewBold) findViewById(R.id.overall_score);
        // metric_heading=(p_MyCustomTextView_mbold) findViewById(R.id.metric_heading);
        all = (LinearLayout) findViewById(R.id.all);
        tlr = (LinearLayout) findViewById(R.id.tlr);
        rpc = (LinearLayout) findViewById(R.id.rpc);
        go = (LinearLayout) findViewById(R.id.go);
        oi = (LinearLayout) findViewById(R.id.oi);
        per = (LinearLayout) findViewById(R.id.per);

        all_iv = (ImageView) findViewById(R.id.all_iv);
        tlr_iv = (ImageView) findViewById(R.id.tlr_iv);
        rpc_iv = (ImageView) findViewById(R.id.rpc_iv);
        go_iv = (ImageView) findViewById(R.id.go_iv);
        oi_iv = (ImageView) findViewById(R.id.oi_iv);
        per_iv = (ImageView) findViewById(R.id.per_iv);

        all_tv = (MyCustomTextViewMbold) findViewById(R.id.all_tv);
        tlr_tv = (MyCustomTextViewMbold) findViewById(R.id.tlr_tv);
        rpc_tv = (MyCustomTextViewMbold) findViewById(R.id.rpc_tv);
        go_tv = (MyCustomTextViewMbold) findViewById(R.id.go_tv);
        oi_tv = (MyCustomTextViewMbold) findViewById(R.id.oi_tv);
        per_tv = (MyCustomTextViewMbold) findViewById(R.id.per_tv);

        all.setOnClickListener(this);
        tlr.setOnClickListener(this);
        rpc.setOnClickListener(this);
        go.setOnClickListener(this);
        oi.setOnClickListener(this);
        per.setOnClickListener(this);
        setAllAdapter(clg_pojo);
        settonormal(tlr_iv, tlr_tv);
        settonormal(rpc_iv, rpc_tv);
        settonormal(go_iv, go_tv);
        settonormal(oi_iv, oi_tv);
        settoblack(all_iv, all_tv, 0);
        settonormal(per_iv, per_tv);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.all) {

            setAllAdapter(clg_pojo);
            settonormal(tlr_iv, tlr_tv);
            settonormal(rpc_iv, rpc_tv);
            settonormal(go_iv, go_tv);
            settonormal(oi_iv, oi_tv);
            settoblack(all_iv, all_tv, 0);
            settonormal(per_iv, per_tv);
        } else if (id == R.id.tlr) {
            TlrPojo tlrPojo = clg_pojo.getTlr();
            setTlrAdapter(tlrPojo);

            settonormal(all_iv, all_tv);
            settonormal(rpc_iv, rpc_tv);
            settonormal(go_iv, go_tv);
            settonormal(oi_iv, oi_tv);
            settonormal(per_iv, per_tv);
            settoblack(tlr_iv, tlr_tv, 1);
        } else if (id == R.id.rpc) {
            RpcPojo rpcPojo = clg_pojo.getRpc();
            settonormal(all_iv, all_tv);
            settonormal(tlr_iv, tlr_tv);
            settonormal(go_iv, go_tv);
            settonormal(oi_iv, oi_tv);
            settonormal(per_iv, per_tv);
            settoblack(rpc_iv, rpc_tv, 2);
            setRPCAdapter(rpcPojo);
        } else if (id == R.id.go) {
            GoPojo goPojo = clg_pojo.getGo();
            settonormal(all_iv, all_tv);
            settonormal(tlr_iv, tlr_tv);
            settonormal(rpc_iv, rpc_tv);
            settonormal(oi_iv, oi_tv);
            settonormal(per_iv, per_tv);
            settoblack(go_iv, go_tv, 3);
            setGOAdapter(goPojo);
        } else if (id == R.id.oi) {
            OiPojo oiPojo = clg_pojo.getOi();
            settonormal(all_iv, all_tv);
            settonormal(tlr_iv, tlr_tv);
            settonormal(rpc_iv, rpc_tv);
            settonormal(go_iv, go_tv);
            settonormal(per_iv, per_tv);
            settoblack(oi_iv, oi_tv, 4);
            setOIAdapter(oiPojo);
        } else if (id == R.id.per) {
            PerceptionPojo perceptionPojo = clg_pojo.getPerception();
            settonormal(all_iv, all_tv);
            settonormal(tlr_iv, tlr_tv);
            settonormal(rpc_iv, rpc_tv);
            settonormal(go_iv, go_tv);
            settonormal(oi_iv, oi_tv);
            settoblack(per_iv, per_tv, 5);
            setPerAdapter(perceptionPojo);
        }
    }

    private void setPerAdapter(PerceptionPojo perceptionPojo) {
        PerceptionPojo loc_per = perceptionPojo;
        double tlr_s = Double.parseDouble(loc_per.getPerception_score());
        int tlr_sr = Integer.parseInt(loc_per.getPerception_state_rank());
        int tlr_r = loc_per.getPerception_rank();
        all_india.setText(String.valueOf(tlr_r));
        state_rank.setText(String.valueOf(tlr_sr));
        overall_score.setText(String.valueOf(tlr_s));
        //  metric_heading.setText(Main_headings[5]);
        ArrayList<SubMetricPojo> sub_metric_poj = loc_per.getPerception_sub();
        float[] per_values = get_values(sub_metric_poj);
        String[] labels = get_labels(sub_metric_poj);
        int[] chart_colors = get_colors(sub_metric_poj);
        LinearLayout rv = (LinearLayout) findViewById(R.id.legends);
        rv.removeAllViews();
        for (int i = 0; i < labels.length; i++) {
            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sub_row = lf.inflate(R.layout.legend_row, null);
            View line = sub_row.findViewById(R.id.line);
            line.setBackgroundColor(chart_colors[i]);

            TextView metric = (TextView) sub_row.findViewById(R.id.metric);
            TextView per = (TextView) sub_row.findViewById(R.id.per);
            //   p_MyCustomTextView_bold count = (p_MyCustomTextView_bold) sub_row.findViewById(R.id.count);
            //  count.setText(optionsPojo.);
            per.setText(String.valueOf(Math.round(per_values[i])));
            metric.setText(labels[i]);
            rv.addView(sub_row);

        }

        BarChart bar_chart = (BarChart) findViewById(R.id.barchart);
        bar_chart.invalidate();
        setchart(bar_chart, per_values, labels, chart_colors);
    }

    private void setOIAdapter(OiPojo oiPojo) {
        OiPojo loc_oi = oiPojo;
        double tlr_s = Double.parseDouble(loc_oi.getOi_score());
        int tlr_sr = Integer.parseInt(loc_oi.getOi_state_rank());
        int tlr_r = loc_oi.getOi_rank();

        all_india.setText(String.valueOf(tlr_r));
        state_rank.setText(String.valueOf(tlr_sr));
        overall_score.setText(String.valueOf(tlr_s));
        //  metric_heading.setText(Main_headings[4]);
        ArrayList<SubMetricPojo> sub_metric_poj = loc_oi.getOi_sub();
        float[] per_values = get_values(sub_metric_poj);
        String[] labels = get_labels(sub_metric_poj);
        int[] chart_colors = get_colors(sub_metric_poj);
        LinearLayout rv = (LinearLayout) findViewById(R.id.legends);
        rv.removeAllViews();
        for (int i = 0; i < labels.length; i++) {
            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sub_row = lf.inflate(R.layout.legend_row, null);
            View line = sub_row.findViewById(R.id.line);
            line.setBackgroundColor(chart_colors[i]);

            TextView metric = (TextView) sub_row.findViewById(R.id.metric);
            TextView per = (TextView) sub_row.findViewById(R.id.per);
            //   p_MyCustomTextView_bold count = (p_MyCustomTextView_bold) sub_row.findViewById(R.id.count);
            //  count.setText(optionsPojo.);
            per.setText(String.valueOf(Math.round(per_values[i])));
            metric.setText(labels[i]);
            rv.addView(sub_row);

        }
        BarChart bar_chart = (BarChart) findViewById(R.id.barchart);
        bar_chart.invalidate();
        setchart(bar_chart, per_values, labels, chart_colors);
    }

    private void setGOAdapter(GoPojo goPojo) {
        GoPojo loc_go = goPojo;
        double tlr_s = Double.parseDouble(loc_go.getGo_score());
        int tlr_sr = Integer.parseInt(loc_go.getGo_state_rank());
        int tlr_r = loc_go.getGo_rank();
        // metric_heading.setText(Main_headings[3]);
        all_india.setText(String.valueOf(tlr_r));
        state_rank.setText(String.valueOf(tlr_sr));
        overall_score.setText(String.valueOf(tlr_s));
        ArrayList<SubMetricPojo> sub_metric_poj = loc_go.getGo_sub();
        float[] per_values = get_values(sub_metric_poj);
        String[] labels = get_labels(sub_metric_poj);
        int[] chart_colors = get_colors(sub_metric_poj);
        LinearLayout rv = (LinearLayout) findViewById(R.id.legends);
        rv.removeAllViews();
        for (int i = 0; i < labels.length; i++) {
            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sub_row = lf.inflate(R.layout.legend_row, null);
            View line = sub_row.findViewById(R.id.line);
            line.setBackgroundColor(chart_colors[i]);

            TextView metric = (TextView) sub_row.findViewById(R.id.metric);
            TextView per = (TextView) sub_row.findViewById(R.id.per);
            //   p_MyCustomTextView_bold count = (p_MyCustomTextView_bold) sub_row.findViewById(R.id.count);
            //  count.setText(optionsPojo.);
            per.setText(String.valueOf(Math.round(per_values[i])));
            metric.setText(labels[i]);
            rv.addView(sub_row);
        }
        BarChart bar_chart = (BarChart) findViewById(R.id.barchart);
        bar_chart.invalidate();
        setchart(bar_chart, per_values, labels, chart_colors);
    }

    private void setTlrAdapter(TlrPojo tlrPojo) {
        TlrPojo loc_tlr = tlrPojo;
        double tlr_s = Double.parseDouble(tlrPojo.getTlr_score());
        int tlr_sr = Integer.parseInt(tlrPojo.getTlr_state_rank());
        int tlr_r = tlrPojo.getTlr_rank();
        //  metric_heading.setText(Main_headings[1]);
        all_india.setText(String.valueOf(tlr_r));
        state_rank.setText(String.valueOf(tlr_sr));
        overall_score.setText(String.valueOf(tlr_s));
        ArrayList<SubMetricPojo> sub_metric_poj = loc_tlr.getTlr_sub();
        float[] per_values = get_values(sub_metric_poj);
        String[] labels = get_labels(sub_metric_poj);
        int[] chart_colors = get_colors(sub_metric_poj);
        LinearLayout rv = (LinearLayout) findViewById(R.id.legends);
        rv.removeAllViews();
        for (int i = 0; i < labels.length; i++) {
            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sub_row = lf.inflate(R.layout.legend_row, null);
            View line = sub_row.findViewById(R.id.line);
            line.setBackgroundColor(chart_colors[i]);

            TextView metric = (TextView) sub_row.findViewById(R.id.metric);
            TextView per = (TextView) sub_row.findViewById(R.id.per);
            //   p_MyCustomTextView_bold count = (p_MyCustomTextView_bold) sub_row.findViewById(R.id.count);
            //  count.setText(optionsPojo.);
            per.setText(String.valueOf(Math.round(per_values[i])));
            metric.setText(labels[i]);
            rv.addView(sub_row);

        }
        BarChart bar_chart = (BarChart) findViewById(R.id.barchart);
        bar_chart.invalidate();
        setchart(bar_chart, per_values, labels, chart_colors);

    }

    private void setRPCAdapter(RpcPojo rpcPojo) {
        RpcPojo loc_rpc = rpcPojo;
        double tlr_s = Double.parseDouble(loc_rpc.getRpc_score());
        int tlr_sr = Integer.parseInt(loc_rpc.getRpc_state_rank());
        int tlr_r = loc_rpc.getRpc_rank();
        // metric_heading.setText(Main_headings[2]);
        all_india.setText(String.valueOf(tlr_r));
        state_rank.setText(String.valueOf(tlr_sr));
        overall_score.setText(String.valueOf(tlr_s));
        ArrayList<SubMetricPojo> sub_metric_poj = loc_rpc.getRpc_sub();
        float[] per_values = get_values(sub_metric_poj);
        String[] labels = get_labels(sub_metric_poj);
        int[] chart_colors = get_colors(sub_metric_poj);
        LinearLayout rv = (LinearLayout) findViewById(R.id.legends);
        rv.removeAllViews();
        for (int i = 0; i < labels.length; i++) {
            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sub_row = lf.inflate(R.layout.legend_row, null);
            View line = sub_row.findViewById(R.id.line);
            line.setBackgroundColor(chart_colors[i]);

            TextView metric = (TextView) sub_row.findViewById(R.id.metric);
            TextView per = (TextView) sub_row.findViewById(R.id.per);
            //   p_MyCustomTextView_bold count = (p_MyCustomTextView_bold) sub_row.findViewById(R.id.count);
            //  count.setText(optionsPojo.);
            per.setText(String.valueOf(Math.round(per_values[i])));
            metric.setText(labels[i]);
            rv.addView(sub_row);

        }
        BarChart bar_chart = (BarChart) findViewById(R.id.barchart);
        bar_chart.invalidate();
        setchart(bar_chart, per_values, labels, chart_colors);

    }

    private int[] get_colors(ArrayList<SubMetricPojo> sub_metric_poj) {
        int[] per_values = new int[sub_metric_poj.size()];

        for (int i = 0; i < sub_metric_poj.size(); i++) {
            SubMetricPojo optionsPojo = sub_metric_poj.get(i);
            per_values[i] = get_hex_chart(Float.parseFloat(optionsPojo.getSub_metric_value()));
        }
        return per_values;
    }

    private String[] get_labels(ArrayList<SubMetricPojo> sub_metric_poj) {
        String[] per_values = new String[sub_metric_poj.size()];

        for (int i = 0; i < sub_metric_poj.size(); i++) {
            SubMetricPojo optionsPojo = sub_metric_poj.get(i);
            per_values[i] = optionsPojo.getSub_metric();
        }
        return per_values;
    }

    private float[] get_values(ArrayList<SubMetricPojo> sub_metric_poj) {
        float[] per_values = new float[sub_metric_poj.size()];

        for (int i = 0; i < sub_metric_poj.size(); i++) {
            SubMetricPojo optionsPojo = sub_metric_poj.get(i);
            per_values[i] = Float.parseFloat(optionsPojo.getSub_metric_value());
        }
        return per_values;
    }

    private void setAllAdapter(CollegePojo clg_pojo) {
        all_india.setText(String.valueOf(clg_pojo.getRank()));
        state_rank.setText(String.valueOf(clg_pojo.getRank()));
        overall_score.setText(String.valueOf(clg_pojo.getOverall_score()));
        //  metric_heading.setText("OverAll");
        TlrPojo loc_tlr = clg_pojo.getTlr();
        RpcPojo loc_rpc = clg_pojo.getRpc();
        GoPojo loc_go = clg_pojo.getGo();
        OiPojo loc_oi = clg_pojo.getOi();
        PerceptionPojo loc_per = clg_pojo.getPerception();

        float[] per_values = new float[5];
        String[] labels = {"TLR", "RPC", "GO", "OI", "PER"};
        int[] chart_colors = {Color.parseColor(scale_hex[0]), Color.parseColor(scale_hex[1]), Color.parseColor(scale_hex[2]), Color.parseColor(scale_hex[3]), Color.parseColor(scale_hex[4])};
        per_values[0] = Float.parseFloat(loc_tlr.getTlr_score());

        per_values[1] = Float.parseFloat(loc_rpc.getRpc_score());
        per_values[2] = Float.parseFloat(loc_go.getGo_score());
        per_values[3] = Float.parseFloat(loc_oi.getOi_score());
        per_values[4] = Float.parseFloat(loc_per.getPerception_score());
        LinearLayout rv = (LinearLayout) findViewById(R.id.legends);

        rv.removeAllViews();
        for (int i = 0; i < labels.length; i++) {
            LayoutInflater lf = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View sub_row = lf.inflate(R.layout.legend_row, null);
            View line = sub_row.findViewById(R.id.line);
            line.setBackgroundColor(chart_colors[i]);

            TextView metric = (TextView) sub_row.findViewById(R.id.metric);
            TextView per = (TextView) sub_row.findViewById(R.id.per);
            //   p_MyCustomTextView_bold count = (p_MyCustomTextView_bold) sub_row.findViewById(R.id.count);
            //  count.setText(optionsPojo.);
            per.setText(String.valueOf(Math.round(per_values[i])));
            metric.setText(labels[i]);
            rv.addView(sub_row);

        }
        BarChart bar_chart = (BarChart) findViewById(R.id.barchart);
        bar_chart.invalidate();
        setchart(bar_chart, per_values, labels, chart_colors);

    }

    private void settonormal(ImageView imageView, MyCustomTextViewMbold metric) {
        int color = Color.parseColor("#FFFFFF"); //The color u want
        imageView.setColorFilter(color);
        metric.setTextColor(getResources().getColor(R.color.white));
        metric.setTextSize(14);
        toolbar.setBackgroundColor(Color.parseColor("#F44336"));

    }

    private void settoblack(ImageView imageView, MyCustomTextViewMbold metric, int i) {
        int color = Color.parseColor("#FFFFFF");
        if (i == 0) {
            color = Color.parseColor("#FFFFFF");
            toolbar.setTitle(R.string.overall_score);
        } else if (i == 1) {
            color = Color.parseColor("#3B88E4");
            toolbar.setTitle(R.string.tlr);
        } else if (i == 2) {
            color = Color.parseColor("#FFC300");
            toolbar.setTitle(R.string.rpc);
        } else if (i == 3) {
            color = Color.parseColor("#2BDEC5");
            toolbar.setTitle(R.string.go);
        } else if (i == 4) {
            color = Color.parseColor("#FB406F");
            toolbar.setTitle(R.string.oi);
        } else if (i == 5) {
            color = Color.parseColor("#785446");
            toolbar.setTitle(R.string.per);
        }
        toolbar.setBackgroundColor(color);
        //The color u want
        imageView.setColorFilter(color);
        metric.setTextColor(color);
        metric.setTextSize(16);


    }

    private int get_hex_chart(float val) {
        int value = Math.round(val);
        int color;
        if (value <= 90) {
            if (value <= 50) {
                if (value <= 30) {
                    if (value <= 20) {
                        color = scale_chart[0];
                    } else {
                        color = scale_chart[1];
                    }
                } else {
                    color = scale_chart[2];
                }
            } else {
                color = scale_chart[3];
            }


        } else {
            color = scale_chart[4];
        }
        return color;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detials, menu);
        MenuItem item = menu.findItem(R.id.fav);
        item.setVisible(true);
        if (clg_pojo != null) {
            item.setIcon(!getfav() ? R.drawable.heart_outline : R.drawable.heart);

        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {

            case R.id.fav:
                new FavoriteActionAsyncTask(this, item).execute();

                break;


        }
        return super.onOptionsItemSelected(item);
    }

//    private void update_widget() {
//        Intent intent = new Intent(this, CollectionWidget.class);
//        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
//        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), CollectionWidget.class));
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
//        sendBroadcast(intent);
//    }

    private boolean getfav() {
        boolean issthere;

        issthere = isFavorite(clg_pojo);

        return issthere;
    }

    private boolean isFavorite(CollegePojo clg_pojo) {
        boolean isfav = true;
        String[] available = {COLUMN_ID, COLUMN_COLLEGEID, COLUMN_INSTITUTENAME, COLUMN_CITY, COLUMN_STATE, COLUMN_OVERALLSCORE, COLUMN_RANK};

//        Uri todoUri = Uri.parse(FavsContentProvider.CONTENT_URI + "/" + clg_pojo.getCollege_id());
        Cursor cursor = getContentResolver().query(todoUri, available, null, null, null);

        // Cursor c = getContentResolver().query(todoUri, null,available, null, null);
        if (cursor.getCount() == 0) {
            isfav = false;
        }
        return isfav;
    }


    private void setchart(BarChart chart, float[] bar_values, final String[] bar_labels, int[] newchartcolors) {
        if (i != 0) {
            chart.clearValues();
            chart.notifyDataSetChanged();
        }
        List<BarEntry> bar_entries = new ArrayList<>();
        for (int j = 0; j < bar_values.length; j++) {
            bar_entries.add(new BarEntry(j, bar_values[j]));
        }
        BarDataSet set1 = new BarDataSet(bar_entries, "The year 2017");
        set1.setValueTextSize(14f);
        set1.setColors(newchartcolors);


        //set1.setValueFormatter(new MyValueFormatter());
        BarData data = new BarData(set1);
        /*   if (values.length <= 3) {
            data.setBarWidth(smalbarWidth);
            chart.setViewPortOffsets(140f, 30f, 140f, 30f);
            // chart.setPadding(45,25,45,25);
        } else {
            data.setBarWidth(barWidth);
        }*/

        //data.setBarWidth(0.9f);
        chart.setData(data);

//        chart.groupBars(1980f, groupSpace, barSpace); // perform the "explicit" grouping

        chart.animateXY(1000, 1000);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.setGridBackgroundColor(128);
        // chart.setBorderColor(255);
        chart.setFitBars(true);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.setDrawBorders(false);
        chart.getLegend().setEnabled(false);

        chart.setPinchZoom(false);
        chart.setTouchEnabled(false);
        chart.getDescription().setEnabled(false);
        chart.setDoubleTapToZoomEnabled(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getXAxis().setEnabled(true);
        chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        XAxis xAxis = chart.getXAxis();
        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return bar_labels[(int) value];
            }

            // we don't draw numbers, so no decimal digits needed
            @Override
            public int getDecimalDigits() {
                return 0;
            }
        };
        xAxis.setGranularity(1f);
        //   xAxis.setValueFormatter(formatter);
        chart.invalidate();
        i++;

    }

    private void deleteComment(CollegePojo clg_pojo) {
//        Uri uri = Uri.parse(FavsContentProvider.CONTENT_URI + "/"
//                + clg_pojo.getCollege_id());
        getContentResolver().delete(todoUri, null, null);
    }

    private void createComment(CollegePojo college_info) {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_ID, college_info.getId());
        values.put(MySQLiteHelper.COLUMN_COLLEGEID, college_info.getCollege_id());
        values.put(MySQLiteHelper.COLUMN_INSTITUTENAME, college_info.getInstitute_name());
        values.put(MySQLiteHelper.COLUMN_CITY, college_info.getCity());
        values.put(MySQLiteHelper.COLUMN_STATE, college_info.getState());
        values.put(MySQLiteHelper.COLUMN_OVERALLSCORE, college_info.getOverall_score());
        values.put(MySQLiteHelper.COLUMN_RANK, college_info.getRank());
        getContentResolver().insert(
                FavsContentProvider.CONTENT_URI, values);
//        } else {
//            // Update todo
//            getContentResolver().update(todoUri, values, null, null);
//        }

    }

    private class FavoriteActionAsyncTask extends AsyncTask<Void, Void, Void> {
        final Context mContext;
        MenuItem mitem;

        public FavoriteActionAsyncTask(Context context, MenuItem item) {
            mContext = context;
            mitem = item;
        }

        @Override
        protected Void doInBackground(Void... params) {
            final Drawable drawable;
            boolean isFav = getfav();

            if (!isFav) {
                datasource.createComment(clg_pojo);
                createComment(clg_pojo);
                drawable = getDrawable(R.drawable.heart);//R.drawable.heart_outline;


            } else {
                datasource.deleteComment(clg_pojo);

                deleteComment(clg_pojo);
                drawable = getDrawable(R.drawable.heart_outline);//R.drawable.heart_outline;
            }
            update_widget();


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mitem.setIcon(drawable);

                }
            });


            return null;
        }


    }

    private void update_widget() {
        Intent intent = new Intent(this, CollectionWidget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        int ids[] = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), CollectionWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);
    }
}
