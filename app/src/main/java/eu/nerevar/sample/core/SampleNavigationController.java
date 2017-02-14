package eu.nerevar.sample.core;


import eu.nerevar.shapeshifter.core.BaseNavigationController;

import static android.support.v4.app.FragmentTransaction.TRANSIT_NONE;

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

    @Override
    protected int getFragmentTransition() {
        return TRANSIT_NONE;
    }
}
