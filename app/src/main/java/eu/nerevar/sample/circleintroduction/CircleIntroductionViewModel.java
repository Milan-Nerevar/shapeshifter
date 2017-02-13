package eu.nerevar.sample.circleintroduction;


import eu.nerevar.sample.base.BaseViewModel;
import eu.nerevar.sample.circle.CircleFragment;
import eu.nerevar.shapeshifter.core.ForwardMode;
import eu.nerevar.shapeshifter.core.Shapeshifter;

public class CircleIntroductionViewModel extends BaseViewModel<CircleIntroductionView> {

    public void navigateToCircle() {
        if (getActivity() == null) {
            return;
        }

        Shapeshifter.with(getActivity())
                .forward()
                .setFragment(CircleFragment.newInstance())
                .setRoot(CircleIntroductionFragment.class.getName())
                .navigate(ForwardMode.WITHOUT_REPLACEMENT);
    }

}
