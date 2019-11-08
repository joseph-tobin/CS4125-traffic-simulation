package CS4125.utils;

import java.util.ArrayList;

abstract class Subject {
    ArrayList<Observer> observers;
    int state;

    public int getState() {
        return state;
    }


}
