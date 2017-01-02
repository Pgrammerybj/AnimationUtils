package com.angelstar.animation.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;

/**
 * 使用PathMeasure实现圆形的路径绘制
 * Created by JackYang on 2017/1/2.
 */

public class CirclePathSimpleView extends View {

    //圆形的路径
    private Path mCirclePath;
    //圆形的临时路径，给PathMeasure使用
    private Path mCircleFlagPath;
    //绘制圆形的画笔
    private Paint mPaint;
    //初始化PathMeasure对象
    private PathMeasure mPathMeasure;
    //圆形的路径长度
    private float mPathLength;
    //添加的属性动画
    private ValueAnimator mValueAnimator;
    //动画输出的动态值
    private float mFlagValue;

    public CirclePathSimpleView(Context context) {
        super(context);
    }

    public CirclePathSimpleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);

        //初始化PathMeasure对象
        mPathMeasure = new PathMeasure();
        mCirclePath = new Path();
    }

    public CirclePathSimpleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //定义一个圆形
        mCirclePath.addCircle(w / 2, h / 2, 100, Path.Direction.CW);
        //将Path设置给PathMeasure
        mPathMeasure.setPath(mCirclePath, true);
        //获取圆形路径的总长度
        mPathLength = mPathMeasure.getLength();

        //设置属性动画
        mValueAnimator = ValueAnimator.ofFloat(1, 0);
        mValueAnimator.setDuration(1600);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.RESTART);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mFlagValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //避免使用：mPathMeasure.getSegment(mStart, mStop, mCircleFlagPath, true);
        PathEffect pathEffect = new DashPathEffect(new float[]{mPathLength, mPathLength}, mFlagValue*mPathLength);

        mPaint.setPathEffect(pathEffect);

        //用用canvas绘制
        canvas.drawPath(mCirclePath, mPaint);
    }
}

