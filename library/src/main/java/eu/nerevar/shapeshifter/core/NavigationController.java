package eu.nerevar.shapeshifter.core;

/**
 * Prescription for navigation controller
 *
 * @param <F> forward request type
 * @param <B> backward request type
 */
public interface NavigationController<F extends BaseRequest, B extends BaseRequest> {

    /**
     * Navigate to fragment by addition
     */
    void navigateByAddition(F request);

    /**
     * Navigate to fragment by replacement
     */
    void navigateByReplacement(F request);

    /**
     * Navigate to fragment without replacement
     */
    void navigateWithoutReplacement(F request);

    /**
     * Navigate to new fragment and pop all previous ones
     */
    void navigateToNewRoot(F request);

    /**
     * Navigate to fragment's specified root
     */
    boolean navigateToFragmentRoot(B request);

    /**
     * Pops whole back stack excluding first fragment
     */
    boolean popWholeBackStack(B request);

    /**
     * Pop back stack by one
     */
    boolean pop(B request);

}
