package eu.nerevar.sample.base;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import eu.inloop.viewmodel.AbstractViewModel;

public abstract class BaseViewModel<T extends BaseView> extends AbstractViewModel<T> {

    @Nullable
    protected Fragment getFragment() {
        if (getView() != null && getView() instanceof Fragment) {
            return (Fragment) getView();
        }

        return null;
    }

    @Nullable
    protected AppCompatActivity getActivity() {
        if (getView() != null && getView() instanceof Fragment) {
            final Fragment fragment = (Fragment) getView();

            if (fragment.getActivity() instanceof AppCompatActivity) {
                return (AppCompatActivity) fragment.getActivity();
            }
        } else if (getView() != null && getView() instanceof AppCompatActivity) {
            return (AppCompatActivity) getView();
        }

        return null;
    }

    @Nullable
    protected List<Pair<View, String>> requestSharedElements() {
        if (getView() != null) {
            return getView().getSharedElements();
        }
        return null;
    }

}
