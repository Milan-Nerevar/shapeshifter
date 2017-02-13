package eu.nerevar.shapeshifter.core;

/**
 * An interface which an activity has to implement to be able to use Shapeshifter library.
 * <p>
 * Represents a declaration on how to access navigation controller attached to activity.
 */
public interface IShapeshifter {

    /**
     * Returns navigation controller attached to activity.
     */
    NavigationController getNavigationController();

}
