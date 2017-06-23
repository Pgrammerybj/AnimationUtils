/*
  * JackYang   2017-06-19 15:20
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.angelstar.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.angelstar.animation.views.RatingBar;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-06-19 15:20
 */
public class CustomRatingBarActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.custom_ratingbar);

        initView();
    }

    private void initView() {
        RatingBar mRatingBar = (RatingBar) findViewById(R.id.rating_bar);
        mRatingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                Toast.makeText(CustomRatingBarActivity.this, "打了：" + RatingCount + "分", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
