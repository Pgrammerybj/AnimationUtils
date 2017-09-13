/*
  * JackYang   2017-09-10 17:59
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.ybj.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频速配主页右上角接收消息的动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-09-10 17:59
 */
public class ReceiveMessageAnimView extends LinearLayout {

    private List<ImageView> list = new ArrayList<>();
    private RelativeLayout mBgAnimation;
    private RelativeLayout mRightBg;
    private TextView mLikeYou;

    public ReceiveMessageAnimView(Context context) {
        super(context);
        initialization(context);
    }

    public ReceiveMessageAnimView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialization(context);
    }

    public ReceiveMessageAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialization(context);
    }

    private void initialization(Context context) {
        LayoutInflater.from(context).inflate(R.layout.receive_video_animation, this);
        mBgAnimation = (RelativeLayout) findViewById(R.id.rl_bg_receive_message);
        mRightBg = (RelativeLayout) findViewById(R.id.rl_right);
        mLikeYou = (TextView) findViewById(R.id.tv_like_you);
        list.add((ImageView) findViewById(R.id.cir_icon_001));
        list.add((ImageView) findViewById(R.id.cir_icon_002));
        list.add((ImageView) findViewById(R.id.cir_icon_003));

    }

    //背景动画
    private void configBgAnimation(final boolean start) {
        ValueAnimator mAnim;
        if (start) {
            mAnim = ValueAnimator.ofFloat(1.0f, 0);
            mLikeYou.setVisibility(VISIBLE);
        } else {
            mAnim = ValueAnimator.ofFloat(0, 1.f);
            mLikeYou.setVisibility(GONE);
        }
        mAnim.setDuration(200);
        mAnim.start();
        mAnim.setStartDelay(1);
        mAnim.setInterpolator(new AccelerateInterpolator());
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                mBgAnimation.setVisibility(VISIBLE);
                mRightBg.setBackground(getResources().getDrawable(R.drawable.bg_plug_receive_message));
                mBgAnimation.setScaleX(1 - cVal);
                mBgAnimation.setTranslationX(cVal * mBgAnimation.getWidth() / 2.0f);
            }
        });
        mAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (start) {
                    for (int i = 0; i < list.size(); i++) {
                        configAnimation(list.get(i), i, true);
                    }
                } else {
                    mRightBg.setBackground(getResources().getDrawable(R.drawable.bg_plug_receive_message_circle));
                    mBgAnimation.setVisibility(GONE);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }
        });
    }

    //头像动画
    private void configAnimation(final ImageView imageView, final int index, final boolean start) {
        ValueAnimator mAnim;
        if (start) {
            //出现
            mAnim = ValueAnimator.ofFloat(0, 1.2F, 1.0F);
        } else {
            //消失
            mAnim = ValueAnimator.ofFloat(1.0F, 0);
        }
        mAnim.setDuration(500);
        mAnim.setStartDelay(index == 0 ? 1 : index * 180);//1 是处理第一次不走start监听（只要不为0）
        mAnim.setTarget(imageView);
        mAnim.start();
        mAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                imageView.setScaleX(cVal);
                imageView.setScaleY(cVal);
            }
        });
        mAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //先做背景动画
                if (!start && index == 0) {
                    configBgAnimation(false);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                list.get(index).setVisibility(VISIBLE);
            }
        });
    }

    /**
     * 结束动画
     */
    public void endAnimation() {
        for (int i = 0; i < list.size(); i++) {
            configAnimation(list.get(i), i, false);
        }
    }

    /**
     * 开始动画
     */
    public void starAnimation() {
        configBgAnimation(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                endAnimation();
            }
        }, 1000 * 3);
    }
}