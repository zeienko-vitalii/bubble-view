package com.zeenko.customview.view;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.zeenko.customview.R;

import java.util.ArrayList;
import java.util.List;

public class CircleImageView extends FrameLayout {
    private final int SIZE = 6;
    private final int CIRCLE_DIAMETER = 125;
    private List<View> views;
    private List<View> strokes;
    private Paint paint;

    public CircleImageView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public CircleImageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        paint = new Paint();
        paint.setColor(Color.RED);
        if (attrs != null) {
            views = new ArrayList<>(SIZE);
            strokes = new ArrayList<>(SIZE);
            generateViews(context, attrs, views);
            generateViews(context, attrs, strokes);
            addRootCircle(context);
            placeViews(context);
        }
        setClickable(true);
        setFocusable(true);
    }

    private void addRootCircle(Context context) {
        views.get(0).setBackground(ContextCompat.getDrawable(context, R.drawable.rounded));
        LayoutParams params = getLayoutParamsWithOffset(centerCircleSize(), 0, 0, 0, 0);
        views.get(0).setLayoutParams(params);
        addChildByIndex(0, views);
    }

    private void placeViews(Context context) {
        for (int i = 0; i < SIZE; i++) {
            if (i == 0) continue;
            views.get(i).setBackground(ContextCompat.getDrawable(context, R.drawable.rounded));
        }

        placeStrokes(context);

        LayoutParams params = getLayoutParamsWithOffset(0, 0, centerCircleSize() + 50, 0);
        views.get(1).setLayoutParams(params);
        addChildByIndex(1, views);

        params = getLayoutParamsWithOffset(0, 0, centerCircleSize() - 25, centerCircleSize() - 25);
        views.get(2).setLayoutParams(params);
        addChildByIndex(2, views);

        params = getLayoutParamsWithOffset(0, 0, 0, centerCircleSize() + 50);
        views.get(3).setLayoutParams(params);
        addChildByIndex(3, views);

        params = getLayoutParamsWithOffset(centerCircleSize() - 25, 0, 0, centerCircleSize() - 25);
        views.get(4).setLayoutParams(params);
        addChildByIndex(4, views);

        params = getLayoutParamsWithOffset(centerCircleSize() + 50, 0, 0, 0);
        views.get(5).setLayoutParams(params);
        views.get(5).setRotation(1);
        addChildByIndex(5, views);

    }

    private void placeStrokes(Context context) {
        for (int i = 0; i < SIZE; i++) {
            strokes.get(i).setBackground(ContextCompat.getDrawable(context, R.drawable.stroke));
        }

        final LayoutParams params = getLayoutParamsWithOffset(150, 5, 0, 0, 0, CIRCLE_DIAMETER);

        strokes.get(0).setLayoutParams(params);
        strokes.get(0).setRotation(90);
        addChildByIndex(0, strokes);

        animateStroke();

//        strokes.get(2).setLayoutParams(params);
//        strokes.get(2).setRotation();
//        addChildByIndex(2, strokes);

//        strokes.get(3).setLayoutParams(params);
//        strokes.get(1).setRotation(90);
//        addChildByIndex(3, strokes);

//        strokes.get(4).setLayoutParams(params);
//        strokes.get(1).setRotation(90);
//        addChildByIndex(4, strokes);

//        strokes.get(5).setLayoutParams(params);
//        strokes.get(1).setRotation(90);
//        strokes.get(5).setRotation(1);
//        addChildByIndex(5, strokes);
    }

    private void animateStroke() {
        final String PROPERTY_WIDTH = "PROPERTY_WIDTH";
        final String PROPERTY_MARGIN_BOTTOM = "PROPERTY_MARGIN_BOTTOM";
        PropertyValuesHolder propertyWidth = PropertyValuesHolder.ofInt(PROPERTY_WIDTH, 0, 250);
        PropertyValuesHolder propertyaMarginBottom = PropertyValuesHolder.ofInt(PROPERTY_MARGIN_BOTTOM, 0, 100);
        ValueAnimator animator = new ValueAnimator();
        animator.setValues(propertyWidth, propertyaMarginBottom);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int width = (int) animation.getAnimatedValue(PROPERTY_WIDTH);
                int marginBottom = (int) animation.getAnimatedValue(PROPERTY_MARGIN_BOTTOM);
                LayoutParams params1 = getLayoutParamsWithOffset(width, 5, 0, 0, 0, marginBottom);
                updateViewLayout(strokes.get(0), params1);
                invalidate();
            }
        });
        animator.start();
    }


    private List<LayoutParams> getListLayoutParams() {
        List<LayoutParams> layoutParams = new ArrayList<>(SIZE);
        int topOffset = 0;
        int leftOffset = 0;
        int rightOffset = 0;
        int bottomOffset = 0;

        for (int i = 1; i < SIZE; i++) {
            if ((i % 3) == 0) {

            } else if ((i % 2) == 0) {

            } else if (i == 6) {

            }

            layoutParams.add(getLayoutParamsWithOffset(leftOffset, topOffset, rightOffset, bottomOffset));
        }
        return layoutParams;
    }

    private LayoutParams getLayoutParamsWithOffset(int left, int top, int right, int bottom) {
        LayoutParams params = new LayoutParams(
                CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        params.setMargins(left, top, right, bottom);
        return params;
    }

    private LayoutParams getLayoutParamsWithOffset(int width, int height, int left, int top, int right, int bottom) {
        LayoutParams params = new LayoutParams(
                width, height);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        params.setMargins(left, top, right, bottom);
        return params;
    }

    private LayoutParams getLayoutParamsWithOffset(int size, int left, int top, int right, int bottom) {
        LayoutParams params = new LayoutParams(
                size, size);
        params.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        params.setMargins(left, top, right, bottom);
        return params;
    }

    private int centerCircleSize() {
        return (int) (CIRCLE_DIAMETER * 1.5);
    }

    private void addChildByIndex(int index, List<View> views) {
        addView(views.get(index));
    }

    private void generateViews(Context context, @NonNull AttributeSet attrs, List<View> views) {
        for (int i = 0; i < SIZE; i++) {
            views.add(new View(context, attrs));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(450, 450, 100, paint);

    }
}
