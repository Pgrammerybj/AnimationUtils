/**
 * jackyang   2017-01-04 16:24
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.item;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-04 16:24
 */
public class TenAnimationActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_tenanimation);
        initToolBar(true, R.string.type_10_animation);
    }

    public void goItemUserLike(View view) {
        startActivity(new Intent(this, UserLikeActivity.class));
    }

    public void goItemSmallBall(View view) {
        startActivity(new Intent(this, SmallBallActivity.class));
    }

    public void goItemTwoBall(View view) {
        startActivity(new Intent(this, TwoBallAnimationActivity.class));
    }

    public void goItemSixBall(View view) {
        startActivity(new Intent(this, SixBallLoadingActivity.class));
    }

    public void goItemTwoBallSticky(View view) {
        startActivity(new Intent(this, StickyControlsActivity.class));
    }

    public void goItemLoadingText(View view) {
        startActivity(new Intent(this, LoadingTextActivity.class));
    }
}
