/**
 * JackYang   2017-05-03 11:51
 * Copyright (c)2017 7see Co.Ltd. All right reserved.
 */
package com.angelstar.animation.pathmeasure;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.angelstar.animation.R;
import com.angelstar.animation.views.LineAnimationView;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-05-03 11:51
 */
public class LineAnimationActivity extends AppCompatActivity {

    private LineAnimationView mLine3;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_lineanimation);

        LineAnimationView line1 = (LineAnimationView) findViewById(R.id.ling1);
        LineAnimationView line2 = (LineAnimationView) findViewById(R.id.ling2);
        mLine3 = (LineAnimationView) findViewById(R.id.ling3);
        //line1.setCurrentOrientation(LineAnimationView.ANIMATION_LEFT);
        //line1.setLong2Short(false);
//        line2.setCurrentOrientation(LineAnimationView.ANIMATION_RIGHT);
//        line2.setLong2Short(false);
        mLine3.setCurrentOrientation(LineAnimationView.ANIMATION_CENTER);
        mLine3.setLong2Short(false);
//        line3.setLineWidth(80);

        View mDemo = findViewById(R.id.view);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1f, 0.5f, 0, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.start();
        mDemo.startAnimation(scaleAnimation);

        getViewSize2();
    }


    /**
     * 获取view的宽高的第一种方式（注意 弊端）
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int measuredWidth = mLine3.getMeasuredWidth();
        int measuredHeight = mLine3.getMeasuredHeight();
    }

    /**
     * 获取view的宽高的第二中方式
     */
    private void getViewSize2() {
        ViewTreeObserver viewTreeObserver = mLine3.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mLine3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int measuredWidth = mLine3.getMeasuredWidth();
                int measuredHeight = mLine3.getMeasuredHeight();
            }
        });
    }

    /**
     * 获取view的宽高的第三种方式
     */
    @Override
    protected void onStart() {
        super.onStart();
        mLine3.post(new Runnable() {
            @Override
            public void run() {
                int measuredWidth = mLine3.getMeasuredWidth();
                int measuredHeight = mLine3.getMeasuredHeight();
            }
        });
    }

    // TODO: 2017/5/14 第四种方式就只在代码中对mLine3手动的Measure来得到View的宽高。比较复杂需要根据View的LayoutParams来分别计算
}