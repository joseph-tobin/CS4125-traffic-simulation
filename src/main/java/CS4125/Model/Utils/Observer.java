package CS4125.Model.Utils;

public abstract class Observer {
    protected Subject subject;
    public abstract void update(Subject subject);
}