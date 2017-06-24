/**
 * JackYang   2017-03-29 14:53
 * Copyright (c)2017 7see Co.Ltd. All right reserved.
 */
package com.ybj.animation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * 两个小球绕Y轴做动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-03-29 14:53
 */
public class TwoBallLoadAnimationView extends View {

    ///////////////////////////////////////////////////////////////////////////
    // {@link https://github.com/Pgrammerybj}
    ///////////////////////////////////////////////////////////////////////////

    //绕中心轴运动的坐标
    private float mCenterX;
    private float mCenterY;
    //小球最大和最小半径
    private static final int DEFAULT_BALL_MAX_RADIUS = 76;
    private static final int DEFAULT_BALL_MIX_RADIUS = 26;
    //运动的最大距离（范围+-100）
    private static final int MAX_MOTION_DISTANCE = 100;
    //位置的小球颜色
    private static final int DEFAULT_BALL_ONE_COLOR = Color.parseColor("#ffeb01");
    private static final int DEFAULT_BALL_TWO_COLOR = Color.parseColor("#e1473c");
    //小球一个周期运动时间
    private static final int DEFAULT_BALL_ANIMATION_TIME = 1000;
    //球的最大半径
    private int mMaxRadius = DEFAULT_BALL_MAX_RADIUS;
    //球的最小半径
    private int mMinRadius = DEFAULT_BALL_MIX_RADIUS;
    //小球运动距离
    private int distance = MAX_MOTION_DISTANCE;
    //画笔
    private Paint mPaint;
    //小球
    private Ball mBallOne;
    private Ball mBallTwo;
    //动画集合
    private AnimatorSet mAnimatorSet;

    public TwoBallLoadAnimationView(Context context) {
        this(context, null);
    }

    public TwoBallLoadAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TwoBallLoadAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initConfig();
    }

    private void initConfig() {
        //初始化两个小球的参数配置
        mBallOne = new Ball();
        mBallTwo = new Ball();
        mBallOne.setBallColor(DEFAULT_BALL_ONE_COLOR);
        mBallTwo.setBallColor(DEFAULT_BALL_TWO_COLOR);
        //初始化画笔工具
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //初始化动画
        configAnimation();
    }

    private void configAnimation() {
        //运动的一个周期的规律
        //1.球从左边的最小状态到Y轴中心最大位置-->变大
        //2.球从Y轴中心最大位置到右边的最小位置-->变小
        //3.从最右变的最小位置往左到Y轴中心位置（最大+最小／2） 新的半径 极小 -->变小
        //4.从Y轴中心位置到往左到起始位置最左边-->变大 恢复到默认的最小状态
        //两个小球唯一的不同点在于 Y轴中心位置  一个是极小 一个是最大

        //小球的半径
        float mCenterRadius = (mMaxRadius + mMinRadius) * 0.5f;

        //第一个小球的 缩放动画和位移动画
        ObjectAnimator mBallOneScale = ObjectAnimator.ofFloat(mBallOne, "radius", mCenterRadius, mMaxRadius, mCenterRadius, mMinRadius, mCenterRadius);
        //无限循环
        mBallOneScale.setRepeatCount(ValueAnimator.INFINITE);
        ValueAnimator mBallOneTransform = ValueAnimator.ofFloat(-1, 0, 1, 0, -1);
        mBallOneTransform.setRepeatCount(ValueAnimator.INFINITE);
        mBallOneTransform.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (Float) animation.getAnimatedValue();
                float x = mCenterX + (distance) * animatedValue;
                mBallOne.setBallCenter(x);
                //不停的重新绘制
                invalidate();
            }
        });

        //第二个小球的动画
        ObjectAnimator mBallTwoScale = ObjectAnimator.ofFloat(mBallTwo, "radius", mCenterRadius, mMinRadius, mCenterRadius, mMaxRadius, mCenterRadius);
        mBallTwoScale.setRepeatCount(ValueAnimator.INFINITE);
        ValueAnimator mBallTwoTransform = ValueAnimator.ofFloat(1, 0, -1, 0, 1);
        mBallTwoTransform.setRepeatCount(ValueAnimator.INFINITE);
        mBallTwoTransform.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedValue = (Float) animation.getAnimatedValue();
                float x = mCenterX + (distance) * animatedValue;
                mBallTwo.setBallCenter(x);
                invalidate();
            }
        });

        //设置动画集合两个球4的动画
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(mBallOneScale, mBallOneTransform, mBallTwoScale, mBallTwoTransform);
        mAnimatorSet.setDuration(DEFAULT_BALL_ANIMATION_TIME);
        //差值器开始快结束慢
        //弹簧运动的算法(Math.sin((Math.PI * 2) * (input * (input * 0.4 + 0.6)) / mTimes) * (1.0f - input) * 0.40 + input * 0.35 + 0.65);
        mAnimatorSet.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldWidth, int oldHeight) {
        super.onSizeChanged(w, h, oldWidth, oldHeight);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mCenterX = getWidth() / 2;
        mCenterY = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 绘制两个小球，小的先绘制，大的后绘制，这样考虑到连个小球叠加的时候的大的需要遮挡住小球（可考虑图层，不过不会啊）
        if (mBallOne.getRadius() > mBallTwo.getRadius()) {
            mPaint.setColor(mBallTwo.getBallColor());
            canvas.drawCircle(mBallTwo.getBallCenter(), mCenterY, mBallTwo.getRadius(), mPaint);
            mPaint.setColor(mBallOne.getBallColor());
            canvas.drawCircle(mBallOne.getBallCenter(), mCenterY, mBallOne.getRadius(), mPaint);
        } else {
            mPaint.setColor(mBallOne.getBallColor());
            canvas.drawCircle(mBallOne.getBallCenter(), mCenterY, mBallOne.getRadius(), mPaint);
            mPaint.setColor(mBallTwo.getBallColor());
            canvas.drawCircle(mBallTwo.getBallCenter(), mCenterY, mBallTwo.getRadius(), mPaint);
        }
    }

    @Override
    public void setVisibility(int visibility) {
        if (getVisibility() != visibility) {
            super.setVisibility(visibility);
            if (visibility == GONE || visibility == INVISIBLE) {
                //停止动画
                stopAnimator();
            } else {
                //开始动画
                startAnimator();
            }
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == GONE || visibility == INVISIBLE) {
            stopAnimator();
        } else {
            startAnimator();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimator();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimator();
    }

    private void startAnimator() {
        if (getVisibility() != VISIBLE) return;
        if (null == mAnimatorSet) return;
        if (mAnimatorSet.isRunning()) return;
        mAnimatorSet.start();
    }

    private void stopAnimator() {
        if (null != mAnimatorSet) mAnimatorSet.end();
    }

    class Ball {
        //半径
        private float mRadius;
        //颜色
        private int mBallColor;
        //圆心
        private float mBallCenter;

        public float getRadius() {
            return mRadius;
        }

        public void setRadius(float radius) {
            mRadius = radius;
        }

        public int getBallColor() {
            return mBallColor;
        }

        public void setBallColor(int ballColor) {
            mBallColor = ballColor;
        }

        public float getBallCenter() {
            return mBallCenter;
        }

        public void setBallCenter(float ballCenter) {
            mBallCenter = ballCenter;
        }
    }
}