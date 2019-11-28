package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleJunctionTest {

    private SimpleJunction simpleJunctionUnderTest;

    @BeforeEach
    void setUp() {
        simpleJunctionUnderTest = new SimpleJunction(0, 0, new ArrayList<>(Arrays.asList()));
    }

    @Test
    void testGetAdjacent() {
        // Setup
        final List<ITCM> expectedResult = Arrays.asList();

        // Run the test
        final List<ITCM> result = simpleJunctionUnderTest.getAdjacent();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testSetAdjacent() {
        // Setup
        final List<ITCM> adj = Arrays.asList();

        // Run the test
        simpleJunctionUnderTest.setAdjacent(adj);

        // Verify the results
    }

    @Test
    void testUpdateState() {
        // Setup

        // Run the test
        simpleJunctionUnderTest.updateState(1);

        // Verify the results
    }

    @Test
    void testGetHeuristic() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = simpleJunctionUnderTest.getHeuristic();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetPossibleNext() {
        // Setup
        final List<IGraphable> expectedResult = Arrays.asList();

        // Run the test
        final List<IGraphable> result = simpleJunctionUnderTest.getPossibleNext();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testCompareTo() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = simpleJunctionUnderTest.compareTo("o");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
