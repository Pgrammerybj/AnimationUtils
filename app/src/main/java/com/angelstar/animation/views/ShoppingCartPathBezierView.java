package com.angelstar.animation.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.angelstar.animation.R;
import com.angelstar.animation.PaintPathEvaluator;

/**
 * 自定义购物车轨迹动画
 * Created by JackYang on 2017/1/1.
 */

public class ShoppingCartPathBezierView extends View implements View.OnClickListener {

    //起点
    private float mStartPointX;
    private float mStartPointY;
    //终点
    private float mEndPointX;
    private float mEndPointY;
    //标记点
    private float mFlagPointX;
    private float mFlagPointY;
    //运动点
    private float mMovePointX;
    private float mMovePointY;
    //准备路径工具
    private Path mPath;
    //初始化画笔
    Paint mBezierPaint;
    Paint mCirclePaint;
    //属性动画
    ValueAnimator mValueAnimator;
    private Bitmap mBitmap;
    private int mBitmapWidth;
    private int mBitmapHeight;


    public ShoppingCartPathBezierView(Context context) {
        super(context);
    }

    public ShoppingCartPathBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setStrokeWidth(4);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setColor(Color.GREEN);

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStrokeWidth(6);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.BLUE);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mBitmapWidth = mBitmap.getWidth();
        mBitmapHeight = mBitmap.getHeight();
        mPath = new Path();
    }

    public ShoppingCartPathBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //首先定义好两个定点的位置
        mStartPointX = w / 4;
        mStartPointY = h / 5;
        mEndPointX = w -mBitmapWidth/2;
        mEndPointY = h -mBitmapHeight/2;
        //控制点
        mFlagPointX = w / 2 + 50;
        mFlagPointY = h / 5 + 20;
        //定点商品
        mMovePointX = mStartPointX;
        mMovePointY = mStartPointY;

        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        PaintPathEvaluator paintPathEvaluator = new PaintPathEvaluator(new PointF(mFlagPointX, mFlagPointY));
        mValueAnimator = ValueAnimator.ofObject(paintPathEvaluator,
                new PointF(mStartPointX, mStartPointY),
                new PointF(mEndPointX, mEndPointY));
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                mMovePointX = pointF.x;
                mMovePointY = pointF.y;
                invalidate();
            }
        });
        mValueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartPointX, mStartPointY);
        mPath.quadTo(mFlagPointX, mFlagPointY, mEndPointX, mEndPointY);
        canvas.drawPath(mPath, mBezierPaint);
        canvas.drawBitmap(mBitmap, mMovePointX - mBitmapWidth / 2, mMovePointY - mBitmapHeight / 2, mCirclePaint);
    }
}
