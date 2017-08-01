/*
  * JackYang   2017-08-01 16:19
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.angelstar.animation.item;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;
import com.ybj.animation.LoadingTextView;

/**
 * Loading文字加载动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-08-01 16:19
 */
public class LoadingTextActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_loadingtext);
        initToolBar(true, R.string.item_loading_text_animation);
        LoadingTextView loading = (LoadingTextView) findViewById(R.id.loading_text);
        loading.start();
    }
}