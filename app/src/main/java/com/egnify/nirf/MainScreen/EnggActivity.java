package com.egnify.nirf.MainScreen;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.egnify.nirf.R;
import com.egnify.nirf.Rest.ApiClient;
import com.egnify.nirf.Rest.ApiInterface;
import com.egnify.nirf.utils.AppUrl;
import com.egnify.nirf.utils.MyCustomTextViewBold;
import com.egnify.nirf.utils.MyCustomTextViewMbold;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnggActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout all, tlr, rpc, go, oi, per;
    ImageView all_iv, tlr_iv, rpc_iv, go_iv, oi_iv, per_iv;
    MyCustomTextViewMbold all_tv, tlr_tv, rpc_tv, go_tv, oi_tv, per_tv;
    LinearLayout heading;
    boolean isloading = true;
    Toolbar toolbar;
    boolean ascending = true;
    MyCustomTextViewBold score, rank;
    List<CommonCollegePojo> sort_filter_array = new ArrayList<>();
    List<CollegePojo> sort_coun_students = new ArrayList<>();
    //demo
    private ProgressBar progressBar;
    private RecyclerView rv_coun_students;
    private TextView emptyView;
    private LinearLayout retry_ll;
    private ApiInterface apiService2;
    private Call<CollegeResponse> call2;
    private List<CollegePojo> coun_students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engg);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        heading = (LinearLayout) findViewById(R.id.heading);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        rv_coun_students = (RecyclerView) findViewById(R.id.rv_coun_students);
        emptyView = (TextView) findViewById(R.id.empty_view);
        retry_ll = (LinearLayout) findViewById(R.id.retry_ll);
        Button retry = (Button) findViewById(R.id.retry);

        score = (MyCustomTextViewBold) findViewById(R.id.score);
        rank = (MyCustomTextViewBold) findViewById(R.id.rank);


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

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_coun_students.setLayoutManager(layoutManager);
        apiService2 = ApiClient.getClient2().create(ApiInterface.class);

        String url = AppUrl.get_colleges;
        Log.d("Students_list", url);
        call2 = apiService2.get_colleges(url);
        network_call(call2);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                network_call(call2);
            }
        });

        score.setOnClickListener(this);
        rank.setOnClickListener(this);
        all.setOnClickListener(this);
        tlr.setOnClickListener(this);
        rpc.setOnClickListener(this);
        go.setOnClickListener(this);
        oi.setOnClickListener(this);
        per.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (!isloading) {

            if (id == R.id.all) {
                setAllAdapter(coun_students);
                score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                settonormal(tlr_iv, tlr_tv);
                settonormal(rpc_iv, rpc_tv);
                settonormal(go_iv, go_tv);
                settonormal(oi_iv, oi_tv);
                settoblack(all_iv, all_tv, 0);
                settonormal(per_iv, per_tv);
            } else if (id == R.id.tlr) {
                score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                settonormal(all_iv, all_tv);
                settonormal(rpc_iv, rpc_tv);
                settonormal(go_iv, go_tv);
                settonormal(oi_iv, oi_tv);
                settonormal(per_iv, per_tv);
                settoblack(tlr_iv, tlr_tv, 1);
                setTlrAdapter(coun_students);
            } else if (id == R.id.rpc) {
                score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                settonormal(all_iv, all_tv);
                settonormal(tlr_iv, tlr_tv);
                settonormal(go_iv, go_tv);
                settonormal(oi_iv, oi_tv);
                settonormal(per_iv, per_tv);
                settoblack(rpc_iv, rpc_tv, 2);
                setRPCAdapter(coun_students);
            } else if (id == R.id.go) {
                score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                settonormal(all_iv, all_tv);
                settonormal(tlr_iv, tlr_tv);
                settonormal(rpc_iv, rpc_tv);
                settonormal(oi_iv, oi_tv);
                settonormal(per_iv, per_tv);
                settoblack(go_iv, go_tv, 3);
                setGOAdapter(coun_students);
            } else if (id == R.id.oi) {
                score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                settonormal(all_iv, all_tv);
                settonormal(tlr_iv, tlr_tv);
                settonormal(rpc_iv, rpc_tv);
                settonormal(go_iv, go_tv);
                settonormal(per_iv, per_tv);
                settoblack(oi_iv, oi_tv, 4);
                setOIAdapter(coun_students);
            } else if (id == R.id.per) {
                score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                settonormal(all_iv, all_tv);
                settonormal(tlr_iv, tlr_tv);
                settonormal(rpc_iv, rpc_tv);
                settonormal(go_iv, go_tv);
                settonormal(oi_iv, oi_tv);
                settoblack(per_iv, per_tv, 5);
                setPerAdapter(coun_students);
            }
            if (id == R.id.score || id == R.id.rank) {

                if (ascending) {
                    ascending = false;
                    Collections.sort(sort_filter_array, Collections.reverseOrder());
                    CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, sort_filter_array, sort_coun_students);
                    rv_coun_students.setAdapter(adapter);
                    score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_down, 0);
                    rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_down, 0);
                } else {
                    ascending = true;
                    Collections.sort(sort_filter_array);
                    CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, sort_filter_array, sort_coun_students);
                    rv_coun_students.setAdapter(adapter);
                    score.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                    rank.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.menu_up, 0);
                }

            }


        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void settonormal(ImageView imageView, MyCustomTextViewMbold metric) {
        int color = Color.parseColor("#FFFFFF"); //The color u want
        imageView.setColorFilter(color);
        metric.setTextColor(getResources().getColor(R.color.white));
        metric.setTextSize(14);
        heading.setBackgroundColor(Color.parseColor("#F44336"));
        toolbar.setBackgroundColor(Color.parseColor("#F44336"));
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void settoblack(ImageView imageView, MyCustomTextViewMbold metric, int i) {
        int color = Color.parseColor("#F44336");
        int color2 = R.color.colorPrimaryDark;
        String metric_n = getString(R.string.overall_score);
        if (i == 0) {
            color = Color.parseColor("#F44336");
            color2 = R.color.colorPrimaryDark;
            metric_n =getString(R.string.overall_score) ;
        } else if (i == 1) {
            color = Color.parseColor("#3B88E4");
            color2 = R.color.colorTlrDark;
            metric_n = getString(R.string.tlr) ;
        } else if (i == 2) {
            color = Color.parseColor("#2BDEC5");
            color2 = R.color.colorRpcDark;
            metric_n =getString(R.string.rpc) ;
        } else if (i == 3) {
            color = Color.parseColor("#FDD835");
            color2 = R.color.colorGoDark;
            metric_n =getString(R.string.go) ;
        } else if (i == 4) {
            color = Color.parseColor("#FB406F");
            color2 = R.color.colorOiDark;
            metric_n = getString(R.string.oi) ;
        } else if (i == 5) {
            color = Color.parseColor("#785446");
            color2 = R.color.colorPerDark;
            metric_n = getString(R.string.per);
        }
        heading.setBackgroundColor(color);
        toolbar.setBackgroundColor(color);
        //The color u want
        imageView.setColorFilter(color);
        metric.setTextColor(color);
        metric.setTextSize(16);
        toolbar.setTitle(metric_n);
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, color2));
        }

    }

    public void network_call(Call<CollegeResponse> call) {
        //top_bar.setVisibility(View.VISIBLE);
        // SlideToDown();
        enable_Load();
        call.clone().enqueue(new Callback<CollegeResponse>() {
            @Override
            public void onResponse(Call<CollegeResponse> call, Response<CollegeResponse> response) {

                if (response.body().getError().equals("false")) {
                    diable_Load();
                    coun_students = response.body().getCollege_details();
                    setAllAdapter(coun_students);
                    // setAllAdapter(coun_students);
                    settoblack(all_iv, all_tv, 0);
                    settonormal(tlr_iv, tlr_tv);
                    settonormal(rpc_iv, rpc_tv);
                    settonormal(go_iv, go_tv);
                    settonormal(oi_iv, oi_tv);
                    settonormal(per_iv, per_tv);
                } else {
                    rv_coun_students.setVisibility(View.GONE);
                    emptyView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    retry_ll.setVisibility(View.GONE);
                    //String msg=response.body().g
                }
            }

            @Override
            public void onFailure(Call<CollegeResponse> call, Throwable t) {
                // Log error here since request failed
                if (t instanceof UnknownHostException) {
                    //Add your code for displaying no network connection error
                    progressBar.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    retry_ll.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void enable_Load() {
        isloading = true;
        progressBar.setVisibility(View.VISIBLE);
        retry_ll.setVisibility(View.GONE);
        rv_coun_students.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    private void diable_Load() {
        isloading = false;
        progressBar.setVisibility(View.GONE);
        retry_ll.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
    }


    private void setAllAdapter(List<CollegePojo> l_coun_students) {
        List<CommonCollegePojo> filter_array = new ArrayList<>();
        for (int i = 0; i < l_coun_students.size(); i++) {
            CommonCollegePojo cmn_clg_pojo = new CommonCollegePojo();
            CollegePojo cmn_pojo = l_coun_students.get(i);
            cmn_clg_pojo.setCollege_id(cmn_pojo.getCollege_id());
            cmn_clg_pojo.setInstitute_name(cmn_pojo.getInstitute_name());
            cmn_clg_pojo.setCity(cmn_pojo.getCity());
            cmn_clg_pojo.setState(cmn_pojo.getState());
            cmn_clg_pojo.setOverall_score(cmn_pojo.getOverall_score());
            cmn_clg_pojo.setRank(cmn_pojo.getRank());
            filter_array.add(cmn_clg_pojo);
        }
         /*if(l_coun_students.size()>0) {
            sort_filter_array.clear();
            sort_coun_students.clear();
        }*/
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, filter_array, l_coun_students);
        rv_coun_students.setAdapter(adapter);
        //  top_bar.setVisibility(View.GONE);
        // SlideToAbove();
    }

    private void setTlrAdapter(List<CollegePojo> l_coun_students) {
        List<CommonCollegePojo> filter_array = new ArrayList<>();
        for (int i = 0; i < l_coun_students.size(); i++) {
            CommonCollegePojo cmn_clg_pojo = new CommonCollegePojo();
            CollegePojo cmn_pojo = l_coun_students.get(i);
            cmn_clg_pojo.setCollege_id(cmn_pojo.getCollege_id());
            cmn_clg_pojo.setInstitute_name(cmn_pojo.getInstitute_name());
            cmn_clg_pojo.setCity(cmn_pojo.getCity());
            cmn_clg_pojo.setState(cmn_pojo.getState());
            TlrPojo tlr = cmn_pojo.getTlr();
            cmn_clg_pojo.setOverall_score(tlr.getTlr_score());
            cmn_clg_pojo.setRank(tlr.getTlr_rank());
            filter_array.add(cmn_clg_pojo);
        }
        Collections.sort(filter_array);
         /*if(l_coun_students.size()>0) {
            sort_filter_array.clear();
            sort_coun_students.clear();
        }*/
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, filter_array, l_coun_students);
        rv_coun_students.setAdapter(adapter);
    }

    private void setRPCAdapter(List<CollegePojo> l_coun_students) {
        List<CommonCollegePojo> filter_array = new ArrayList<>();
        for (int i = 0; i < l_coun_students.size(); i++) {
            CommonCollegePojo cmn_clg_pojo = new CommonCollegePojo();
            CollegePojo cmn_pojo = l_coun_students.get(i);
            cmn_clg_pojo.setCollege_id(cmn_pojo.getCollege_id());
            cmn_clg_pojo.setInstitute_name(cmn_pojo.getInstitute_name());
            cmn_clg_pojo.setCity(cmn_pojo.getCity());
            cmn_clg_pojo.setState(cmn_pojo.getState());
            RpcPojo rpc = cmn_pojo.getRpc();
            cmn_clg_pojo.setOverall_score(rpc.getRpc_score());
            cmn_clg_pojo.setRank(rpc.getRpc_rank());
            filter_array.add(cmn_clg_pojo);
        }
        Collections.sort(filter_array);
        /*if(l_coun_students.size()>0) {
            sort_filter_array.clear();
            sort_coun_students.clear();
        }*/
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, filter_array, l_coun_students);
        rv_coun_students.setAdapter(adapter);
    }

    private void setGOAdapter(List<CollegePojo> l_coun_students) {
        List<CommonCollegePojo> filter_array = new ArrayList<>();
        for (int i = 0; i < l_coun_students.size(); i++) {
            CommonCollegePojo cmn_clg_pojo = new CommonCollegePojo();
            CollegePojo cmn_pojo = l_coun_students.get(i);
            cmn_clg_pojo.setCollege_id(cmn_pojo.getCollege_id());
            cmn_clg_pojo.setInstitute_name(cmn_pojo.getInstitute_name());
            cmn_clg_pojo.setCity(cmn_pojo.getCity());
            cmn_clg_pojo.setState(cmn_pojo.getState());
            GoPojo go = cmn_pojo.getGo();
            cmn_clg_pojo.setOverall_score(go.getGo_score());
            cmn_clg_pojo.setRank(go.getGo_rank());
            filter_array.add(cmn_clg_pojo);
        }
        Collections.sort(filter_array);
         /*if(l_coun_students.size()>0) {
            sort_filter_array.clear();
            sort_coun_students.clear();
        }*/
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, filter_array, l_coun_students);
        rv_coun_students.setAdapter(adapter);
    }

    private void setOIAdapter(List<CollegePojo> l_coun_students) {
        List<CommonCollegePojo> filter_array = new ArrayList<>();
        for (int i = 0; i < l_coun_students.size(); i++) {
            CommonCollegePojo cmn_clg_pojo = new CommonCollegePojo();
            CollegePojo cmn_pojo = l_coun_students.get(i);
            cmn_clg_pojo.setCollege_id(cmn_pojo.getCollege_id());
            cmn_clg_pojo.setInstitute_name(cmn_pojo.getInstitute_name());
            cmn_clg_pojo.setCity(cmn_pojo.getCity());
            cmn_clg_pojo.setState(cmn_pojo.getState());
            OiPojo oi = cmn_pojo.getOi();
            cmn_clg_pojo.setOverall_score(oi.getOi_score());
            cmn_clg_pojo.setRank(oi.getOi_rank());
            filter_array.add(cmn_clg_pojo);
        }
        Collections.sort(filter_array);
         /*if(l_coun_students.size()>0) {
            sort_filter_array.clear();
            sort_coun_students.clear();
        }*/
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, filter_array, l_coun_students);
        rv_coun_students.setAdapter(adapter);
    }

    private void setPerAdapter(List<CollegePojo> l_coun_students) {
        List<CommonCollegePojo> filter_array = new ArrayList<>();
        for (int i = 0; i < l_coun_students.size(); i++) {
            CommonCollegePojo cmn_clg_pojo = new CommonCollegePojo();
            CollegePojo cmn_pojo = l_coun_students.get(i);
            cmn_clg_pojo.setCollege_id(cmn_pojo.getCollege_id());
            cmn_clg_pojo.setInstitute_name(cmn_pojo.getInstitute_name());
            cmn_clg_pojo.setCity(cmn_pojo.getCity());
            cmn_clg_pojo.setState(cmn_pojo.getState());
            PerceptionPojo perception = cmn_pojo.getPerception();
            cmn_clg_pojo.setOverall_score(perception.getPerception_score());
            cmn_clg_pojo.setRank(perception.getPerception_rank());
            filter_array.add(cmn_clg_pojo);
        }
        Collections.sort(filter_array);
        /*if(l_coun_students.size()>0) {
            sort_filter_array.clear();
            sort_coun_students.clear();
        }*/
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        sort_filter_array = filter_array;
        sort_coun_students = l_coun_students;
        CollegeAdapter adapter = new CollegeAdapter(EnggActivity.this, filter_array, l_coun_students);
        rv_coun_students.setAdapter(adapter);
    }
}
