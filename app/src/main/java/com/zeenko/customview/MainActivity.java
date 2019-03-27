package com.zeenko.customview;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private VectorDrawableCompat myVectorGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        imageView = findViewById(R.id.ivStroke);
//        imageView.setOnClickListener((view) -> {
//            updateVd();
//            final Resources.Theme theme = getResources().newTheme();
//            theme.applyStyle(R.style.TranslateX2, false);
//            theme.applyStyle(R.style.TranslateX, false);
//            changeTheme(theme);
//        });
    }

    private void changeTheme(Resources.Theme theme) {
        final Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.vd_stroke, theme);
        imageView.setImageDrawable(drawable);
    }

    private void updateVd() {
        ((Animatable) imageView.getDrawable()).start();
//        final String PROPERTY_WIDTH = "PROPERTY_WIDTH";
//        final String PROPERTY_MARGIN_BOTTOM = "PROPERTY_MARGIN_BOTTOM";
//        PropertyValuesHolder propertyWidth = PropertyValuesHolder.ofInt(PROPERTY_WIDTH, 0, 350);
//        PropertyValuesHolder propertyaMarginBottom = PropertyValuesHolder.ofInt(PROPERTY_MARGIN_BOTTOM, 0, 100);
//        ValueAnimator animator = new ValueAnimator();
//        animator.setValues(propertyWidth, propertyaMarginBottom);
//        animator.setDuration(2000);
//        animator.addUpdateListener(animation -> {
//            int width = (int) animation.getAnimatedValue(PROPERTY_WIDTH);
//            int marginBottom = (int) animation.getAnimatedValue(PROPERTY_MARGIN_BOTTOM);
////            imageView.setRotation();
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(width, 39));
//        });
//        animator.start();
    }

}
