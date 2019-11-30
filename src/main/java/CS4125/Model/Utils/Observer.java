package CS4125.Model.Utils;

public interface Observer {
    Subject subject = new Subject();
    void update(int state);
}