package com.angelstar.animation.item.userlike;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.angelstar.animation.R;

import java.util.Random;

/**
 * 自定义点赞动画
 * Created by jackYang on 2017/1/4.
 */
public class UserLikeAnimationView extends RelativeLayout {

    //存放心形图片的数组
    private Drawable[] mDrawables;
    //设置不同的差值器，使动画看起来更具有随机性
    private Interpolator mLine = new LinearInterpolator();//线性
    private Interpolator mAcc = new AccelerateInterpolator();//加速
    private Interpolator mDec = new DecelerateInterpolator();//减速
    private Interpolator mAccAndDec = new AccelerateDecelerateInterpolator();//先加速再减速
    //创建一个存放差速器的数组
    private Interpolator[] mInterpolator;
    //需要一个属性动画改变运动轨迹
    private ValueAnimator mValueAnimator;
    //图片真实的宽高
    private int mMPicWidth;
    private int mMPicHeight;
    //图片参数
    private LayoutParams mLayoutParams;
    //获取屏幕的宽高
    private int mHeight;
    private int mWidth;
    //上下文
    private Context mContext;
    //产生一个随机数
    Random mRandom = new Random();

    public UserLikeAnimationView(Context context) {
        super(context);
        initView(context);
    }

    public UserLikeAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public UserLikeAnimationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * 初始化所需要的工具
     */
    private void initView(Context context) {
        mContext = context;
        mDrawables = new Drawable[3];
        //获取本地的图片资源
        Drawable mBlue = getResources().getDrawable(R.mipmap.pl_blue);
        Drawable mRed = getResources().getDrawable(R.mipmap.pl_red);
        Drawable mYellow = getResources().getDrawable(R.mipmap.pl_yellow);
        mDrawables[0] = mRed;
        mDrawables[1] = mBlue;
        mDrawables[2] = mYellow;
        //获取图片真实的宽高,用户后面的计算（这3张大小是一样的
        mMPicWidth = mBlue.getIntrinsicWidth();
        mMPicHeight = mBlue.getIntrinsicHeight();
        mLayoutParams = new LayoutParams(mMPicWidth, mMPicHeight);
        mLayoutParams.addRule(CENTER_HORIZONTAL,TRUE); //居中
        mLayoutParams.addRule(ALIGN_PARENT_BOTTOM, TRUE);//父布局底部
        //将差速器添加到数组
        mInterpolator = new Interpolator[4];
        mInterpolator[0] = mLine;
        mInterpolator[1] = mAcc;
        mInterpolator[2] = mDec;
        mInterpolator[3] = mAccAndDec;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    /**
     * 该方法在外面用户自己调用开启
     */
    public void addHeart() {
        ImageView imageView = new ImageView(getContext());
        //随机选择一个图片
        imageView.setImageDrawable(mDrawables[mRandom.nextInt(3)]);
        imageView.setLayoutParams(mLayoutParams);
        addView(imageView);
        //获取随机动画
        Animator mAnimator = getAnimator(imageView);
        mAnimator.addListener(new AnimatorEndListener(imageView));
        //用户调用才开始执行动画
        mAnimator.start();
    }

    /**
     * 给图片设置动画
     *
     * @param imageView 需要做动画的控件，如图片
     * @return 返回一个动画的集合
     */
    private Animator getAnimator(View imageView) {
        //设置第一部分开始动画
        AnimatorSet mAnimatorSet = getStartAnimator(imageView);
        //设置移动动画
        ValueAnimator mBezierMovingValueAnimator = getBezierMovingValueAnimator(imageView);

        AnimatorSet mFinallyAnimSet = new AnimatorSet();
        //顺序播放动画
        mFinallyAnimSet.playSequentially(mAnimatorSet, mBezierMovingValueAnimator);
        mFinallyAnimSet.setInterpolator(mInterpolator[mRandom.nextInt(4)]);//随机选择一个差速器
        mAnimatorSet.setTarget(imageView);

        return mFinallyAnimSet;
    }

    /**
     * 第一部分：设置控件开始显示的动画
     *
     * @param target
     * @return
     */
    private AnimatorSet getStartAnimator(View target) {
        ObjectAnimator mAlphaAnim = ObjectAnimator.ofFloat(target, View.ALPHA, 0.2f, 1f);//透明度
        ObjectAnimator mScaleXAnim = ObjectAnimator.ofFloat(target, View.SCALE_X, 0.2f, 1f);//X轴缩放
        ObjectAnimator mScaleYAnim = ObjectAnimator.ofFloat(target, View.SCALE_Y, 0.2f, 1f);//Y轴缩放动画;
        AnimatorSet mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(500);
        mAnimatorSet.setInterpolator(mLine);
        mAnimatorSet.playTogether(mAlphaAnim, mScaleXAnim, mScaleYAnim);
        mAnimatorSet.setTarget(target);
        return mAnimatorSet;
    }

    /**
     * 第二部分：设置控件(图片)移动过程的轨迹动画(三阶贝塞尔曲线)
     *
     * @param target 目标控件
     * @return
     */
    private ValueAnimator getBezierMovingValueAnimator(View target) {
        PicPointEvaluator picPointEvaluator = new PicPointEvaluator(getPointF(2), getPointF(1));
        mValueAnimator = ValueAnimator.ofObject(picPointEvaluator,
                new PointF((mWidth - mMPicWidth) / 2, mHeight - mMPicHeight),
                new PointF(mRandom.nextInt(mWidth), 0));
        mValueAnimator.setDuration(3000);
        mValueAnimator.setTarget(target);
        mValueAnimator.addUpdateListener(new LikeAnimatorUpdateListener(target));
        return mValueAnimator;
    }

    /**
     * 计算出三阶贝塞尔曲线所需要的两个控制点
     *
     * @param i 通过 i 来判断是那个点，因为第二个点在上面Y坐标需要相应的改变
     * @return 返回控制点
     */
    private PointF getPointF(int i) {
        //控制点的X范围为0到屏幕的宽度减去100
        int mFlagX = mRandom.nextInt(mWidth - 100);
        //控制点的Y坐标其实也并非要是屏幕高度的一半，自己看情况来做
        int mFlagY = mRandom.nextInt(mHeight - 100 / i);
        return new PointF(mFlagX, mFlagY);
    }

    /**
     * 实现动画运动过程中的监听
     */
    private class LikeAnimatorUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private View target;

        public LikeAnimatorUpdateListener(View target) {
            this.target = target;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            PointF mPointF = (PointF) animation.getAnimatedValue();
            target.setX(mPointF.x);
            target.setY(mPointF.y);
            //在向上移动的过程中在做一个透明度减少的动画
            target.setAlpha(1 - animation.getAnimatedFraction());
        }
    }

    /**
     * 动画执行过程中的监听
     */
    private class AnimatorEndListener extends AnimatorListenerAdapter {
        private View mView;

        public AnimatorEndListener(View view) {
            mView = view;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            //因为用户不停的点赞也就会不停的添加View，导致内存吃不消。
            //所以当一个动画中执行结束的时候，我们应该将View移除掉
            removeView(mView);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            super.onAnimationCancel(animation);
            //取消的时候也应该将控件移除
           removeView(mView);
        }
    }
}
