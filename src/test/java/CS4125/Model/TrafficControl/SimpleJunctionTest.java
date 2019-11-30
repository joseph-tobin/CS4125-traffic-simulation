package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Vehicle.Car;
import CS4125.Model.Vehicle.IVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleJunctionTest {

    private SimpleJunction simpleJunctionUnderTest;
    private SimpleJunction simpleJunctionUnderTestAdj1;
    private SimpleJunction simpleJunctionUnderTestAdj2;

    @BeforeEach
    void setUp() {
        simpleJunctionUnderTest = new SimpleJunction("juncUnderTest", 0, 0, false);
        simpleJunctionUnderTestAdj1 = new SimpleJunction("juncUnderTestAdj1",1, 1, false);
        simpleJunctionUnderTestAdj2 = new SimpleJunction("juncUnderTestAdj2",2, 2, false);
        // add adjacent Junctions
        simpleJunctionUnderTest.setAdjacent(Arrays.asList(simpleJunctionUnderTestAdj1, simpleJunctionUnderTestAdj2));
        simpleJunctionUnderTest.setAdjacent(Arrays.asList(simpleJunctionUnderTestAdj1, simpleJunctionUnderTestAdj2));
        simpleJunctionUnderTest.setAdjacent(Arrays.asList(simpleJunctionUnderTestAdj1, simpleJunctionUnderTestAdj2));
    }

    @Test
    void testGetAdjacent() {
        // Setup
        final List<ITCM> expectedResult = Arrays.asList(simpleJunctionUnderTestAdj1, simpleJunctionUnderTestAdj2);

        // Run the test
        final List<ITCM> result = simpleJunctionUnderTest.getAdjacent();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetAdjacent() {
        SimpleJunction a = new SimpleJunction("a", 9,9, false);
        SimpleJunction b = new SimpleJunction("b",9,9, false);
        SimpleJunction c = new SimpleJunction("c",9,9, false);
        // Setup
        final List<ITCM> adj = Arrays.asList(a,b,c);

        // Run the test
        simpleJunctionUnderTest.setAdjacent(adj);

        // Verify the results
        assertEquals(adj, simpleJunctionUnderTest.getAdjacent());
    }

    @Test
    void testEnterQueue() {
        // Setup
        final ITCM origin = simpleJunctionUnderTestAdj1;
        final IVehicle vehicle = new Car(simpleJunctionUnderTest, simpleJunctionUnderTestAdj1);

        // Run the test
        final boolean result = simpleJunctionUnderTest.enterQueue(origin, vehicle);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetPossibleNext() {
        // Setup
        final List<IGraphable> expectedResult = Arrays.asList(simpleJunctionUnderTestAdj1, simpleJunctionUnderTestAdj2);

        // Run the test
        final List<IGraphable> result = simpleJunctionUnderTest.getPossibleNext();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCompareTo() {
        // Setup
        final int expectedResult = 0;
        final ITCM sjUnderTestCopy = new SimpleJunction("jncUnderTestCopy", 0, 0, false);
        sjUnderTestCopy.setAdjacent(Arrays.asList(simpleJunctionUnderTestAdj1, simpleJunctionUnderTestAdj2));

        // Run the test
        final int result = simpleJunctionUnderTest.compareTo(sjUnderTestCopy);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
