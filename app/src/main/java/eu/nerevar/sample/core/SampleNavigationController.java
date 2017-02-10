package eu.nerevar.sample.core;


import eu.nerevar.shapeshifter.core.BaseNavigationController;

public class SampleNavigationController extends BaseNavigationController {

    private final int containerId;
    private final String rootTag;

    public SampleNavigationController(int containerId, String rootTag) {
        this.containerId = containerId;
        this.rootTag = rootTag;
    }

    @Override
    protected int getContainerId() {
        return containerId;
    }

    @Override
    protected String getRootTag() {
        return rootTag;
    }
}
