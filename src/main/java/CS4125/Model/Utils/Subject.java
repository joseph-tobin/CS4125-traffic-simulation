package CS4125.Model.Utils;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    ArrayList<Observer> observers = new ArrayList<Observer>();
    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }
    public void detach(Observer observer) {
        observers.indexOf(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}
