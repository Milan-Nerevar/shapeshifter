package eu.nerevar.shapeshifter.core;


import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

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
        request.activity.getSupportFragmentManager().executePendingTransactions();
        setAnimations(request);
    }

    @Override
    public void navigateByReplacement(ForwardRequest request) {
        replaceFragment(request, false);
        request.activity.getSupportFragmentManager().executePendingTransactions();
        setAnimations(request);
    }

    @Override
    public void navigateWithoutReplacement(ForwardRequest request) {
        replaceFragment(request, true);
        request.activity.getSupportFragmentManager().executePendingTransactions();
        setAnimations(request);
    }

    @Override
    public boolean popWholeBackStack(BackwardRequest request) {
        final FragmentManager fragmentManager = request.activity.getSupportFragmentManager();

        final List<Fragment> fragments = fragmentManager.getFragments();

        if (fragments != null && !fragments.isEmpty()) {
            for (Fragment fragment : fragments) {
                if (request.leaveFirst && fragments.indexOf(fragment) == (fragments.size() - 1)) {
                    break;
                }

                if (fragment == null || fragment.isRemoving() || fragment.isDetached()) {
                    // do nothing
                } else {
                    final FragmentTransaction transaction = fragmentManager.beginTransaction().remove(fragment);
                    if (request.immediate) {
                        if (request.allowStateLoss) {
                            transaction.commitNowAllowingStateLoss();
                        } else {
                            transaction.commitNow();
                        }
                    } else {
                        if (request.allowStateLoss) {
                            transaction.commitAllowingStateLoss();
                        } else {
                            transaction.commit();
                        }
                    }
                }
            }

            fragments.clear();

            return true;
        }

//        final int temp = request.leaveFirst ? 1 : 0;
//
//        if (fragmentManager.getBackStackEntryCount() > temp) {
//
//            for (int i = temp; i < fragmentManager.getBackStackEntryCount(); i++) {
//                pop(request);
//            }
//
//            return true;
//        }

        return false;
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

    protected void replaceFragment(final ForwardRequest request,
                                   final boolean addToBackStack) {
        // create fragment transaction
        final FragmentTransaction ft = request.activity.getSupportFragmentManager().beginTransaction();

        // set transition
        ft.setTransition(getFragmentTransition());

        // replace fragment
        ft.replace(getContainerId(), request.fragment, null);

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

    protected void addFragment(final ForwardRequest request,
                               final boolean addToBackStack) {
        // create fragment transaction
        final FragmentTransaction ft = request.activity.getSupportFragmentManager().beginTransaction();

        // set transition
        ft.setTransition(getFragmentTransition());

        // add fragment
        ft.add(getContainerId(), request.fragment, null);

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
    protected abstract int getContainerId();

    protected abstract String getRootTag();

    @IntDef({TRANSIT_NONE, TRANSIT_FRAGMENT_OPEN, TRANSIT_FRAGMENT_CLOSE, TRANSIT_FRAGMENT_FADE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Transit {
    }
}
