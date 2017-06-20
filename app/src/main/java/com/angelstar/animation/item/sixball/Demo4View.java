package com.angelstar.animation.item.sixball;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class Demo4View extends View {

    private Paint mPaint;
    private int mPointCount = 6, mPointRadius = 25, mTranslateX, mTranslateY, mWidth, mHeight, mCenterY;
    private float mFraction = 0, mPathLength;
    private ValueAnimator mAnimator;
    private boolean mThroughAbove = true;
    private PathMeasure mPathMeasure;
    private Path mPath;
    private float[] mPos = new float[2];// set position

    public Demo4View(Context context) {
        super(context);
    }

    public Demo4View(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLACK);
        // bezier
        mPath = new Path();
        mPathMeasure = new PathMeasure();
        mAnimator = ValueAnimator.ofFloat(0, 1);
        long duration = 700;
        mAnimator.setDuration(duration);
        mAnimator.setInterpolator(new DecelerateInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFraction = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
                mThroughAbove = !mThroughAbove;
            }
        });
        mAnimator.setRepeatCount(Integer.MAX_VALUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        mWidth = (3 * mPointCount + 1) * mPointRadius;// gap == radius
        mHeight = mPointRadius * 2 * 10;// 3 circle height
        if (widthMode != MeasureSpec.AT_MOST && heightMode != MeasureSpec.AT_MOST) {
            if (width < mWidth)
                width = mWidth;
            if (height < mHeight)
                height = mHeight;
        } else if (widthMeasureSpec != MeasureSpec.AT_MOST) {
            if (width < mWidth)
                width = mWidth;
        } else if (heightMeasureSpec != MeasureSpec.AT_MOST) {
            if (height < mHeight)
                height = mHeight;
        }
        mTranslateX = width / 2 - mWidth / 2;
        mTranslateY = height / 2 - mHeight / 2;
        mCenterY = mHeight / 2;
        mPath.moveTo(mPointRadius * 2, mCenterY);
        mPath.cubicTo(mPointRadius * 2, mCenterY, mWidth / 2, 0, mWidth - mPointRadius * 2, mCenterY);
        mPathMeasure.setPath(mPath, false);
        mPathLength = mPathMeasure.getLength();
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mTranslateX, mTranslateY);
        for (int i = 0; i < mPointCount; i++) {
            if (i == 0) {
                mPathMeasure.getPosTan(mPathLength * mFraction, mPos, null);
                canvas.drawCircle(mPos[0], mThroughAbove ? mPos[1] : mHeight - mPos[1], mPointRadius, mPaint);//go left
            } else {
                canvas.drawCircle((2 + 3 * i) * mPointRadius - mFraction * mPointRadius * 3, mCenterY, mPointRadius, mPaint);//go left
            }
        }
    }

    public void startLoading() {
        if (!mAnimator.isRunning())
            mAnimator.start();
    }

    public void stopLoading() {
        if (mAnimator.isRunning())
            mAnimator.cancel();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startLoading();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopLoading();
    }
}
