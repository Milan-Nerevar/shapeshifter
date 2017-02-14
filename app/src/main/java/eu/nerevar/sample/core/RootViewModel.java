package eu.nerevar.sample.core;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import eu.nerevar.sample.R;
import eu.nerevar.sample.base.BaseViewModel;
import eu.nerevar.sample.circleintroduction.CircleIntroductionFragment;
import eu.nerevar.shapeshifter.core.ForwardMode;
import eu.nerevar.shapeshifter.core.Shapeshifter;

public class RootViewModel extends BaseViewModel<RootView> {

    boolean onNavigationDrawerClicked(@IdRes int id) {
        if (getActivity() == null) {
            return false;
        }

        final Fragment fragment;

        switch (id) {
            case R.id.nav_first_fragment:
                fragment = CircleIntroductionFragment.newInstance();
                break;
            default:
                return false;
        }

        // navigate to new root
        Shapeshifter.with(getActivity())
                .forward()
                .setFragment(fragment)
                .navigate(ForwardMode.NEW);


        return true;
    }

}
