/**
 * jackyang   2017-01-04 16:34
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.item.userlike;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.R;

/**
 * 直播应用点赞效果动画，心形图片可以替换
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-04 16:34
 */
public class UserLikeAnimation extends AppCompatActivity implements View.OnClickListener {

    private UserLikeAnimationView mUserLikeAnimator;

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_userlikeanimation);
        mUserLikeAnimator = (UserLikeAnimationView) findViewById(R.id.like);
        mUserLikeAnimator.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mUserLikeAnimator.addHeart();
    }
}