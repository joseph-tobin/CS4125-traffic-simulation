package CS4125.Model.Utils;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.TrafficControl.SimpleJunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A_StarTest {

    private ITCM start, end, B, C, D;

    @BeforeEach
    void setUp() {
        start = new SimpleJunction("start", 1, 1, false);
        end = new SimpleJunction("end",6, 3, false);
        B = new SimpleJunction("B",5,1, false);
        C = new SimpleJunction("C",3,4, false);
        D = new SimpleJunction("D",5,4, false);
    }

    @Test
    void testFindRouteSimple() {
        // Setup
        start.setAdjacent((Arrays.asList(B, C)));
        B.setAdjacent((Arrays.asList(start,C)));
        C.setAdjacent((Arrays.asList(start,B,D)));
        D.setAdjacent((Arrays.asList(C, end)));
        end.setAdjacent((Arrays.asList(D)));

        final List<IGraphable> expectedResult = Arrays.asList(start, C, D, end);

        // Run the test
        final List<IGraphable> result = A_Star.findRoute(start, end);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testFindRoute() {
        // Setup
        start.setAdjacent((Arrays.asList(B)));
        B.setAdjacent((Arrays.asList(start, end)));
        end.setAdjacent((Arrays.asList(B)));

        final List<IGraphable> expectedResult = Arrays.asList(start, B, end);

        // Run the test
        final List<IGraphable> result = A_Star.findRoute(start, end);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
