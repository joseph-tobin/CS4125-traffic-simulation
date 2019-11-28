package CS4125.Model.Utils;

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.TrafficControl.SimpleJunction;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class A_StarTest {

    @Test
    void testFindRoute() {
        // Setup
        ITCM start = new SimpleJunction(1, 1, null);
        ITCM end = new SimpleJunction(6, 3, null);
        ITCM B = new SimpleJunction(5,1,null);
        ITCM C = new SimpleJunction(3,4,null);
        ITCM D = new SimpleJunction(5,4,null);

        start.setAdjacent((Arrays.asList(B, C)));
        end.setAdjacent((Arrays.asList(D)));
        B.setAdjacent((Arrays.asList(start, C)));
        C.setAdjacent((Arrays.asList(start, B)));
        D.setAdjacent((Arrays.asList(end, C)));

        final List<IGraphable> expectedResult = Arrays.asList(end, D, C, start);

        // Run the test
        final List<IGraphable> result = A_Star.findRoute(start, end);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
