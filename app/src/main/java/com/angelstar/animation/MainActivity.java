package com.angelstar.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.vectordrawable.Vectors;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 测试VectorDrawable案例
     * @param view
     */
    public void goVectorDemo(View view) {
        startActivity(new Intent(this, Vectors.class));
    }
}
