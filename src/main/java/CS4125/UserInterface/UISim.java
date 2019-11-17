package CS4125.UserInterface;

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
class NodeDelay {
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

/**
 * A dummy Sim to show how UI interaction can be implemented
 */
public class UISim {
    UISim (UIController controller) {

        Circle n1 = controller.addNode(200, 200); // Adding nodes
        Circle n2 = controller.addNode(300, 400);
        Circle n3 = controller.addNode(400, 200);

        controller.addEdge(n1, n2); // Adding roads between nodes
        controller.addEdge(n2, n3);

        // Adding car animated along path in list of NodeDelay (
        controller.addCar(new NodeDelay[]{new NodeDelay(n1, 1000),
                new NodeDelay(n2, 1000),
                new NodeDelay(n3, 5000)});
    }

}
