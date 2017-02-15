package eu.nerevar.sample.core;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.util.Pair;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import eu.inloop.viewmodel.base.ViewModelBaseActivity;
import eu.nerevar.sample.R;
import eu.nerevar.sample.circle.CircleFragment;
import eu.nerevar.sample.databinding.ActivityRootBinding;
import eu.nerevar.shapeshifter.core.ForwardMode;
import eu.nerevar.shapeshifter.core.IShapeshifter;
import eu.nerevar.shapeshifter.core.NavigationController;
import eu.nerevar.shapeshifter.core.Shapeshifter;

public class RootActivity extends ViewModelBaseActivity<RootView, RootViewModel>
        implements RootView, IShapeshifter {

    private final NavigationController navigationController = new SampleNavigationController(R.id.frameLayout, "root_activity_root_tag");

    private ActivityRootBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_root);
        binding.navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.drawer.closeDrawers();
                return getViewModel().onNavigationDrawerClicked(item.getItemId());
            }
        });

        if (savedInstanceState == null) {
            binding.navigation.setCheckedItem(R.id.nav_fragment_transitions);
            Shapeshifter.with(this)
                    .forward()
                    .setFragment(CircleFragment.newInstance())
                    .navigate(ForwardMode.REPLACEMENT);
        }

        setModelView(this);
    }

    @Nullable
    @Override
    public Class<RootViewModel> getViewModelClass() {
        return RootViewModel.class;
    }

    @Override
    public NavigationController getNavigationController() {
        return navigationController;
    }

    @Override
    public List<Pair<View, String>> getSharedElements() {
        return null;
    }
}
