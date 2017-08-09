/*
  * JackYang   2017-08-09 17:30
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.angelstar.animation.item;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * 贝塞尔实现的水波纹和图形叠加加载动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-08-09 17:30
 */
public class WaterRipplesActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_waterripples);
        initToolBar(true, R.string.item_water_ripples_animation);
    }
}