/*
  * JackYang   2017-06-19 15:20
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.angelstar.animation;

import android.os.Bundle;

import com.angelstar.animation.views.RatingBar;

import butterknife.BindView;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-06-19 15:20
 */
public class CustomRatingBarActivity extends BaseActivity {

    @BindView(R.id.rating_bar) RatingBar mRatingBar;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.custom_ratingbar);
        initToolBar(true, R.string.type_custom_ratingBar);

        mRatingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                showToast("打了：" + RatingCount + "分");
            }
        });
    }
}
