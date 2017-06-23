/**
 * jackyang   2016-12-30 23:38
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.bezier;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-30 23:38
 */
public class ThirdBezierActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_thirdbezier);
        initToolBar(true, R.string.bezier_third);
    }
}