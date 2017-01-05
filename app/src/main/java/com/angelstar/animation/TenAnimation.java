/**
 * jackyang   2017-01-04 16:24
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.item.smallball.SmallBallAnimation;
import com.angelstar.animation.item.userlike.UserLikeAnimation;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-04 16:24
 */
public class TenAnimation extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_tenanimation);
    }

    /**
     * 点赞动画
     *
     * @param view
     */
    public void goItemUserLike(View view) {
        startActivity(new Intent(this, UserLikeAnimation.class));
    }

    /**
     * 小球自由落落体的加载动画
     * @param view
     */
    public void goItemSmallBall(View view) {
        startActivity(new Intent(this,SmallBallAnimation.class));
    }
}