package CS4125.Model.Utils;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.TrafficControl.SimpleJunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A_StarTest {

    private ITCM start, end, B, C, D;

    @BeforeEach
    void setUp() {
        start = new SimpleJunction(1, 1, null);    start.setName("start");
        end = new SimpleJunction(6, 3, null);      end.setName("end");
        B = new SimpleJunction(5,1,null);          B.setName("B");
        C = new SimpleJunction(3,4,null);          C.setName("C");
        D = new SimpleJunction(5,4,null);          D.setName("D");
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
