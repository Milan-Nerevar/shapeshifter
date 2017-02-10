package eu.nerevar.sample.circle;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AbsoluteLayout;

import eu.inloop.viewmodel.binding.ViewModelBaseBindingFragment;
import eu.inloop.viewmodel.binding.ViewModelBindingConfig;
import eu.nerevar.sample.R;
import eu.nerevar.sample.databinding.FragmentCircleBinding;

public class CircleFragment extends ViewModelBaseBindingFragment<CircleView, CircleViewModel, FragmentCircleBinding>
        implements CircleView {

    public static CircleFragment newInstance() {
        return new CircleFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBinding().setModel(getViewModel().getModel());

        setModelView(this);
    }

    @Nullable
    @Override
    public ViewModelBindingConfig getViewModelBindingConfig() {
        return new ViewModelBindingConfig(R.layout.fragment_circle, getActivity());
    }

    @Nullable
    @Override
    public Class getViewModelClass() {
        return CircleViewModel.class;
    }

    @Override
    public int getParentWidth() {
        return 500;
    }

    @Override
    public int getParentHeight() {
        return 900;
    }

    @Override
    public void moveImage(int x, int y) {
        final AbsoluteLayout.LayoutParams absParams = (AbsoluteLayout.LayoutParams) getBinding().image.getLayoutParams();

        absParams.x = x;
        absParams.y = y;

        getBinding().image.setLayoutParams(absParams);
    }
}
