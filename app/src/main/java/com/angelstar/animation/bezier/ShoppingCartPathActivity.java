/**
 * jackyang   2017-01-01 16:16
 * Copyright (c)2017 AngelStar Co.Ltd. All right reserved.
 */
package com.angelstar.animation.bezier;

import android.os.Bundle;

import com.angelstar.animation.BaseActivity;
import com.angelstar.animation.R;

/**
 * Bezier实现的购物车轨迹动画
 *
 * @author jackyang
 * @version 1.0.0
 * @since 2017-01-01 16:16
 */
public class ShoppingCartPathActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_shoppingcartpathbezier);
        initToolBar(true, R.string.bezier_shopping_cart);
    }
}