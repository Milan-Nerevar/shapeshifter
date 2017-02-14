package eu.nerevar.shapeshifter.core;

/**
 * Default backward request built by {@link BackwardBuilder} and used by {@link BaseNavigationController}.
 */
public class BackwardRequest extends BaseRequest {

    public final String root;

    public BackwardRequest(BackwardBuilder builder) {
        super(builder);

        this.root = builder.root;
    }
}
