package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

class RoundaboutTest {

    @Mock
    private ITCM mockTcm;

    private Roundabout roundaboutUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        roundaboutUnderTest = new Roundabout(mockTcm);
    }

    @Test
    void testUpdateState() {
        // Setup

        // Run the test
        roundaboutUnderTest.updateState(0);

        // Verify the results
    }

    @Test
    void testGetX() {
        // Setup
        final float expectedResult = 0.0f;

        // Run the test
        final float result = roundaboutUnderTest.getX();

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testGetY() {
        // Setup
        final float expectedResult = 0.0f;

        // Run the test
        final float result = roundaboutUnderTest.getY();

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testGetAdjacent() {
        // Setup
        final List<ITCM> expectedResult = Arrays.asList();

        // Run the test
        final List<ITCM> result = roundaboutUnderTest.getAdjacent();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetX() {
        // Setup

        // Run the test
        roundaboutUnderTest.setX(0.0f);

        // Verify the results
    }

    @Test
    void testSetY() {
        // Setup

        // Run the test
        roundaboutUnderTest.setY(0.0f);

        // Verify the results
    }

    @Test
    void testSetAdjacent() {
        // Setup
        final List<ITCM> adj = Arrays.asList();

        // Run the test
        roundaboutUnderTest.setAdjacent(adj);

        // Verify the results
    }

    @Test
    void testEnterQueue() {
        // Setup
        final ITCM origin = null;
        final Vehicle vehicle = new Vehicle(null, null);

        // Run the test
        final boolean result = roundaboutUnderTest.enterQueue(origin, vehicle);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testGetHeuristic() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = roundaboutUnderTest.getHeuristic();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetPossibleNext() {
        // Setup
        final List<IGraphable> expectedResult = Arrays.asList();

        // Run the test
        final List<IGraphable> result = roundaboutUnderTest.getPossibleNext();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetEstimatedCost() {
        // Setup
        final float expectedResult = 0.0f;

        // Run the test
        final float result = roundaboutUnderTest.getEstimatedCost();

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testDistanceTo() {
        // Setup
        final IGraphable node = null;
        final float expectedResult = 0.0f;

        // Run the test
        final float result = roundaboutUnderTest.distanceTo(node);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    void testCompareTo() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = roundaboutUnderTest.compareTo("o");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
