package eu.nerevar.sample.base;

import android.support.v4.util.Pair;
import android.view.View;

import java.util.List;

import eu.inloop.viewmodel.IView;


public interface BaseView extends IView {

    List<Pair<View, String>> getSharedElements();

}
