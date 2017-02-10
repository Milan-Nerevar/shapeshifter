package eu.nerevar.shapeshifter.core;


public class ForwardRequest extends BaseRequest {

    public final Object enterTransition;
    public final Object exitTransition;
    public final Object reenterTransition;
    public final Object returnTransition;
    public final Object sharedElementEnterTransition;
    public final Object sharedElementReturnTransition;
    public final boolean allowEnterTransitionOverlap;
    public final boolean allowReturnTransitionOverlap;
    public final String root;

    ForwardRequest(ForwardBuilder builder) {
        super(builder);

        this.enterTransition = builder.enterTransition;
        this.exitTransition = builder.exitTransition;
        this.reenterTransition = builder.reenterTransition;
        this.returnTransition = builder.returnTransition;
        this.sharedElementEnterTransition = builder.sharedElementEnterTransition;
        this.sharedElementReturnTransition = builder.sharedElementReturnTransition;
        this.allowEnterTransitionOverlap = builder.allowEnterTransitionOverlap;
        this.allowReturnTransitionOverlap = builder.allowReturnTransitionOverlap;
        this.root = builder.root;
    }
}
