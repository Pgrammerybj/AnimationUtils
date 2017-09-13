/*
  * JackYang   2017-09-10 20:42
  * Copyright (c)2017 7see Co.Ltd. All right reserved. 
  *
  */
package com.angelstar.animation.item;

import android.os.Bundle;
import android.view.View;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;
import com.ybj.animation.ReceiveMessageAnimView;

/**
 * class description here@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-09-10 20:42
 */
public class ReceiveMessageActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_receive_message);
        setTitle(R.string.item_receive_message_animation);
        final ReceiveMessageAnimView animView = (ReceiveMessageAnimView) findViewById(R.id.rmv_animation);
        animView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animView.starAnimation();
            }
        });
    }
}