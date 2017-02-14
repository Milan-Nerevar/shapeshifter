package eu.nerevar.sample.circle;


import eu.nerevar.sample.base.BaseView;

interface CircleView extends BaseView {

    int getParentWidth();

    int getParentHeight();

    void moveImage(int x, int y);

}
