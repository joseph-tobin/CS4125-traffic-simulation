package CS4125.Sim;

import CS4125.TrafficControl.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimulationTest {

    private Simulation simulationUnderTest;

    @BeforeEach
    void setUp() {
        simulationUnderTest = new Simulation();
    }

    @Test
    void testRun() {
        // Setup

        // Run the test
        simulationUnderTest.run();

        // Verify the results
    }

    @Test
    void testPause() {
        // Setup

        // Run the test
        simulationUnderTest.pause();

        // Verify the results
    }

    @Test
    void testReset() {
        // Setup

        // Run the test
        simulationUnderTest.reset();

        // Verify the results
    }

    @Test
    void testCreateNodes() {
        // Setup

        // Run the test
        simulationUnderTest.createNodes();

        // Verify the results
    }

    @Test
    void testCreateVehicle() {
        // Setup
        final Node start = new Node(0.0f, 0.0f, new ArrayList<>(Arrays.asList()));
        final Node end = new Node(0.0f, 0.0f, new ArrayList<>(Arrays.asList()));

        // Run the test
        simulationUnderTest.createVehicle(start, end);

        // Verify the results
    }

    @Test
    void testUpdateGraph() {
        // Setup

        // Run the test
        simulationUnderTest.updateGraph();

        // Verify the results
    }

    @Test
    void testGetMetrics() {
        // Setup
        final Metric expectedResult = new Metric();

        // Run the test
        final Metric result = simulationUnderTest.getMetrics();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
