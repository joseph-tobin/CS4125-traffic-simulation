package CS4125.Model.TrafficControl.TrafficControl_Tests;

import org.junit.jupiter.api.Test;

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