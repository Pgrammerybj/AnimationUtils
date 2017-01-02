/**
 * JackYang   2017-01-02 15:47
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.pathmeasure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.R;

/**
 * 使用PathMeasure实现一些动画效果
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-02 15:47
 */
public class PathMeasureTest extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pathmeasuretest);
    }

    public void goCanvasPathPathMeasure(View view) {
        startActivity(new Intent(this,CanvasPathPathMeasure.class));
    }

}