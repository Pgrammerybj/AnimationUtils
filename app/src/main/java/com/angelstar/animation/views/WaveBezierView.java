package com.angelstar.animation.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Bezier实现的水波纹自定义View
 * 二阶贝塞尔曲线有三个点，一个起点一个终点还有就是一个动点
 * Created by jackYang on 2016/12/30.
 */

public class WaveBezierView extends View implements View.OnClickListener {

    //动点
    private float mFlagPointX;
    private float mFlagPointY;
    //一个屏幕完整波的个数
    private int mWaveCount;
    //定义一个高度的基准线
    private int mCenterHeight;
    //波长
    private final static int WAVELENGTH = 200;
    //准备路径工具
    private Path mPath;
    //初始化画笔
    Paint mBezierPaint;
    //属性动画
    ValueAnimator mValueAnimator;
    //偏移量
    private int mOffset;
    //屏幕的宽度
    private int mScreenWidth;
    private int mScreenHeight;
    //是否开始了动画
    private boolean isStartAnimator = false;
    //水波纹的动态高度；
    private int mWaveHeight;
    private ValueAnimator mMHeightValueAnimator;

    public WaveBezierView(Context context) {
        super(context);
    }

    public WaveBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Paint.ANTI_ALIAS_FLAG抗锯齿
        //绘制Bezier曲线的画笔
        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setStyle(Paint.Style.FILL);
        mBezierPaint.setColor(Color.BLUE);
        mBezierPaint.setStrokeWidth(6);
    }

    public WaveBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenWidth = w;
        mScreenHeight = h;
        mCenterHeight = h / 2;
        mWaveHeight = mScreenHeight;
        mWaveCount = (int) Math.round((w / WAVELENGTH) + 1.5);
        mPath = new Path();
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清楚Path之前的所有设置
        mPath.reset();
        mPath.moveTo(-WAVELENGTH + mOffset, mWaveHeight);
        for (int i = 0; i < mWaveCount; i++) {
            //Path绘制贝塞尔曲线
            mPath.quadTo(-WAVELENGTH * 3 / 4 + WAVELENGTH * i + mOffset, mWaveHeight - 30, -WAVELENGTH / 2 + WAVELENGTH * i + mOffset, mWaveHeight);
            mPath.quadTo(-WAVELENGTH / 4 + WAVELENGTH * i + mOffset, mWaveHeight + 30, WAVELENGTH * i + mOffset, mWaveHeight);
        }
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        //开始用canvas绘制
        canvas.drawPath(mPath, mBezierPaint);
    }

    @Override
    public void onClick(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        mValueAnimator = ValueAnimator.ofInt(0, WAVELENGTH);
        mValueAnimator.setDuration(600);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mOffset = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        //改变水波纹的高度动画
        mMHeightValueAnimator = ValueAnimator.ofInt(mScreenHeight, mCenterHeight);
        mMHeightValueAnimator.setDuration(4000);
        mMHeightValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mWaveHeight = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animatorSet.playTogether(mValueAnimator, mMHeightValueAnimator);
        animatorSet.setInterpolator(new LinearInterpolator());
        if (!isStartAnimator) animatorSet.start();
        isStartAnimator = true;
    }
}
