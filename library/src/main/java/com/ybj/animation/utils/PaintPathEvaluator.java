package com.ybj.animation.utils;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * 利用网络上提供的算法计算出贝塞尔曲线上每一个点的坐标
 * Created by jackYang on 2017/1/1.
 */

public class PaintPathEvaluator implements TypeEvaluator<PointF> {
    private PointF flagPoint;

    public PaintPathEvaluator(PointF flagPointF) {
        flagPoint = flagPointF;
    }

    @Override
    public PointF evaluate(float v, PointF pointF, PointF t1) {
        return BezierUtil.CalculateBezierPointForQuadratic(v, pointF, flagPoint, t1);
    }
}
