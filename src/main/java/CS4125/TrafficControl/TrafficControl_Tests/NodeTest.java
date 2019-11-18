package CS4125.TrafficControl.TrafficControl_Tests;

import CS4125.TrafficControl.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    private final Node N = new Node(2,8);

    @Test
    void getX() {
        assertEquals(N.getX(), 2);
    }

    @Test
    void getY() {
        assertEquals(N.getY(), 8);
    }

    @Test
    void getCapacity() {
    }

    @Test
    void getHeuristic() {
    }
}