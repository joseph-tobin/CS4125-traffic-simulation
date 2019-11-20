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
    Circle n;
    int delay;

    public NodeDelay(Circle n, int delay) {
        this.n = n;
        this.delay = delay;
    }
    public Circle getNode(){
        return n;
    }
    public int getDelay(){
        return delay;
    }
}
