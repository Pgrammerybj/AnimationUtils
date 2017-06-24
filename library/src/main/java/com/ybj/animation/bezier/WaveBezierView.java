package com.ybj.animation.bezier;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Bezier&属性动画实现自定义水波纹View
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
    private final static int WAVELENGTH = 460;
    //准备路径工具
    private Path mPath;
    private Path mSecondPath;
    //初始化画笔
    Paint mBezierPaint;
    Paint mSecondBezierPaint;
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

    private Bitmap backgroundBitmap;

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
        //绘制后面一道Bezier曲线的画笔
        mSecondBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondBezierPaint.setStyle(Paint.Style.FILL);
        mSecondBezierPaint.setColor(Color.GREEN);
        mSecondBezierPaint.setStrokeWidth(4);
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
        mSecondPath = new Path();
        setOnClickListener(this);

        if(null==getBackground()){
            throw new IllegalArgumentException(String.format("background is null."));
        }else{
             backgroundBitmap = getBitmapFromDrawable(getBackground());
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //清楚Path之前的所有设置
        mPath.reset();
        mSecondPath.reset();
        //mPath.moveTo(WAVELENGTH + mOffset, mWaveHeight);
        mPath.moveTo(0, mWaveHeight);
        mSecondPath.moveTo(0, mWaveHeight);
        for (int i = 0; i < mWaveCount; i++) {
            //Path绘制贝塞尔曲线
            mPath.quadTo(-WAVELENGTH * 3 / 4 + WAVELENGTH * i + mOffset, mWaveHeight - 40, -WAVELENGTH / 2 + WAVELENGTH * i + mOffset, mWaveHeight);
            mPath.quadTo(-WAVELENGTH / 4 + WAVELENGTH * i + mOffset, mWaveHeight + 40, WAVELENGTH * i + mOffset, mWaveHeight);
            //后面淡淡的水波
            mSecondPath.quadTo(-WAVELENGTH * 3 / 4 + WAVELENGTH * i + mOffset, mWaveHeight + 46, -WAVELENGTH / 2 + WAVELENGTH * i + mOffset, mWaveHeight);
            mSecondPath.quadTo(-WAVELENGTH / 4 + WAVELENGTH * i + mOffset, mWaveHeight - 46, WAVELENGTH * i + mOffset, mWaveHeight);
        }
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mSecondPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mSecondPath.lineTo(0, mScreenHeight);
        mPath.close();
        mSecondPath.close();
        //开始用canvas绘制
        //canvas.drawPath(mSecondPath, mSecondBezierPaint);
        canvas.drawPath(mPath, mBezierPaint);

        ///////////////////////////////////////////////////////////////////////////
        // 做动画重叠效果
        ///////////////////////////////////////////////////////////////////////////

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        int min = Math.min(mScreenHeight,mScreenHeight);
        backgroundBitmap = Bitmap.createScaledBitmap(backgroundBitmap,min,min,false);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));

        canvas.drawBitmap(backgroundBitmap,0,0,paint);
    }

    @Override
    public void onClick(View view) {
        AnimatorSet animatorSet = new AnimatorSet();
        mValueAnimator = ValueAnimator.ofInt(0, WAVELENGTH);
        mValueAnimator.setDuration(800);
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
        //mMHeightValueAnimator = ValueAnimator.ofInt(mScreenHeight, mCenterHeight);
        mMHeightValueAnimator = ValueAnimator.ofInt(mScreenHeight, 0);
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


    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        try {
            Bitmap bitmap;
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }
}
