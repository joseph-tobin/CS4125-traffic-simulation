package CS4125.Model.Vehicle.Vehicle_Tests;

import CS4125.Model.Vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {
    private final ITCM curr = new ITCM(1,2);
    private final ITCM start = curr;
    private final ITCM end = new ITCM(5,6);
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