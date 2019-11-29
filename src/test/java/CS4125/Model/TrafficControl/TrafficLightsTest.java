//package CS4125.Model.TrafficControl;
//
//import CS4125.Model.Utils.IGraphable;
//import CS4125.Model.Vehicle.Vehicle;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.MockitoAnnotations.initMocks;
//
//class TrafficLightsTest {
//
//    private ITCM baseTCM = new SimpleJunction("base", 200,200, null);
//    private ITCM baseTCM1 = new SimpleJunction("adj1", 300,300, null);
//    private ITCM baseTCM2 = new SimpleJunction("adj2", 400,400, null);
//
//    private TrafficLights trafficLightsUnderTest;
//    private TrafficLights trafficLightsUnderTestAdj1;
//    private TrafficLights trafficLightsUnderTestAdj2;
//
//
//    @BeforeEach
//    void setUp() {
//        trafficLightsUnderTest = new TrafficLights(baseTCM);
//        trafficLightsUnderTestAdj1 = new TrafficLights(baseTCM1);
//        trafficLightsUnderTestAdj2 = new TrafficLights(baseTCM2);
//
//        trafficLightsUnderTest.setAdjacent(new ArrayList<>(Arrays.asList(trafficLightsUnderTestAdj1, trafficLightsUnderTestAdj2)));
//        trafficLightsUnderTestAdj1.setAdjacent(new ArrayList<>(Arrays.asList(trafficLightsUnderTest, trafficLightsUnderTestAdj2)));
//        trafficLightsUnderTestAdj2.setAdjacent(new ArrayList<>(Arrays.asList(trafficLightsUnderTestAdj1, trafficLightsUnderTest)));
//    }
//
//    @Test
//    void testUpdateState() {
//        // Setup
//
//        // Run the test
//        trafficLightsUnderTest.updateState(0);
//
//        // Verify the results
//        fail();
//    }
//
//    @Test
//    void testEnterQueue() {
//        // Setup
//        final Vehicle vehicle = new Vehicle(trafficLightsUnderTestAdj1, trafficLightsUnderTest);
//
//        // Run the test
//        final boolean result = trafficLightsUnderTest.enterQueue(trafficLightsUnderTestAdj1, vehicle);
//
//        // Verify the results
//        assertTrue(result);
//    }
//}
