package eu.inloop.shapeshifter.core;

import android.support.v4.app.Fragment;

/**
 * Default backward navigation mode for {@link BaseNavigationController} built by a {@link ForwardBuilder}.
 *
 * TODO rethink the names of these states since they are not too convenient IMHO
 */
public enum ForwardMode {

    /**
     * Add fragment to container over the current fragment using {@link android.support.v4.app.FragmentTransaction#add(int, Fragment, String)}
     */
    ADDITION,

    /**
     * Replace fragment in container using {@link android.support.v4.app.FragmentTransaction#replace(int, Fragment, String)} without adding to backstack
     */
    WITHOUT_REPLACEMENT,

    /**
     * Replace fragment in container using {@link android.support.v4.app.FragmentTransaction#replace(int, Fragment, String)} with adding to backstack
     */
    REPLACEMENT,

    /**
     * Pop all fragments and add new fragment using {@link ForwardMode#WITHOUT_REPLACEMENT} method
     */
    NEW

}
