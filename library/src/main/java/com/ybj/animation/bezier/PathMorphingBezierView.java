package com.ybj.animation.bezier;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Bezier实现的路径变换动画自定义View
 * 一个起点一个终点还有就是2个动点，通过属性动画来控制动点的Y坐标
 * Created by jackYang on 2016/12/31.
 */

public class PathMorphingBezierView extends View implements View.OnClickListener {

    //起点
    private float mStartPointX;
    private float mStartPointY;
    //终点
    private float mEndPointX;
    private float mEndPointY;
    //动点1
    private float mFlagPointX;
    private float mFlagPointY;
    //动点2
    private float mFlagPoint2X;
    private float mFlagPoint2Y;

    //准备路径工具
    private Path mPath;
    //初始化画笔
    Paint mBezierPaint;
    Paint mPointPaint;
    Paint mTextPaint;

    //属性动画
    ValueAnimator mValueAnimator;

    public PathMorphingBezierView(Context context) {
        super(context);
    }

    public PathMorphingBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //Paint.ANTI_ALIAS_FLAG抗锯齿
        //绘制Bezier曲线的画笔
        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setStrokeWidth(6);
        //绘制两个点的画笔
        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStyle(Paint.Style.STROKE);
        mPointPaint.setStrokeWidth(16);
        mPointPaint.setColor(Color.BLUE);
        //绘制文本的画笔
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeWidth(1);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(Color.RED);
    }

    public PathMorphingBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化起点和终点的位置
        mStartPointX = w / 4;
        mStartPointY = h / 4;
        mEndPointX = w / 4 * 3;
        mEndPointY = h / 4;

        //先给中间的动点来个默认值
        mFlagPointX = mStartPointX;
        mFlagPointY = mStartPointY;
        mFlagPoint2X = mEndPointX;
        mFlagPoint2Y = mEndPointY;

        mPath = new Path();

        //添加属性动画用来改变动点的Y坐标
        mValueAnimator = ValueAnimator.ofFloat(mStartPointY, h);
        mValueAnimator.setDuration(2000);
        mValueAnimator.setInterpolator(new BounceInterpolator());
        //添加一个动画过程的监听
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //两个动点的Y坐标统一变换
                mFlagPointY = (float) valueAnimator.getAnimatedValue();
                mFlagPoint2Y = (float) valueAnimator.getAnimatedValue();
                //刷新页面
                invalidate();
            }
        });
        //给着一个View添加一个点击时间，触发动画的开始
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //清楚Path之前的所有设置
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);
        //Path绘制贝塞尔曲线
        mPath.cubicTo(mFlagPointX, mFlagPointY, mFlagPoint2X, mFlagPoint2Y, mEndPointX, mEndPointY);

        //开始用canvas绘制
        canvas.drawPath(mPath, mBezierPaint);
        //绘制起点和终点
        canvas.drawPoint(mStartPointX, mStartPointY, mPointPaint);
        canvas.drawText("起点", mStartPointX, mStartPointY + 20, mTextPaint);
        canvas.drawPoint(mEndPointX, mEndPointY, mPointPaint);
        canvas.drawText("终点", mEndPointX, mEndPointY + 20, mTextPaint);
        canvas.drawPoint(mFlagPointX, mFlagPointY, mPointPaint);
        canvas.drawPoint(mFlagPoint2X, mFlagPoint2Y, mPointPaint);
        canvas.drawLine(mStartPointX, mStartPointY, mFlagPointX, mFlagPointY, mTextPaint);
        canvas.drawLine(mFlagPointX, mFlagPointY, mFlagPoint2X, mFlagPoint2Y, mTextPaint);
        canvas.drawLine(mFlagPoint2X, mFlagPoint2Y, mEndPointX, mEndPointY, mTextPaint);
    }

    @Override
    public void onClick(View view) {
        //点击后触发动画的开始
        mValueAnimator.start();
    }
}
