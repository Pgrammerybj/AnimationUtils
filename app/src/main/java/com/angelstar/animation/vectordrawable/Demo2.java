/**
 * Jack Yang   2016-12-29 17:29
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.vectordrawable;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.angelstar.animation.R;

/**
 * 动态的VectorDrawable的变换动画测试
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-29 17:29
 */
public class Demo2 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_demo2);
    }

    /**
     * 一定要分清AnimatedVectorDrawable AnimatedVectorDrawableCompat的区别和用法
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void anim(View view) {
        //必须在5.0以上才可以使用
        ImageView imageView = (ImageView) view;
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getDrawable(R.drawable.fivestar_anim);
        imageView.setImageDrawable(drawable);
        if (null != drawable) drawable.start();
    }
}