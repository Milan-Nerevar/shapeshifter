package eu.nerevar.shapeshifter.core;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import eu.nerevar.shapeshifter.utils.ShapeshifterConstants;

/**
 * Default backward builder for creating a {@link BackwardRequest} used by {@link BaseNavigationController}.
 */
public class BackwardBuilder extends BaseBuilder<BackwardBuilder, BackwardRequest, BackwardMode> {

    boolean immediate;
    boolean leaveFirst;
    boolean allowStateLoss;

    String root;

    public BackwardBuilder(@NonNull AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void navigate(BackwardMode mode) {
        init();
        switch (mode) {
            case FRAGMENT_ROOT:
                navigationController.navigateToFragmentRoot(new BackwardRequest(this));
                break;
            case POP:
                navigationController.pop(new BackwardRequest(this));
                break;
            case POP_WHOLE:
                navigationController.popWholeBackStack(new BackwardRequest(this));
                break;
        }
    }

    @Override
    public void navigate(NavigationTask<BackwardRequest> navigationTask) {
        init();
        // TODO
    }

    @Override
    protected BackwardBuilder self() {
        return this;
    }

    public BackwardBuilder setImmediate(boolean immediate) {
        this.immediate = immediate;
        return self();
    }

    public BackwardBuilder setLeaveFirst(boolean leaveFirst) {
        this.leaveFirst = leaveFirst;
        return self();
    }

    public BackwardBuilder setAllowStateLoss(boolean allowStateLoss) {
        this.allowStateLoss = allowStateLoss;
        return self();
    }

    protected void init() {
        if (fragment == null || fragment.getArguments() == null) {
            return;
        }

        this.root = fragment.getArguments().getString(ShapeshifterConstants.ARG_FRAGMENT_ROOT);
    }
}
