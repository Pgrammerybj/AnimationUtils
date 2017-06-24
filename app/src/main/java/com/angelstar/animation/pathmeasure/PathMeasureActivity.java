/**
 * JackYang   2017-01-02 15:47
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.pathmeasure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * 使用PathMeasure实现一些动画效果
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-02 15:47
 */
public class PathMeasureActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pathmeasuretest);
        initToolBar(true, R.string.type_path_measure);
    }

    public void goCanvasPathPathMeasure(View view) {
        startActivity(new Intent(this, CanvasPathMeasureActivity.class));
    }

    public void goCanvasPath(View view) {
        startActivity(new Intent(this, CanvasPathSimpleActivity.class));
    }

    public void goGetPosTan(View view) {
        startActivity(new Intent(this, PathMeasureGetPosTanActivity.class));
    }

    public void goLineAnimation(View view) {
        startActivity(new Intent(this, LineAnimationActivity.class));
    }
}