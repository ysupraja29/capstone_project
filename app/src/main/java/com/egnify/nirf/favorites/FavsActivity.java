package com.egnify.nirf.favorites;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.egnify.nirf.R;
import com.egnify.nirf.utils.MyCustomTextView;
import com.egnify.nirf.utils.MyCustomTextViewMbold;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FavsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";
    private CursorRecyclerViewAdapter adapter;
    private RecyclerView mRecyclerView;
    private int LOADER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favs);
        MyCustomTextView nouser = (MyCustomTextView) findViewById(R.id.nouser);
        RelativeLayout visible_profile = (RelativeLayout) findViewById(R.id.visible_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ImageView profile = (ImageView) findViewById(R.id.profile);

        MyCustomTextViewMbold username = (MyCustomTextViewMbold) findViewById(R.id.user_name);
        MyCustomTextView email_id = (MyCustomTextView) findViewById(R.id.email_id);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            visible_profile.setVisibility(View.VISIBLE);
            nouser.setVisibility(View.GONE);
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String uid = user.getUid();
            Glide.with(this).load(photoUrl).into(profile);
            username.setText(name);
            email_id.setText(email);
            mRecyclerView = (RecyclerView) findViewById(R.id.fav_rv);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            adapter = new CursorRecyclerViewAdapter(this, null);
            mRecyclerView.setAdapter(adapter);


        } else {
            visible_profile.setVisibility(View.GONE);
            nouser.setVisibility(View.VISIBLE);
        }

    }



    /*loader*/

    @Override
    protected void onStart() {
        super.onStart();
        getSupportLoaderManager().initLoader(0, null, this);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, FavsContentProvider.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

}
