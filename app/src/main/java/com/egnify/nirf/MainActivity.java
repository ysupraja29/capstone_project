package com.egnify.nirf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.egnify.nirf.MainScreen.EnggActivity;

public class MainActivity extends BaseActivity implements
        View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        ImageView uni_iv=(ImageView) findViewById(R.id.uni_iv);
        ImageView engg_iv=(ImageView) findViewById(R.id.engg_iv);
        ImageView manag_iv=(ImageView) findViewById(R.id.manag_iv);
        ImageView pharm_iv=(ImageView) findViewById(R.id.pharm_iv);

        Glide.with(MainActivity.this).load(R.drawable.university).into(uni_iv);
        Glide.with(MainActivity.this).load(R.drawable.engineering).into(engg_iv);
        Glide.with(MainActivity.this).load(R.drawable.managment).into(manag_iv);
        Glide.with(MainActivity.this).load(R.drawable.pharm).into(pharm_iv);
        uni_iv.setOnClickListener(this);
        engg_iv.setOnClickListener(this);
        manag_iv.setOnClickListener(this);
        pharm_iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.engg_iv) {
            Intent intent=new Intent(MainActivity.this, EnggActivity.class);
            startActivity(intent);
        }
    }
}
