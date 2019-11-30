package CS4125.Sim;

import CS4125.TrafficControl.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

class VehicleTest {

    @Mock
    private Node mockCurr;
    @Mock
    private Node mockLast;
    @Mock
    private Node mockStart;
    @Mock
    private Node mockEnd;

    private Vehicle vehicleUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        vehicleUnderTest = new Vehicle(mockCurr, mockLast, mockStart, mockEnd, new ArrayList<>(Arrays.asList()));
    }

    @Test
    void testCopy() {
        // Setup
        final Vehicle expectedResult = new Vehicle(null, null);

        // Run the test
        final Vehicle result = vehicleUnderTest.copy();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testMove() {
        // Setup

        // Run the test
        vehicleUnderTest.move();

        // Verify the results
    }
}
