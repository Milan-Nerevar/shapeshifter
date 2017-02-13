package eu.nerevar.shapeshifter.core;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;

public final class RequestManager {

    private final AppCompatActivity activity;

    RequestManager(AppCompatActivity activity) {
        this.activity = activity;
    }

    RequestManager(Fragment fragment) {
        this((AppCompatActivity) fragment.getActivity());
    }

    public boolean pop() {
        // TODO
        return false;
    }

    public boolean navigateToRootFragment() {
        // TODO
        return false;
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
