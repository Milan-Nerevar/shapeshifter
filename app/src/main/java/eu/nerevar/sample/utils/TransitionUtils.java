package eu.nerevar.sample.utils;


import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.transition.ChangeBounds;

public class TransitionUtils {

    /**
     * Builds a change bounds animation if api level is at least Lollipop
     *
     * @param duration duration of transition in ms
     * @return change bounds transition
     */
    @Nullable
    public static ChangeBounds changeBoundsAnimation(@IntRange(from = 0) final int duration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final ChangeBounds transition = new ChangeBounds();
            transition.setDuration(duration);
            return transition;
        }

        return null;
    }

}
