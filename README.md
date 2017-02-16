Shapeshifter
================

Shapeshifter takes care of navigation through fragments in given activity reducing the boilerplate & repeating code.

<b>Basic idea</b>
A library with generic navigation Requests and Builders which target navigation controller of given activity.
Navigation controller consumes the requests and handles the fragment transactions.

How to implement
--------

1. Create your <NavigationController> by extending [BaseNavigationController](library/src/main/java/eu/milan/core/BaseNavigationController.java).

   ```java
   
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

   ```
   
   You can also implement your own <NavigationController> by implementing [NavigationController](library/src/main/java/eu/milan/core/NavigationController.java).
