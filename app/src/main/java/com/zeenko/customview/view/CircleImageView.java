package com.zeenko.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.zeenko.customview.R;

import java.util.ArrayList;
import java.util.List;

public class CircleImageView extends FrameLayout {
    private final int SIZE = 6;
    private final int CIRCLE_DIAMETER = 125;
    private List<ImageView> views;
    private List<ImageView> strokes;
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

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        paint = new Paint();
        paint.setColor(Color.RED);
        if (attrs != null) {
            views = new ArrayList<>(SIZE);
            strokes = new ArrayList<>(SIZE);
            generateImageViews(context, attrs, views);
            generateImageViews(context, attrs, strokes);
            addRootCircle(context);
            placeViews(context);
        }
        setClickable(true);
        setFocusable(true);
    }

    private void addRootCircle(Context context) {
        setImageToView(views.get(0), R.drawable.img_3);
        LayoutParams params = getLayoutParamsWithOffset(centerCircleSize(), 0, 0, 0, 0);
        views.get(0).setLayoutParams(params);
        views.get(0).setOnClickListener((view) -> {
            animateStroke();
        });
        addChildImageViewByIndex(0, views, 0);
    }

    private void placeViews(Context context) {
        for (int i = 0; i < SIZE; i++) {
            views.get(i).setBackground(ContextCompat.getDrawable(context, R.drawable.rounded));
        }

        placeStrokes();
        int index = 1;
        int elevation = 12;
        LayoutParams params = getLayoutParamsWithOffset(0, 0, centerCircleSize() + 50, 0);
        views.get(1).setLayoutParams(params);
        setImageToView(views.get(index), R.drawable.img_2);
        addChildImageViewByIndex(index++, views, getDp(elevation));

        params = getLayoutParamsWithOffset(0, 0, centerCircleSize() - 25, centerCircleSize() - 25);
        views.get(2).setLayoutParams(params);
        addChildImageViewByIndex(index, views, getDp(elevation));
        setImageToView(views.get(index++), R.drawable.img_1);

        params = getLayoutParamsWithOffset(0, 0, 0, centerCircleSize() + 50);
        views.get(3).setLayoutParams(params);
        addChildImageViewByIndex(index, views, getDp(elevation));
        setImageToView(views.get(index++), R.drawable.img_2);

        params = getLayoutParamsWithOffset(centerCircleSize() - 25, 0, 0, centerCircleSize() - 25);
        views.get(4).setLayoutParams(params);
        addChildImageViewByIndex(index, views, getDp(elevation));
        setImageToView(views.get(index++), R.drawable.img_1);

        params = getLayoutParamsWithOffset(centerCircleSize() + 50, 0, 0, 0);
        views.get(5).setLayoutParams(params);
        views.get(5).setRotation(1);
        setImageToView(views.get(index), R.drawable.img_2);
        addChildImageViewByIndex(index, views, getDp(elevation));
    }

    private void placeStrokes() {
        for (int i = 0; i < SIZE; i++) {
            strokes.get(i).setImageResource(R.drawable.animated_stroke);
        }
        LayoutParams params = getLayoutParamsWithOffset(getDp(100), getDp(20), CIRCLE_DIAMETER, 0, 0, 0);
        strokes.get(0).setLayoutParams(params);
        addChildImageViewByIndex(0, strokes, 0);

        params = getLayoutParamsWithOffset(getDp(100), getDp(20), CIRCLE_DIAMETER, 0, 0, CIRCLE_DIAMETER);
        strokes.get(1).setLayoutParams(params);
        strokes.get(1).setRotation(315);
        addChildImageViewByIndex(1, strokes, 0);

        params = getLayoutParamsWithOffset(getDp(100), getDp(20), 0, 0, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
        strokes.get(2).setLayoutParams(params);
        strokes.get(2).setRotation(225);
        addChildImageViewByIndex(2, strokes, 0);
//        animateStroke();
    }

    private void addChildImageViewByIndex(int index, List<ImageView> views, float elevation) {
        ImageView imageView = views.get(index);
        ViewCompat.setElevation(imageView, elevation);
//        imageView.setElevation(elevation);
//        imageView.setTranslationZ(elevation);
        addView(imageView);
    }

    private void animateStroke() {
        ((Animatable) strokes.get(0).getDrawable()).start();
        ((Animatable) strokes.get(1).getDrawable()).start();
        ((Animatable) strokes.get(2).getDrawable()).start();
    }

    private int getDp(int pixels) {
        DisplayMetrics displaymetrics = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pixels, displaymetrics);
    }

    private void setImageToView(ImageView img, @DrawableRes int resId) {
        img.setImageResource(resId);
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

    private void generateImageViews(Context context, @NonNull AttributeSet attrs, List<ImageView> views) {
        for (int i = 0; i < SIZE; i++) {
            views.add(new ImageView(context, attrs));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(450, 450, 100, paint);
    }

//    private List<LayoutParams> getListLayoutParams() {
//        List<LayoutParams> layoutParams = new ArrayList<>(SIZE);
//        int topOffset = 0;
//        int leftOffset = 0;
//        int rightOffset = 0;
//        int bottomOffset = 0;
//
//        for (int i = 1; i < SIZE; i++) {
//            if ((i % 3) == 0) {
//
//            } else if ((i % 2) == 0) {
//
//            } else if (i == 6) {
//
//            }
//
//            layoutParams.add(getLayoutParamsWithOffset(leftOffset, topOffset, rightOffset, bottomOffset));
//        }
//        return layoutParams;
//    }

}
