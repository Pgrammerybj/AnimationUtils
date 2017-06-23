/**
 * jackyang   2017-01-02 23:54
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.pathmeasure;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-02 23:54
 */
public class CanvasPathSimpleActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_canvaspathsimple);
        initToolBar(true, R.string.btn_path_measure_canvas2_path);
    }
}