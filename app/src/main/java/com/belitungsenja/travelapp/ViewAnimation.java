/*
 * Created by Android Rion on 9/15/19 10:28 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/15/19 10:27 PM
 * Kunjungi androidrion.com untuk tutorial Android Studio
 */

package com.belitungsenja.travelapp;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

public class ViewAnimation {


    public static void fadeOutIn(View view) {
        view.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 0.f, 0.5f, 1.f);
        ObjectAnimator.ofFloat(view, "alpha", 0.f).start();
        animatorAlpha.setDuration(500);
        animatorSet.play(animatorAlpha);
        animatorSet.start();
    }

}
