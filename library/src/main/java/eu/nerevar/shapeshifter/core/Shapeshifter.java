package eu.nerevar.shapeshifter.core;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * A singleton to represent a simple static interface for building navigation requests with {@link RequestManager}.
 */
public final class Shapeshifter {

    private Shapeshifter() {
        // empty & unused constructor
    }

    /**
     * Navigation from activity
     */
    public static RequestManager with(@NonNull final Activity activity) {
        if (!(activity instanceof AppCompatActivity)) {
            throw new IllegalArgumentException("Activity must extend AppCompatActivity to support navigation.");
        }

        return new RequestManager((AppCompatActivity) activity);
    }

    /**
     * Navigation from fragment
     */
    public static RequestManager with(@NonNull final Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("Activity of fragment cannot be null to support navigation.");
        }

        if (!(fragment.getActivity() instanceof AppCompatActivity)) {
            throw new IllegalArgumentException("Activity must extend AppCompatActivity to support navigation.");
        }

        return new RequestManager((AppCompatActivity) fragment.getActivity());
    }

}
