/**
 * JackYang   2017-04-11 15:28
 * Copyright (c)2017 7see Co.Ltd. All right reserved.
 */
package com.ybj.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Loader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 六个小球从依次左到右加载动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-04-11 15:28
 * <p>
 * Flag:1.0.0版本只为实现功能，目前并没有加入各种自定义属性来控制动画，期待下个版本
 */
public class SixBallLoadingView extends View {

    private int mSmallBallCount = 6, mRadius = 26;
    private long duration = 800;
    private Paint mPaint;
    private Path mPath;
    private PathMeasure mPathMeasure;
    private ValueAnimator mValueAnimator;
    private boolean onTheHorizon = true;
    //中间的间隙等于小球的半径，最左和最右边的小球分别都有一个半径的间隙
    private int mViewWidth = (3 * mSmallBallCount + 1) * mRadius;
    private int mViewHeight = mRadius * 2 * 10;
    private int mCenterY = mViewHeight / 2;
    private int mTranslateX;
    private int mTranslateY;
    private float mPathLength;
    private float mAnimatedValue;
    private int count = 0;

    //存放贝塞尔曲线上的点的坐标
    private float[] mPoint = new float[2];
    private int[] mColors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.YELLOW};
    private int[] mTempColors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GREEN, Color.RED, Color.YELLOW};


    public SixBallLoadingView(Context context) {
        this(context, null);
        invalidateView();
    }

    public SixBallLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        invalidateView();
    }

    public SixBallLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        invalidateView();
    }

    private void invalidateView() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setDither(true);

        //初始化path
        mPath = new Path();
        //初始化pathMeasure
        mPathMeasure = new PathMeasure();
        //初始化动画
        invalidateAnimation();
    }

    private void invalidateAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(duration);
        mValueAnimator.setRepeatCount(Integer.MAX_VALUE >> 1);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAnimatedValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                //执行完一次以后改变运动的方向，从左上到左下，以此往复
                changeColors(count % 6);
                onTheHorizon = !onTheHorizon;
                count++;
            }
        });
    }

    private void changeColors(int flag) {
        int temp = mColors[flag];
        for (int i = 0; i < mSmallBallCount; i++) {
            if (i == mSmallBallCount - 1) {
                mTempColors[mSmallBallCount - 1] = temp;
            } else {
                mTempColors[i] = mColors[(flag + i + 1) % 6];
            }
        }
//        mTempColors[0] = mColors[(flag + 1) % 6];
//        mTempColors[1] = mColors[(flag + 2) % 6];
//        mTempColors[2] = mColors[(flag + 3) % 6];
//        mTempColors[3] = mColors[(flag + 4) % 6];
//        mTempColors[4] = mColors[(flag + 5) % 6];
//        mTempColors[5] = temp;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


        if (widthMode != MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
            if (width < mViewWidth)
                width = mViewWidth;
            if (height < mViewHeight)
                height = mViewHeight;
        } else if (widthMeasureSpec != MeasureSpec.AT_MOST) {
            if (width < mViewWidth)
                width = mViewWidth;
        } else if (heightMeasureSpec != MeasureSpec.AT_MOST) {
            if (height < mViewHeight)
                height = mViewHeight;
        }

        //计算X和Y轴的偏移量(居中)
        mTranslateX = (width - mViewWidth) / 2;
        mTranslateY = (height - mViewHeight) / 2;

        mPath.moveTo(mRadius * 2, mCenterY);
        mPath.cubicTo(mRadius * 2, mCenterY, mViewWidth / 2, 0, mViewWidth - 2 * mRadius, mCenterY);
        mPathMeasure.setPath(mPath, false);
        mPathLength = mPathMeasure.getLength();

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(mTranslateX, mTranslateY);
        for (int i = 0; i < mSmallBallCount; i++) {
            mPaint.setColor(mTempColors[i]);
            if (i == 0) {
                //第一个点需要做贝塞尔轨迹动画
                mPathMeasure.getPosTan(mPathLength * mAnimatedValue, mPoint, null);
                canvas.drawCircle(mPoint[0], onTheHorizon ? mPoint[1] : mViewHeight - mPoint[1],
                        mRadius, mPaint);
            } else {
                //后面5个球依次向左平移3个半径
                canvas.drawCircle((2 + 3 * i) * mRadius - mAnimatedValue * 3 * mRadius, mCenterY,
                        mRadius, mPaint);
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mValueAnimator && mValueAnimator.isRunning()) {
            mValueAnimator.cancel();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (null != mValueAnimator && !mValueAnimator.isRunning()) {
            mValueAnimator.start();
        }
    }
}