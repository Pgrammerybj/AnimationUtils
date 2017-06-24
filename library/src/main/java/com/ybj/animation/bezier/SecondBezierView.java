package com.ybj.animation.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 二阶贝塞尔自定义View
 * 二阶贝塞尔曲线有三个点，一个起点一个终点还有就是一个动点
 * Created by jackYang on 2016/12/30.
 */

public class SecondBezierView extends View {

    //起点
    private float mStartPointX;
    private float mStartPointY;
    //终点
    private float mEndPointX;
    private float mEndPointY;
    //动点
    private float mFlagPointX;
    private float mFlagPointY;

    //准备路径工具
    private Path mPath;
    //初始化画笔
    Paint mBezierPaint;
    Paint mPointPaint;
    Paint mTextPaint;

    public SecondBezierView(Context context) {
        this(context, null);
    }

    public SecondBezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(30);
        mTextPaint.setColor(Color.RED);
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
        mFlagPointX = w / 2;
        mFlagPointY = h / 4 - 200;

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //清楚Path之前的所有设置
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);
        //Path绘制贝塞尔曲线
        mPath.quadTo(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY);

        //开始用canvas绘制
        canvas.drawPath(mPath, mBezierPaint);
        //绘制起点和终点
        canvas.drawPoint(mStartPointX, mStartPointY, mPointPaint);
        canvas.drawText("起点", mStartPointX, mStartPointY + 20, mTextPaint);
        canvas.drawPoint(mEndPointX, mEndPointY, mPointPaint);
        canvas.drawText("终点", mEndPointX, mEndPointY + 20, mTextPaint);
        canvas.drawPoint(mFlagPointX, mFlagPointY, mPointPaint);
        canvas.drawLine(mStartPointX, mStartPointY, mFlagPointX, mFlagPointY, mTextPaint);
        canvas.drawLine(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY, mTextPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                //动态获取中间点的坐标
                mFlagPointX = event.getX();
                mFlagPointY = event.getY();
                //页面刷新
                invalidate();
                break;
        }
        return true;
    }
}
