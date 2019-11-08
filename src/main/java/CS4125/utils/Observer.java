package CS4125.utils;

public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}