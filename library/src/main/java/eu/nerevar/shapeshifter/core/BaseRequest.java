package eu.nerevar.shapeshifter.core;


import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Base class for navigation requests
 */
public abstract class BaseRequest {

    public final AppCompatActivity activity;
    public final Fragment fragment;

    public final boolean immediate;
    public final boolean allowStateLoss;

    public BaseRequest(BaseBuilder builder) {
        this.activity = builder.activity;
        this.fragment = builder.fragment;
        this.immediate = builder.immediate;
        this.allowStateLoss = builder.allowStateLoss;
    }

}
