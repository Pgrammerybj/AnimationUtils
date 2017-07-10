/*
  * JackYang   2017-07-10 08:28
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.angelstar.animation.item;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * 两个小球的粘性控件效果@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-07-10 08:28
 */
public class StickyControlsActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sticky_controls);
        initToolBar(true, R.string.item_two_ball_sticky_animation);
    }
}