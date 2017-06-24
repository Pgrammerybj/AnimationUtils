package com.ybj.animation.path;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 使用PathMeasure的getPosTan()方法来获取轨迹上点的坐标和点所对应的切线角度
 * Created by jackYang on 2017/1/3.
 */

public class PathMeasureGetPosTanView extends View implements View.OnClickListener {

    private static final String TAG = PathMeasureGetPosTanView.class.getSimpleName();
    private Path mCirclePath;
    private Paint mPaint;
    private Paint mTextPaint;
    private Paint mSectorPaint;
    private PathMeasure mPathMeasure;
    private ValueAnimator mValueAnimator;
    //属性动画产生的值
    private float mFlagValue;
    //给getPosTan()使用的两个数组
    private float[] mPos;
    private float[] mTan;
    //动画是否开始
    private boolean isStart = false;
    //是否是第一次动画
    private boolean isFirst = true;

    public PathMeasureGetPosTanView(Context context) {
        super(context);
    }

    public PathMeasureGetPosTanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(6);
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(44);
        mTextPaint.setStrokeWidth(0);
        mTextPaint.setColor(Color.CYAN);
        mTextPaint.setStyle(Paint.Style.STROKE);

        mSectorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSectorPaint.setStrokeWidth(6);
        mSectorPaint.setColor(Color.BLUE);
        mSectorPaint.setStyle(Paint.Style.FILL);

        mCirclePath = new Path();
        //Path.Direction.CW:顺时针
        mCirclePath.addCircle(0, 0, 200, Path.Direction.CW);
        mPathMeasure = new PathMeasure();
        //true为封闭，这是圆形所以true，false无所谓了
        mPathMeasure.setPath(mCirclePath, true);

        mPos = new float[2];
        mTan = new float[2];

        //创建属性动画
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mValueAnimator.setDuration(6000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFlagValue = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        //点击启动动画
        setOnClickListener(this);
    }

    public PathMeasureGetPosTanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        if (isStart) {
            mValueAnimator.pause();
            isStart = false;
        } else {
            if (isFirst) {
                mValueAnimator.start();
                isFirst = false;
            } else {
                mValueAnimator.resume();
            }
            isStart = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //通过API计算出轨迹长度
        float mPathLength = mPathMeasure.getLength();
        //第一个参数就是一个比例值
        mPathMeasure.getPosTan(mFlagValue * mPathLength, mPos, mTan);
        //获取路径上某点的切线角度
        float degree = (float) (Math.atan2(mTan[1], mTan[0]) * 180 / Math.PI);

        canvas.save();
        canvas.translate(560, 700);
        //获取轨迹上一点的坐标
        float x = mPos[0];
        float y = mPos[1];
        canvas.drawCircle(x, y, 10, mPaint);
        if (x >= 0) {
            canvas.drawText("切线", x + 30, y + 30, mTextPaint);
        } else {
            canvas.drawText("切线", x - 30, y + 30, mTextPaint);
        }
        //画出外面大圆的圆心(注意都是相对坐标)
        //canvas.drawCircle(0, 0, 6, mPaint);
        //画出圆心和切点的连线
        //canvas.drawLine(0, 0, x, y, mSectorPaint);
        //画出扇形 new RectF();定义扇形的大小以圆心为基准点
        RectF oval2 = new RectF(-200, -200, 200, 200);
        canvas.drawArc(oval2, 0, 360 * mFlagValue, true, mSectorPaint);
        //画出文字进度
        canvas.drawText(String.valueOf(Math.round(100 * mFlagValue)) + "%", 20, 20, mTextPaint);
        //画出大圆的轨迹
        canvas.drawPath(mCirclePath, mPaint);
        //旋转画布
        canvas.rotate(degree);
        //画出切线
        canvas.drawLine(0, -200, 400, -200, mPaint);
        canvas.restore();
    }
}
