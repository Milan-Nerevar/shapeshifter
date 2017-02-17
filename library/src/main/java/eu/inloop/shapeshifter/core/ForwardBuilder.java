package eu.inloop.shapeshifter.core;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.TransitionRes;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import eu.inloop.shapeshifter.utils.ShapeshifterConstants;

/**
 * Default forward builder for creating a {@link ForwardRequest} used by {@link BaseNavigationController}.
 */
public class ForwardBuilder extends BaseBuilder<ForwardBuilder, ForwardRequest, ForwardMode> {

    final List<Pair<View, String>> sharedElements = new ArrayList<>();

    Object enterTransition;
    Object exitTransition;
    Object reenterTransition;
    Object returnTransition;
    Object sharedElementEnterTransition;
    Object sharedElementReturnTransition;

    boolean allowEnterTransitionOverlap;
    boolean allowReturnTransitionOverlap;
    boolean replaceSameFragment;

    String root;

    ForwardBuilder(@NonNull final AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void navigate(ForwardMode mode) {
        if (fragment == null) {
            throw new IllegalStateException("Fragment cannot be null");
        }

        initArguments();

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
            case NEW:
                navigationController.navigateToNewRoot(new ForwardRequest(this));
                break;
        }
    }

    @Override
    public void navigate(final NavigationTask<ForwardRequest> navigationTask) {
        if (fragment == null) {
            throw new IllegalStateException("Fragment cannot be null");
        }

        initArguments();

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder addSharedElement(View view, String string) {
        this.sharedElements.add(new Pair<>(view, string));
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder addSharedElements(List<Pair<View, String>> elements) {
        this.sharedElements.addAll(elements);
        return this;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setEnterTransition(Object enterTransition) {
        this.enterTransition = enterTransition;
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setEnterTransition(@TransitionRes int res) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            this.enterTransition = TransitionInflater.from(activity).inflateTransition(res);
        }
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setExitTransition(Object exitTransition) {
        this.exitTransition = exitTransition;
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setExitTransition(@TransitionRes int res) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            this.exitTransition = TransitionInflater.from(activity).inflateTransition(res);
        }
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setReenterTransition(Object reenterTransition) {
        this.reenterTransition = reenterTransition;
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setReenterTransition(@TransitionRes int res) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            this.reenterTransition = TransitionInflater.from(activity).inflateTransition(res);
        }
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setReturnTransition(Object returnTransition) {
        this.returnTransition = returnTransition;
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setReturnTransition(@TransitionRes int res) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            this.returnTransition = TransitionInflater.from(activity).inflateTransition(res);
        }
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setSharedElementEnterTransition(Object sharedElementEnterTransition) {
        this.sharedElementEnterTransition = sharedElementEnterTransition;
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setSharedElementEnterTransition(@TransitionRes int res) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            this.sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(res);
        }
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setSharedElementReturnTransition(Object sharedElementReturnTransition) {
        this.sharedElementReturnTransition = sharedElementReturnTransition;
        return self();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ForwardBuilder setSharedElementReturnTransition(@TransitionRes int res) {
        if (Build.VERSION_CODES.LOLLIPOP <= Build.VERSION.SDK_INT) {
            this.sharedElementReturnTransition = TransitionInflater.from(activity).inflateTransition(res);
        }
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

    public ForwardBuilder setReplaceSameFragment(boolean replaceSameFragment) {
        this.replaceSameFragment = replaceSameFragment;
        return self();
    }

    public ForwardBuilder setRoot(String root) {
        this.root = root;
        return self();
    }

    /**
     * Init fragment arguments
     */
    protected void initArguments() {
        if (fragment.getArguments() == null) {
            final Bundle arg = new Bundle(1);
            arg.putString(ShapeshifterConstants.ARG_FRAGMENT_ROOT, root);
            fragment.setArguments(arg);
        } else {
            fragment.getArguments().putString(ShapeshifterConstants.ARG_FRAGMENT_ROOT, root);
        }
    }
}
