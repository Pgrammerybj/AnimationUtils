/**
 * jackyang   2017-01-04 16:34
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.item;

import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;
import com.ybj.animation.UserLikeAnimationView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 直播应用点赞效果动画，心形图片可以替换
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-04 16:34
 */
public class UserLikeActivity extends BaseActivity {

    @BindView(R.id.like) UserLikeAnimationView mUserLikeAnimator;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_userlikeanimation);
        initToolBar(true, R.string.item_like_animation);
    }

    @OnClick(R.id.like)
    public void onClick(View v) {
        mUserLikeAnimator.addHeart();
    }
}