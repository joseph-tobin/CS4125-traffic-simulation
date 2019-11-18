package CS4125.Sim.Sim_Tests;

import CS4125.Sim.Vehicle;
import CS4125.TrafficControl.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private final Node curr = new Node(1,2);
    private final Node start = curr;
    private final Node end = new Node (5,6);
    private final Vehicle Vehicle_T = new Vehicle(start, end);

    @Test
    void copy() {
        assertNotSame(Vehicle_T.copy(), Vehicle_T);
    }

    @Test
    void getCurrentNode() {
        assertSame(Vehicle_T.getCurrentNode(), curr);
    }

    @Test
    void getStartNode() {
        assertSame(Vehicle_T.getStartNode(), start);
    }

    @Test
    void getEndNode() {
        assertSame(Vehicle_T.getEndNode(), end);
    }
}