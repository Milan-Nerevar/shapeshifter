Shapeshifter
================

Shapeshifter takes care of navigation through fragments in given activity reducing the boilerplate & repeating code.

<b>Basic idea</b>
A library with generic navigation Requests and Builders which target navigation controller of given activity.
Navigation controller consumes the requests and handles the fragment transactions.

How to implement
--------

1. Create your <b>SampleNavigationController</b> by extending [BaseNavigationController](library/src/main/java/eu/milan/core/BaseNavigationController.java).

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
   
   You can also implement your own custom <b>NavigationController</b> by implementing [NavigationController](library/src/main/java/eu/milan/core/NavigationController.java).
   
2. Add your <b>SampleNavigationController</b> to given activity

   ```java

   private final NavigationController navigationController = new SampleNavigationController(R.id.frameLayout, "root_activity_root_tag");

   ```
   
3. Implement [IShapeshifter](library/src/main/java/eu/milan/core/IShapeshifter.java) in your activity.

   ```java

   public class RootActivity extends AppCompatActivity implements IShapeshifter {
      private final NavigationController navigationController = new SampleNavigationController(R.id.frameLayout, "root_activity_root_tag");
      
      @Override
      public NavigationController getNavigationController() {
          return navigationController;
      }
   }

   ```
   
   This makes the library know how to target the <b>NavigationController</b> of your activity.
   
How to use
--------

Library currently support forward and backward navigation.

To initialize first fragment when your activity starts:

```java

Shapeshifter.with(this)
   .forward()
   .setFragment(CircleFragment.newInstance())
   .navigate(ForwardMode.REPLACEMENT);

```

To navigate to new fragment from navigation menu (pop all and replace):

```java

Shapeshifter.with(getActivity())
   .forward()
   .setFragment(fragment)
   .navigate(ForwardMode.NEW);

```

To navigate to new fragment and add it to backstack:

```java

Shapeshifter.with(getActivity())
   .forward()
   .setFragment(CircleFragment.newInstance())
   .navigate(ForwardMode.WITHOUT_REPLACEMENT);

```

Download
--------

- soon to be on jcenter :)
