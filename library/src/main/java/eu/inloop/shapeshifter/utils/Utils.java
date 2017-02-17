package eu.inloop.shapeshifter.utils;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import eu.inloop.shapeshifter.core.NavigationController;
import eu.inloop.shapeshifter.core.IShapeshifter;

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

    @Nullable
    public static String listToString(@Nullable final List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();

        for (String s : list) {
            builder.append(s);
            if (list.indexOf(s) != list.size() - 1) {
                builder.append(", ");
            }
        }

        return builder.toString();
    }

}
