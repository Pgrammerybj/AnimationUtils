/**
 * JackYang   2017-04-11 15:28
 * Copyright (c)2017 7see Co.Ltd. All right reserved.
 */
package com.angelstar.animation.item.sixball;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 六个小球从依次左到右加载动画@ybj
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-04-11 15:28
 */
public class SixBallLoadingView extends View {

    public SixBallLoadingView(Context context) {
        this(context, null);
    }

    public SixBallLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SixBallLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}