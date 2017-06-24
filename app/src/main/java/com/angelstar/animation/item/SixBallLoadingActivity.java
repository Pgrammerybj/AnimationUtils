/**
 * JackYang   2017-04-11 15:27
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
 * @since 2017-04-11 15:27
 */
public class SixBallLoadingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_sixballloading);
        initToolBar(true, R.string.item_six_ball_animation);
    }
}