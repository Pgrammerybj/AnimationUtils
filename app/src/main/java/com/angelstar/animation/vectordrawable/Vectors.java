/**
 * JackYang   2016-12-28 18:37
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.vectordrawable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.angelstar.animation.R;

/**
 * class description here@ybj
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-28 18:37
 */
public class Vectors extends AppCompatActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_vectordemo);
    }

    public void goDemo(View view) {
        startActivity(new Intent(this,Demo.class));
    }
}