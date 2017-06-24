package com.ybj.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

/**
 * 利用属性动画和Canvas绘制小球自由落体动画
 * 整个动画可以分为三个阶段：
 * 1.小球直线下落
 * 2.小球快接近地面的时候，地面的阴影动画
 * 3.小球到地面（最低点时的变形效果,椭圆）
 * <p>
 * Created by jackYang on 2017/1/5.
 */

public class SmallBallAnimationView extends View {

    //小球和椭圆的画笔
    private Paint mBallPaint;
    //地面阴影画笔
    private Paint mShadowPaint;
    //轨迹
    private Path mPath;
    //运动的属性动画
    private ValueAnimator mValueAnimator;
    //小球变化的Y坐标
    private float mBallFlagY;
    private float mMBallX;
    private float mMStartY;
    private float mMEndY;
    //圆的半径
    private float mRadius = 30;

    public SmallBallAnimationView(Context context) {
        super(context);
        init();
    }

    public SmallBallAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SmallBallAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化画笔等
     */
    private void init() {
        mBallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBallPaint.setStyle(Paint.Style.FILL);
        mBallPaint.setColor(Color.BLUE);

        mShadowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mShadowPaint.setStyle(Paint.Style.FILL);
        mShadowPaint.setStrokeWidth(3);
        mShadowPaint.setColor(Color.GRAY);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mMBallX = getWidth() / 2;
        mMEndY = getHeight() / 2;
        mMStartY = mMEndY * 2 / 3;
        if (mBallFlagY == 0) {
            showAnimation();
        } else {
            drawCircle(canvas);
            //画阴影线
            drawLine(canvas);
        }
    }

    private void showAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(mMStartY, mMEndY);
        mValueAnimator.setDuration(800);
        //小球加速下落
        mValueAnimator.setInterpolator(new AccelerateInterpolator(1.2f));
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mBallFlagY = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }

    /**
     * 画阴影线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        if (mBallFlagY - mMStartY > 10) {
            float mFlagDiffer = (mBallFlagY - mMStartY) / (mMEndY - mMStartY) * (mRadius / 2) + 2;
            //线性阴影
//            canvas.drawLine(mMBallX, mMEndY, mMBallX + mFlagDiffer, mMEndY, mShadowPaint);
//            canvas.drawLine(mMBallX, mMEndY, mMBallX - mFlagDiffer, mMEndY, mShadowPaint);
            //椭圆阴影
            RectF mRectF = new RectF(mMBallX - mFlagDiffer, mMEndY + 10, mMBallX + mFlagDiffer, mMEndY + 15);
            canvas.drawOval(mRectF, mShadowPaint);
        }
    }

    /**
     * 画椭圆
     *
     * @param canvas
     */
    private void drawCircle(Canvas canvas) {
        if (mMEndY - mBallFlagY > 10) {
            canvas.drawCircle(mMBallX, mBallFlagY, mRadius, mBallPaint);
        } else {
            //绘制椭圆
            RectF mRectF = new RectF(mMBallX - mRadius - 2, mBallFlagY - mRadius + 4, mMBallX + mRadius + 2, mBallFlagY + mRadius);
            canvas.drawOval(mRectF, mBallPaint);
        }
    }
}
