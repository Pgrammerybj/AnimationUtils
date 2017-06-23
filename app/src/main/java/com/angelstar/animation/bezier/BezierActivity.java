/**
 * jackyang   2016-12-30 20:28
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.angelstar.animation.R;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-30 20:28
 */
public class BezierActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_beziers);
    }

    public void goSecondBezier(View view) {
        startActivity(new Intent(this, SecondBezierActivity.class));
    }

    public void goThirdBezier(View view) {
        startActivity(new Intent(this, ThirdBezierActivity.class));
    }

    public void goDrawBoardBezier(View view) {
        startActivity(new Intent(this, DrawBoardActivity.class));
    }

    public void goPathMorphingBezier(View view) {
        startActivity(new Intent(this, PathMorphingActivity.class));
    }

    public void goWaveBezier(View view) {
        startActivity(new Intent(this, WaveBezierAcitvity.class));
    }

    public void goShoppingCartPathBezier(View view) {
        startActivity(new Intent(this, ShoppingCartPathActivity.class));
    }

}