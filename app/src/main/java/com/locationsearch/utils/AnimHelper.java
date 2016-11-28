package com.locationsearch.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import com.locationsearch.R;

/**
 *
 */
public class AnimHelper {
    /**
     * @param view View
     */
    public static void applyScale(final View view) {
        if (view == null)
            return;
        Animation performAnimation = AnimationUtils.loadAnimation(
                view.getContext(), R.anim.scale);
        performAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        view.startAnimation(performAnimation);
    }


    public static void applyEntry(final View view) {
        if (view == null)
            return;
        Animation performAnimation = AnimationUtils.loadAnimation(
                view.getContext(), R.anim.enter);
        performAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });
        view.startAnimation(performAnimation);
    }

    public static void applyExit(final View view, final CallBack callBack) {
        if (view == null)
            return;
        Animation performAnimation = AnimationUtils.loadAnimation(
                view.getContext(), R.anim.right_exit);
        performAnimation.setAnimationListener(new AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                if (callBack != null)
                    callBack.finish();

            }
        });
        view.startAnimation(performAnimation);
    }

    public static void scaleView(View v, float startScale, float endScale) {
        try {
            Animation anim = new ScaleAnimation(
                    1f, 1f, // Start and end values for the X axis scaling
                    startScale, endScale, // Start and end values for the Y axis scaling
                    Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                    Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
            anim.setFillAfter(true); // Needed to keep the result of the animation
            v.startAnimation(anim);
        } catch (Exception e) {

        }
    }

    public interface CallBack {
        void finish();
    }

}
