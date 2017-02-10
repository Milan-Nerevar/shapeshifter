package eu.nerevar.shapeshifter.utils;


import android.app.Activity;
import android.support.annotation.NonNull;

import eu.nerevar.shapeshifter.core.IShapeshifter;
import eu.nerevar.shapeshifter.core.NavigationController;

public class Utils {

    @NonNull
    public static NavigationController getNavigationController(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity cannot be null");
        }

        if (!(activity instanceof IShapeshifter)) {
            throw new RuntimeException("Activity must implement IShapeshifter");
        }

        return ((IShapeshifter) activity).getNavigationController();
    }

}
