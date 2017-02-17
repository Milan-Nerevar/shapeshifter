package eu.inloop.shapeshifter.core;

/**
 * Default backward navigation mode for {@link BaseNavigationController} built by a {@link BackwardBuilder}.
 */
public enum BackwardMode {

    /**
     * Pop backstack by one
     */
    POP,

    /**
     * Pop all fragment transaction from backstack
     */
    POP_WHOLE,

    /**
     * Pop all fragment transactions from backstack till we the fragment with given rood
     */
    FRAGMENT_ROOT

}
