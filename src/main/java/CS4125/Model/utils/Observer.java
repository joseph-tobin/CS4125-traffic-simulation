package CS4125.Model.utils;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}