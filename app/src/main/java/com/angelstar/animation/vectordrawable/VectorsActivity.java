/**
 * JackYang   2016-12-28 18:37
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.vectordrawable;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-28 18:37
 */
public class VectorsActivity extends AppCompatActivity {

    static {
        //Google为了兼容的问题，但是在我这个项目中没有添加也是可以运行的。
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //隐藏地步的导航栏
        View decorView = getWindow().getDecorView();
        int systemUiFlagHideNavigation = 0;
        systemUiFlagHideNavigation = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(systemUiFlagHideNavigation);
        setContentView(R.layout.activity_vectordemo);
    }

    public void click(View view) {
        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
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