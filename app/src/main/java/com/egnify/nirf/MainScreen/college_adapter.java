package com.egnify.nirf.MainScreen;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.egnify.nirf.CollegeDetailsActivity;
import com.egnify.nirf.R;
import com.egnify.nirf.utils.app_url;
import com.egnify.nirf.utils.p_MyCustomTextView_mbold;

import java.util.ArrayList;
import java.util.List;


public class college_adapter extends RecyclerView.Adapter<college_adapter.CustomViewHolder> {
    private Activity mContext;
    private List<common_college_pojo> l_coun_students = new ArrayList<>();
    List<college_pojo> lCounStudents = new ArrayList<>();

    public college_adapter(Activity activity, List<common_college_pojo> l_coun_students, List<college_pojo> lCounStudents) {
        this.mContext = activity;
        this.l_coun_students = l_coun_students;
        this.lCounStudents = lCounStudents;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.college_item, null);
        final CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final CustomViewHolder customViewHolder, final int i) {
        final common_college_pojo clg_pojo = l_coun_students.get(i);
        final String name = tocaps(clg_pojo.getInstitute_name());
        customViewHolder.name.setText(name);
        customViewHolder.college_id.setText(clg_pojo.getCollege_id());
        customViewHolder.location.setText(clg_pojo.getCity() + "," + clg_pojo.getState());
        customViewHolder.score.setText(String.valueOf(clg_pojo.getOverall_score()));
        customViewHolder.rank.setText(String.valueOf(clg_pojo.getRank()));
        final int position = HasName(clg_pojo.getCollege_id());
        String url = app_url.image_url + clg_pojo.getCollege_id() + ".png";
        Log.d("college_image",url);
        Glide.with(mContext).load(url).into(customViewHolder.logo);

        customViewHolder.row_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position >= 0) {
                    Intent intent = new Intent(mContext, CollegeDetailsActivity.class);
                    intent.putExtra("college_pojo", lCounStudents.get(position));
                    mContext.startActivity(intent);
                }
            }
        });
    }

    public int HasName(String college_id) {
        for (int i = 0; i < lCounStudents.size(); i++) {
            String s = lCounStudents.get(i).getCollege_id();
            //search the string
            if (college_id.equals(s)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return (null != l_coun_students ? l_coun_students.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        p_MyCustomTextView_mbold name;
        LinearLayout row_one;
        p_MyCustomTextView_mbold college_id;
        p_MyCustomTextView_mbold location;
        p_MyCustomTextView_mbold score;
        p_MyCustomTextView_mbold rank;
        ImageView logo;

        public CustomViewHolder(View view) {
            super(view);
            this.row_one = (LinearLayout) view.findViewById(R.id.row_one);
            this.name = (p_MyCustomTextView_mbold) view.findViewById(R.id.name);
            this.college_id = (p_MyCustomTextView_mbold) view.findViewById(R.id.college_id);
            this.location = (p_MyCustomTextView_mbold) view.findViewById(R.id.location);
            this.score = (p_MyCustomTextView_mbold) view.findViewById(R.id.score);
            this.rank = (p_MyCustomTextView_mbold) view.findViewById(R.id.rank);
            this.logo = (ImageView) view.findViewById(R.id.logo);
        }
    }

    public String tocaps(String source) {
        Log.d("name", source);
        source = source.toLowerCase();
        StringBuffer res = new StringBuffer();

        String[] strArr = source.split(" ");
        for (String str : strArr) {
            if (!str.equals("")) {
                char[] stringArray = str.trim().toCharArray();
                stringArray[0] = Character.toUpperCase(stringArray[0]);
                str = new String(stringArray);
                res.append(str).append(" ");
            }
        }

        return res.toString().trim();
    }
}
