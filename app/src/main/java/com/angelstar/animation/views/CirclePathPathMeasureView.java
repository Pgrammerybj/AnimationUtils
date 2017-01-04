package com.angelstar.animation.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.View;

/**
 * 使用PathMeasure实现圆形的路径绘制
 * Created by JackYang on 2017/1/2.
 */

public class CirclePathPathMeasureView extends View {

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

    public CirclePathPathMeasureView(Context context) {
        super(context);
    }

    public CirclePathPathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(6);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);

        //初始化PathMeasure对象
        mPathMeasure = new PathMeasure();

        mCirclePath = new Path();
        mCircleFlagPath = new Path();
    }

    public CirclePathPathMeasureView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
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

        mCircleFlagPath.reset();
        //如果要使用PathMeasure.getSegment()，那么必须就得调用对应的Path的moveTo(0,0)方法
        //不然getSegment()将无效
        mCirclePath.moveTo(0, 0);

        //结束点（根据属性动画的mFlagValue动态计算)
        float mStop = mFlagValue * mPathLength;
        //开始点(放慢动画可以看到开始点的算法，是根据路径的一般来做判断的当路径绘制
        // 小于一般的时候"开始点"位置不变，当大于一半的时候"开始点"极速向结束点靠近，这样形成了加载动画效果)
        float mStart = (float) (mStop - ((0.5 - Math.abs(mFlagValue - 0.5)) * mPathLength));
        //按参数获取相应的路径段(Segment：段)
        mPathMeasure.getSegment(mStart, mStop, mCircleFlagPath, true);

        //用用canvas绘制
        canvas.drawPath(mCircleFlagPath, mPaint);
    }
}

