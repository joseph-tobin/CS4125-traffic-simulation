package CS4125.View;

import javafx.scene.shape.Circle;

/**
 * n: Node (probably shouldn't be a circle)
 * delay: time taken to get to Node from the previous one
 * -> This will need to be calculated in the sim as we are generating the path,
 * -> based on the distance of the road and the queue along it.
 * -> This will be used as the time (in milliseconds) an animation will run for,
 * -> AKA the time for a car to travel along that path.
 * -> This can be scaled afterwards, being relative is the main thing.
 */
public class NodeDelay {
    private String tcmType;
    private String name;
    private int x;
    private int y;
    private int delay;

    public NodeDelay(String tcmType, String name, int x, int y, int delay) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.delay = delay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public String getTcmType() {
        return tcmType;
    }

    public void setTcmType(String tcmType) {
        this.tcmType = tcmType;
    }
}
