package com.angelstar.animation;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by jackyang on 2017/1/4.
 */

public class PicPointEvaluator implements TypeEvaluator<PointF> {
    private PointF flagPointF1;
    private PointF flagPointF2;

    /**
     * 通过构造传入两个控制点
     *
     * @param flagPointF1
     * @param flagPointF2
     */
    public PicPointEvaluator(PointF flagPointF1, PointF flagPointF2) {
        this.flagPointF1 = flagPointF1;
        this.flagPointF2 = flagPointF2;
    }

    /**
     * 利用 CalculateBezierPointForCubic 算法计算出三阶贝塞尔曲线上任意点
     *
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        return BezierUtil.CalculateBezierPointForCubic(fraction, startValue, flagPointF1, flagPointF2, endValue);
    }
}
