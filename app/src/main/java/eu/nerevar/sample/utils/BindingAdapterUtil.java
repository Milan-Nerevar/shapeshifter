package eu.nerevar.sample.utils;


import android.databinding.BindingAdapter;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.ImageView;

public final class BindingAdapterUtil {

    @BindingAdapter("colorTint")
    public static void setColorTint(ImageView view, int color) {
        DrawableCompat.setTint(view.getDrawable(), color);
    }

}
