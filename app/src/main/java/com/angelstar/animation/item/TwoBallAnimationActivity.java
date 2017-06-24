/**
 * JackYang   2017-03-29 14:50
 * Copyright (c)2017 7see Co.Ltd. All right reserved.
 */
package com.angelstar.animation.item;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-03-29 14:50
 */
public class TwoBallAnimationActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_twoball_animation);
        initToolBar(true, R.string.item_two_ball_animation);
    }
}