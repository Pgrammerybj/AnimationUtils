/**
 * JackYang   2017-05-03 11:34
 * Copyright (c)2017 7see Co.Ltd. All right reserved.
 */
package com.ybj.animation.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.ybj.animation.R;

/**
 * 线条的左右中间动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-05-03 11:34
 */
public class LineAnimationView extends View {

    //绘制的线条的高度
    private int lineHeight = 10;
    //绘制的线条的宽度
    private int lineWidth = 200;
    //绘制的线条的颜色
    private int lineColor = Color.WHITE;
    //从左到右的动画
    public static final int ANIMATION_LEFT = 0;
    //从右到左的动画
    public static final int ANIMATION_RIGHT = 1;
    //从中间到两边的动画
    public static final int ANIMATION_CENTER = 2;
    //选择的动画方向
    public int mCurrentOrientation = ANIMATION_LEFT;
    //伸展动画开始缩减动画（开始和结束）
    public boolean isLong2Short = true;

    private Paint mPaint;
    private ValueAnimator mValueAnimator;
    public int mFlagValue;

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setCurrentOrientation(int currentOrientation) {
        mCurrentOrientation = currentOrientation;
    }

    public void setLong2Short(boolean long2Short) {
        isLong2Short = long2Short;
    }

    public LineAnimationView(Context context) {
        super(context);
    }

    public LineAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LineAnimationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttribute(context, attrs);
        initPain();
    }

    private void initAttribute(Context context, @Nullable AttributeSet attrs) {
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.LineAnimation);
        lineHeight = (int) mTypedArray.getDimension(R.styleable.LineAnimation_lineHeight, 20);
        lineWidth = (int) mTypedArray.getDimension(R.styleable.LineAnimation_lineWidth, 120);
        lineColor = mTypedArray.getColor(R.styleable.LineAnimation_lineColor, Color.WHITE);
        mCurrentOrientation = mTypedArray.getInteger(R.styleable.LineAnimation_mCurrentOrientation, ANIMATION_LEFT);
        isLong2Short = mTypedArray.getBoolean(R.styleable.LineAnimation_isLong2Short, true);
        mTypedArray.recycle();
    }

    private void initPain() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(lineHeight);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(lineColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (isLong2Short) {
            mValueAnimator = ValueAnimator.ofInt(lineWidth, 0);
        } else {
            mValueAnimator = ValueAnimator.ofInt(0, lineWidth);
        }
        mValueAnimator.setDuration(2600);
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mFlagValue = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrentOrientation == ANIMATION_LEFT) {
            //左边
            canvas.drawLine(0, 0, mFlagValue, 0, mPaint);
        } else if (mCurrentOrientation == ANIMATION_RIGHT) {
            //右边
            canvas.drawLine(lineWidth, 0, lineWidth - mFlagValue, 0, mPaint);
        } else if (mCurrentOrientation == ANIMATION_CENTER) {
            //中间动画
            canvas.drawLine(lineWidth, 0, mFlagValue, 0, mPaint);
            canvas.drawLine(lineWidth, 0, 2 * lineWidth - mFlagValue, 0, mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //解决继承View后自定义的View占据了父控件的全部大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int WidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int HeightMode = MeasureSpec.getSize(heightMeasureSpec);
        int HeightSize = MeasureSpec.getSize(heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}