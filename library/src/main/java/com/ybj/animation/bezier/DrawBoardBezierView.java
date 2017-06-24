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
 * 利用贝塞尔曲线来优化绘图板的菱角
 * Created by jackYang on 2016/12/31.
 */
public class DrawBoardBezierView extends View {

    //两个零时坐标
    private float mFlagX;
    private float mFlagY;

    private Path mPath;
    private Paint mPaint;

    public DrawBoardBezierView(Context context) {
        super(context);
    }

    public DrawBoardBezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);

        mPath = new Path();
    }

    public DrawBoardBezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mFlagX = event.getX();
                mFlagY = event.getY();
                mPath.moveTo(mFlagX, mFlagY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                //利用贝塞尔曲线来优化
                float centerX = (x + mFlagX) / 2;
                float centerY = (y + mFlagY) / 2;
                mPath.quadTo(mFlagX, mFlagY, centerX, centerY);
                mFlagX = x;
                mFlagY = y;
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(mPath, mPaint);
    }
}
