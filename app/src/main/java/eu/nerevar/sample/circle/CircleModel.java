package eu.nerevar.sample.circle;


import android.databinding.ObservableInt;

import java.io.Serializable;

public class CircleModel implements Serializable {

    public static final int TRANSITION_DURATION = 300;

    private final ObservableInt color = new ObservableInt();

    private final ObservableInt x = new ObservableInt();

    private final ObservableInt y = new ObservableInt();

    public ObservableInt getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color.set(color);
    }

    public ObservableInt getX() {
        return x;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public ObservableInt getY() {
        return y;
    }

    public void setY(int y) {
        this.y.set(y);
    }

}
