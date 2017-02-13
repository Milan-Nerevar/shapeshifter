package eu.nerevar.shapeshifter.core;


import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;

/**
 * A class which represents an interface to build navigation request builders of type {@link BaseBuilder}
 * and holds given {@link AppCompatActivity} to handle the operations of it's support fragment manager.
 */
public final class RequestManager {

    /**
     * Activity to handle the operations
     */
    private final AppCompatActivity activity;

    /**
     * Default constructor to instantiate request manager from activity
     *
     * @param activity to handle navigation operations
     */
    RequestManager(AppCompatActivity activity) {
        this.activity = activity;
    }

    /**
     * Default implementation to pop by one fragment
     */
    public void pop() {
        backward().navigate(BackwardMode.POP);
    }

    /**
     * Default implementation to pop whole fragment manager.
     */
    public void popWhole() {
        backward().navigate(BackwardMode.POP_WHOLE);
    }

    /**
     * Default implementation to navigate to root fragment
     *
     * @param fragment fragment of which root to return to
     */
    public void navigateToRootFragment(@NonNull final Fragment fragment) {
        backward().setFragment(fragment).navigate(BackwardMode.FRAGMENT_ROOT);
    }

    /**
     * Default backward builder
     *
     * @return builder of {@link BackwardBuilder} class
     */
    public BackwardBuilder backward() {
        return forClass(BackwardBuilder.class);
    }

    /**
     * Default forward builder
     *
     * @return builder of {@link ForwardBuilder} class
     */
    public ForwardBuilder forward() {
        return forClass(ForwardBuilder.class);
    }

    /**
     * Returns custom request builder.
     * <p>
     * Request builder and Navigation Controller request types of calling activity must match.
     *
     * @param clazz class of which to create the class
     * @param <T>   type of Builder
     * @param <R>   type of Request
     * @param <M>   type of navigation mode
     * @return new instance of specified class created from one parameter constructor of class AppCompatActivity
     */
    public <T extends BaseBuilder<T, R, M>, R extends BaseRequest, M> T forClass(Class<T> clazz) {
        T instance = null;

        try {
            instance = clazz.getDeclaredConstructor(AppCompatActivity.class).newInstance(activity);
        } catch (InstantiationException e) {
            Log.e("Shapeshifter", "Could not instantiate builder", e);
        } catch (IllegalAccessException e) {
            Log.e("Shapeshifter", "Could not access class " + clazz.getName(), e);
        } catch (NoSuchMethodException e) {
            Log.e("Shapeshifter", "Could not find such method of" + clazz.getName(), e);
        } catch (InvocationTargetException e) {
            Log.e("Shapeshifter", "Error of invocation of " + clazz.getName() + " class' method", e);
        }

        return instance;
    }

}
