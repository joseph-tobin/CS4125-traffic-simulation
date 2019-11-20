package CS4125.View;

import CS4125.View.EventHandlers.UIController;
import javafx.scene.shape.Circle;


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
