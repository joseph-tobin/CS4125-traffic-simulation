package CS4125.UserInterface;

import javafx.scene.shape.Circle;

public class UISim {
    UISim (UIController controller) {

        Circle n1 = controller.addNode(200, 200);
        Circle n2 = controller.addNode(300, 400);
        Circle n3 = controller.addNode(400, 200);

        controller.addEdge(n1, n2);
        controller.addEdge(n2, n3);

        controller.addCar(new NodeDelay[]{new NodeDelay(n1, 1000),
                new NodeDelay(n2, 1000),
                new NodeDelay(n3, 5000)});
    }

}
