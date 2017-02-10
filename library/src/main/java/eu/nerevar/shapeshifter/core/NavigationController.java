package eu.nerevar.shapeshifter.core;

/**
 * Prescription for navigation controller
 *
 * @param <F> forward request
 * @param <B> backward request
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
     * Pops whole back stack excluding first fragment
     */
    boolean popWholeBackStack(B request);

    /**
     * Pop back stack by one
     */
    boolean pop(B request);

}
