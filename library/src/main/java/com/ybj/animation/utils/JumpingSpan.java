package com.ybj.animation.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.text.style.ReplacementSpan;

public class JumpingSpan extends ReplacementSpan {

    private float translationX = 0;
    private float translationY = 0;

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fontMetricsInt) {
        return (int) paint.measureText(text, start, end);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, @NonNull Paint paint) {
        canvas.drawText(text, start, end, x + translationX, y + translationY, paint);
    }

    public void setTranslationX(float translationX) {
        this.translationX = translationX;
    }

    public void setTranslationY(float translationY) {
        this.translationY = translationY;
    }
}
