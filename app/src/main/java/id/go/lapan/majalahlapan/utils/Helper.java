package id.go.lapan.majalahlapan.utils;

import android.animation.ObjectAnimator;
import android.widget.RelativeLayout;
import com.github.aakira.expandablelayout.Utils;

public class Helper {
    public static ObjectAnimator changeRotate(RelativeLayout button, float from, float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(button, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(Utils.createInterpolator(Utils.LINEAR_INTERPOLATOR));
        return animator;
    }
}
