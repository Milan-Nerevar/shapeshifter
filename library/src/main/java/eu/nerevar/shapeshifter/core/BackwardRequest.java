package eu.nerevar.shapeshifter.core;


public class BackwardRequest extends BaseRequest {

    boolean leaveFirst;

    public BackwardRequest(BackwardBuilder builder) {
        super(builder);

        this.leaveFirst = builder.leaveFirst;
    }
}
