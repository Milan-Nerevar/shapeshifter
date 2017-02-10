package eu.nerevar.sample.circle;


import eu.inloop.viewmodel.IView;

interface CircleView extends IView {

    int getParentWidth();

    int getParentHeight();

    void moveImage(int x, int y);

}
