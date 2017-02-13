package eu.nerevar.shapeshifter.core;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import eu.nerevar.shapeshifter.utils.ShapeshifterConstants;

/**
 * Default forward builder for creating a {@link ForwardRequest} used by {@link BaseNavigationController}.
 */
public class ForwardBuilder extends BaseBuilder<ForwardBuilder, ForwardRequest, ForwardMode> {

    Object enterTransition;
    Object exitTransition;
    Object reenterTransition;
    Object returnTransition;
    Object sharedElementEnterTransition;
    Object sharedElementReturnTransition;

    boolean allowEnterTransitionOverlap;
    boolean allowReturnTransitionOverlap;

    String root;

    ForwardBuilder(@NonNull final AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void navigate(ForwardMode mode) {
        if (fragment == null) {
            throw new IllegalStateException("Fragment cannot be null");
        }

        init();

        switch (mode) {
            case ADDITION:
                navigationController.navigateByAddition(new ForwardRequest(this));
                break;
            case REPLACEMENT:
                navigationController.navigateByReplacement(new ForwardRequest(this));
                break;
            case WITHOUT_REPLACEMENT:
                navigationController.navigateWithoutReplacement(new ForwardRequest(this));
                break;
        }
    }

    @Override
    public void navigate(final NavigationTask<ForwardRequest> navigationTask) {
        if (fragment == null) {
            throw new IllegalStateException("Fragment cannot be null");
        }

        init();

        new Runnable() {
            @Override
            public void run() {
                navigationTask.run(navigationController);
            }
        };
    }


    @Override
    protected ForwardBuilder self() {
        return this;
    }

    public ForwardBuilder setEnterTransition(Object enterTransition) {
        this.enterTransition = enterTransition;
        return self();
    }

    public ForwardBuilder setExitTransition(Object exitTransition) {
        this.exitTransition = exitTransition;
        return self();
    }

    public ForwardBuilder setReenterTransition(Object reenterTransition) {
        this.reenterTransition = reenterTransition;
        return self();
    }

    public ForwardBuilder setReturnTransition(Object returnTransition) {
        this.returnTransition = returnTransition;
        return self();
    }

    public ForwardBuilder setSharedElementEnterTransition(Object sharedElementEnterTransition) {
        this.sharedElementEnterTransition = sharedElementEnterTransition;
        return self();
    }

    public ForwardBuilder setSharedElementReturnTransition(Object sharedElementReturnTransition) {
        this.sharedElementReturnTransition = sharedElementReturnTransition;
        return self();
    }

    public ForwardBuilder setAllowEnterTransitionOverlap(boolean allowEnterTransitionOverlap) {
        this.allowEnterTransitionOverlap = allowEnterTransitionOverlap;
        return self();
    }

    public ForwardBuilder setAllowReturnTransitionOverlap(boolean allowReturnTransitionOverlap) {
        this.allowReturnTransitionOverlap = allowReturnTransitionOverlap;
        return self();
    }

    public ForwardBuilder setRoot(String root) {
        this.root = root;
        return self();
    }

    protected void init() {
        fragment.setEnterTransition(enterTransition);
        fragment.setExitTransition(exitTransition);
        fragment.setReenterTransition(reenterTransition);
        fragment.setReturnTransition(returnTransition);
        fragment.setSharedElementEnterTransition(sharedElementEnterTransition);
        fragment.setSharedElementReturnTransition(sharedElementReturnTransition);

        if (fragment.getArguments() == null) {
            final Bundle arg = new Bundle(1);
            arg.putString(ShapeshifterConstants.ARG_FRAGMENT_ROOT, root);
            fragment.setArguments(arg);
        } else {
            fragment.getArguments().putString(ShapeshifterConstants.ARG_FRAGMENT_ROOT, root);
        }
    }
}
