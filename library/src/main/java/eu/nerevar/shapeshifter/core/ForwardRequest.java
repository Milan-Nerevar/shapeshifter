package eu.nerevar.shapeshifter.core;

import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Default forward request built by {@link ForwardBuilder} and used by {@link BaseNavigationController}.
 */
public class ForwardRequest extends BaseRequest {

    public final List<Pair<View, String>> sharedElements = new ArrayList<>();

    public final Object enterTransition;
    public final Object exitTransition;
    public final Object reenterTransition;
    public final Object returnTransition;
    public final Object sharedElementEnterTransition;
    public final Object sharedElementReturnTransition;
    public final boolean allowEnterTransitionOverlap;
    public final boolean allowReturnTransitionOverlap;
    public final boolean replaceSameFragment;
    public final String root;

    ForwardRequest(ForwardBuilder builder) {
        super(builder);

        this.sharedElements.addAll(builder.sharedElements);
        this.enterTransition = builder.enterTransition;
        this.exitTransition = builder.exitTransition;
        this.reenterTransition = builder.reenterTransition;
        this.returnTransition = builder.returnTransition;
        this.sharedElementEnterTransition = builder.sharedElementEnterTransition;
        this.sharedElementReturnTransition = builder.sharedElementReturnTransition;
        this.allowEnterTransitionOverlap = builder.allowEnterTransitionOverlap;
        this.allowReturnTransitionOverlap = builder.allowReturnTransitionOverlap;
        this.replaceSameFragment = builder.replaceSameFragment;
        this.root = builder.root;
    }
}
