package eu.nerevar.shapeshifter.core;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public final class Shapeshifter {

    /**
     * Navigation from activity
     */
    public static RequestManager with(@NonNull final AppCompatActivity activity) {
        return new RequestManager(activity);
    }

    /**
     * Navigation from fragment
     */
    public static RequestManager with(@NonNull final Fragment fragment) {
        return new RequestManager(fragment);
    }

}
