package eu.nerevar.shapeshifter.core;


import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE;
import static android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE;
import static android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN;
import static android.support.v4.app.FragmentTransaction.TRANSIT_NONE;

/**
 * Basic implementation of navigation controller.
 */
@SuppressWarnings("WeakerAccess")
public abstract class BaseNavigationController implements NavigationController<ForwardRequest, BackwardRequest> {

    @Override
    public void navigateByAddition(ForwardRequest request) {
        addFragment(request, true);
        setAnimations(request);
    }

    @Override
    public void navigateByReplacement(ForwardRequest request) {
        replaceFragment(request, false);
        setAnimations(request);
    }

    @Override
    public void navigateWithoutReplacement(ForwardRequest request) {
        replaceFragment(request, true);
        setAnimations(request);
    }

    @Override
    public void navigateToNewRoot(ForwardRequest request) {
        final FragmentManager fragmentManager = request.activity.getSupportFragmentManager();

        // pop all fragments
        if (fragmentManager.getBackStackEntryCount() > 0) {
            if (request.immediate) {
                while (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStackImmediate();
                }
            } else {
                final int count = fragmentManager.getBackStackEntryCount();

                for (int i = 0; i < count; i++) {
                    fragmentManager.popBackStack();
                }
            }
        }

        // replace fragment if allowed and is the same class
        if (request.replaceSameFragment && fragmentManager.findFragmentByTag(getRootTag()) != null) {
            if (!fragmentManager.findFragmentByTag(getRootTag()).getClass().equals(request.fragment.getClass())) {
                replaceFragment(request, false);
            }
        } else {
            // replace fragment
            replaceFragment(request, false);
        }
    }

    @Override
    public boolean navigateToFragmentRoot(BackwardRequest request) {
        if (request.root == null) {
            return false;
        }

        final FragmentManager fragmentManager = request.activity.getSupportFragmentManager();

        if (request.immediate) {
            fragmentManager.popBackStackImmediate(request.root, 0);
        } else {
            fragmentManager.popBackStack(request.root, 0);
        }

        return false;
    }

    /**
     * Pops whole back stack. Can leave first fragment if requested.
     *
     * @return if immediate is false, then return is false because the operation is asynchronous.
     * Otherwise returns if something was popped.
     */
    @Override
    public boolean popWholeBackStack(BackwardRequest request) {
        final FragmentManager fragmentManager = request.activity.getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() <= 0) {
            return false;
        }

        if (request.immediate) {
            while (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStackImmediate();
            }

            return true;
        } else {
            for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
                fragmentManager.popBackStack();
            }

            return false;
        }
    }

    @Override
    public boolean pop(BackwardRequest request) {
        final FragmentManager fragmentManager = request.activity.getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            if (request.immediate) {
                fragmentManager.popBackStackImmediate();
            } else {
                fragmentManager.popBackStack();
            }
            return true;
        }

        return false;
    }

    protected void replaceFragment(final BaseRequest request,
                                   final boolean addToBackStack) {
        // create fragment transaction
        final FragmentTransaction ft = request.activity.getSupportFragmentManager().beginTransaction();

        // set transition
        ft.setTransition(getFragmentTransition());

        // replace fragment
        ft.replace(getContainerId(), request.fragment, getRootTag());

        // add to backstack ?
        if (addToBackStack) {
            ft.addToBackStack(request.fragment.getClass().getName());
        }

        // commit
        if (request.immediate) {
            if (request.allowStateLoss) {
                ft.commitNowAllowingStateLoss();
            } else {
                ft.commitNow();
            }
        } else {
            if (request.allowStateLoss) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }
        }
    }

    protected void addFragment(final BaseRequest request,
                               final boolean addToBackStack) {
        // create fragment transaction
        final FragmentTransaction ft = request.activity.getSupportFragmentManager().beginTransaction();

        // set transition
        ft.setTransition(getFragmentTransition());

        // add fragment
        ft.add(getContainerId(), request.fragment, request.fragment.getClass().getName());

        // add to backstack ?
        if (addToBackStack) {
            ft.addToBackStack(request.fragment.getClass().getName());
        }

        // commit
        if (request.immediate) {
            if (request.allowStateLoss) {
                ft.commitNowAllowingStateLoss();
            } else {
                ft.commitNow();
            }
        } else {
            if (request.allowStateLoss) {
                ft.commitAllowingStateLoss();
            } else {
                ft.commit();
            }
        }
    }

    protected void setAnimations(@NonNull final ForwardRequest request) {
        final Fragment fragment = request.fragment;

        fragment.setEnterTransition(request.enterTransition);
        fragment.setReenterTransition(request.reenterTransition);
        fragment.setExitTransition(request.exitTransition);
        fragment.setReturnTransition(request.returnTransition);
        fragment.setSharedElementEnterTransition(request.sharedElementEnterTransition);
        fragment.setSharedElementReturnTransition(request.sharedElementReturnTransition);
        fragment.setAllowEnterTransitionOverlap(request.allowEnterTransitionOverlap);
        fragment.setAllowReturnTransitionOverlap(request.allowReturnTransitionOverlap);
    }

    @Transit
    protected int getFragmentTransition() {
        return TRANSIT_FRAGMENT_FADE;
    }

    /**
     * Returns container id on which to handle the operation.
     */
    @IdRes
    protected abstract int getContainerId();

    @Nullable
    protected abstract String getRootTag();

    @IntDef({TRANSIT_NONE, TRANSIT_FRAGMENT_OPEN, TRANSIT_FRAGMENT_CLOSE, TRANSIT_FRAGMENT_FADE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Transit {
    }
}
