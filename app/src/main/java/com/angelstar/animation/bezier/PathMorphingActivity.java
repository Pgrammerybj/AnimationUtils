/**
 * jackyang   2016-12-31 14:52
 * Copyright (c)2016 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.bezier;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * Bezier实现的路径变化
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2016-12-31 14:52
 */
public class PathMorphingActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_pathmorphingbezier);
        initToolBar(true, R.string.bezier_path_morphing);
    }
}