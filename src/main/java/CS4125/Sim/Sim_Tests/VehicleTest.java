package CS4125.Sim.Sim_Tests;

import CS4125.Sim.Vehicle;
import CS4125.TrafficControl.ITCM;
import CS4125.TrafficControl.SimpleJunction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Node;
import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private final ITCM curr = new SimpleJunction(1,2, new ArrayList<ITCM>());
    private final ITCM start = curr;
    private final ITCM end = new SimpleJunction (5,6, new ArrayList<ITCM>());
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