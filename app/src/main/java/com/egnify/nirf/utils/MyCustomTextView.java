package com.egnify.nirf.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

/**
 * Created by janardhanyerranagu on 9/19/15.
 */
public class MyCustomTextView extends android.support.v7.widget.AppCompatTextView {

    public MyCustomTextView(Context context, AttributeSet arrt) {
        super(context, arrt);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Light.ttf"));
    }


}
