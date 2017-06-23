package com.angelstar.animation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.bezier.BezierActivity;
import com.angelstar.animation.pathmeasure.PathMeasureActivity;
import com.angelstar.animation.vectordrawable.VectorsActivity;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("各种动画效果");
    }

    /** 测试VectorDrawable案例 */
    public void goVectorDemo(View view) {
        startActivity(new Intent(this, VectorsActivity.class));
    }

    public void goBezierDemo(View view) {
        startActivity(new Intent(this, BezierActivity.class));
    }

    public void goPathMeasureDemo(View view) {
        startActivity(new Intent(this, PathMeasureActivity.class));
    }

    public void goTenAnimation(View view) {
        startActivity(new Intent(this, TenAnimation.class));
    }

    public void goCustomRatingBar(View view) {
        startActivity(new Intent(this, CustomRatingBarActivity.class));
    }
}
