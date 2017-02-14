package eu.nerevar.sample.circleintroduction;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.List;

import eu.inloop.viewmodel.binding.ViewModelBaseBindingFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;
import eu.nerevar.sample.R;
import eu.nerevar.sample.databinding.FragmentCircleIntroductionBinding;

public class CircleIntroductionFragment extends ViewModelBaseBindingFragment<CircleIntroductionView, CircleIntroductionViewModel, FragmentCircleIntroductionBinding>
        implements CircleIntroductionView {

    public static CircleIntroductionFragment newInstance() {
        return new CircleIntroductionFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setModelView(this);
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_circle_introduction, getActivity());
    }

    @Nullable
    @Override
    public Class<CircleIntroductionViewModel> getViewModelClass() {
        return CircleIntroductionViewModel.class;
    }

    @Override
    public List<Pair<View, String>> getSharedElements() {
        return null;
    }
}
