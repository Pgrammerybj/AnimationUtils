/**
 * jackyang   2017-01-04 16:24
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.angelstar.animation.item.sixball.SixBallLoadingAnimation;
import com.angelstar.animation.item.smallball.SmallBallAnimation;
import com.angelstar.animation.item.twoball.TwoBallAnimation;
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
     *
     * @param view
     */
    public void goItemSmallBall(View view) {
        startActivity(new Intent(this, SmallBallAnimation.class));
    }

    /**
     * 两个小球绕着Y轴往复运动
     *
     * @param view
     */
    public void goItemTwoBall(View view) {
        startActivity(new Intent(this, TwoBallAnimation.class));
    }

    public void goItemSixBall(View view) {
        startActivity(new Intent(this,SixBallLoadingAnimation.class));
    }
}
