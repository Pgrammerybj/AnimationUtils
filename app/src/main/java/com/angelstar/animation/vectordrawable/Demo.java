/**
 * jackyang   2016-12-28 19:22
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.vectordrawable;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-28 19:22
 */
public class Demo extends AppCompatActivity {

    private static final String TAG = Demo.class.getSimpleName();

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
        setContentView(R.layout.activity_demo);
    }

    public void click(View view) {
        ImageView imageView = (ImageView) view;
        Drawable drawable = imageView.getDrawable();
        if (drawable instanceof Animatable) {
            ((Animatable) drawable).start();
        }
    }

    /**
     * VectorDrawable动态矢量图的兼容
     */
    public void goAnim(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(new Intent(this, Demo2.class));
        } else {
            //当前系统版本大于5.0的时候才支持Vector的动态变换图
            Toast.makeText(this, "当前版本不支持Vector的动态变换", Toast.LENGTH_SHORT).show();
        }
    }
}