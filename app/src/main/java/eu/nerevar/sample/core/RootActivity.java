package eu.nerevar.sample.core;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import eu.inloop.viewmodel.base.ViewModelBaseEmptyActivity;
import eu.nerevar.sample.R;
import eu.nerevar.sample.circleintroduction.CircleIntroductionFragment;
import eu.nerevar.shapeshifter.core.ForwardMode;
import eu.nerevar.shapeshifter.core.IShapeshifter;
import eu.nerevar.shapeshifter.core.NavigationController;
import eu.nerevar.shapeshifter.core.Shapeshifter;

public class RootActivity extends ViewModelBaseEmptyActivity implements IShapeshifter {

    private final NavigationController navigationController = new SampleNavigationController(R.id.frameLayout, "root_activity_root_tag");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_root);

        if (savedInstanceState == null) {
            Shapeshifter.with(this)
                    .forward()
                    .setFragment(CircleIntroductionFragment.newInstance())
                    .setRoot(CircleIntroductionFragment.class.getName())
                    .navigate(ForwardMode.WITHOUT_REPLACEMENT);
        }
    }

    @Override
    public NavigationController getNavigationController() {
        return navigationController;
    }
}
