
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
package CS4125.Controller.Sim;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
    import static org.mockito.Matchers.any;
    import static org.mockito.Matchers.anyInt;
    import static org.mockito.Matchers.anyString;
    import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.InjectMocks;
import org.mockito.stubbing.Answer;

class SimulationTest {

    @Test
    void testInit() throws Exception {
            // Setup
                                        final CS4125.View.EventHandlers.UIController controller = new CS4125.View.EventHandlers.UIController(null);

        // Run the test
 Simulation.INSTANCE.init(controller);

            // Verify the results
    }
                                                                    @Test
    void testRun() throws Exception {
        // Run the test
 Simulation.INSTANCE.run();

            // Verify the results
    }
                                                                    @Test
    void testPause() throws Exception {
        // Run the test
 Simulation.INSTANCE.pause();

            // Verify the results
    }
                                                                    @Test
    void testReset() throws Exception {
        // Run the test
 Simulation.INSTANCE.reset();

            // Verify the results
    }
                                                                    @Test
    void testAddNode() throws Exception {
        // Run the test
 Simulation.INSTANCE.addNode("type","name",0,0);

            // Verify the results
    }
                                                                    @Test
    void testDeleteNode() throws Exception {
        // Run the test
 Simulation.INSTANCE.deleteNode("label");

            // Verify the results
    }
                                                                    @Test
    void testFindNode() throws Exception {
            // Setup
                                        final CS4125.Model.TrafficControl.ITCM expectedResult = null;

        // Run the test
 final CS4125.Model.TrafficControl.ITCM result =  Simulation.INSTANCE.findNode("label");

            // Verify the results
 assertEquals(expectedResult, result) ;
    }
                                                                    @Test
    void testFindNodeIndex() throws Exception {
            // Setup
                                        final java.util.List<CS4125.Model.TrafficControl.ITCM> list = java.util.Arrays.asList();
                final int expectedResult = 0;

        // Run the test
 final int result =  Simulation.INSTANCE.findNodeIndex("label",list);

            // Verify the results
 assertEquals(expectedResult, result) ;
    }
                                                                    @Test
    void testAddEdge() throws Exception {
        // Run the test
 Simulation.INSTANCE.addEdge("l1","l2");

            // Verify the results
    }
                                                                    @Test
    void testDeleteEdge() throws Exception {
        // Run the test
 Simulation.INSTANCE.deleteEdge("l1","l2");

            // Verify the results
    }
                                                                    @Test
    void testAddAdjacent() throws Exception {
            // Setup
                                        final CS4125.Model.TrafficControl.ITCM n1 = null;
                final CS4125.Model.TrafficControl.ITCM n2 = null;

        // Run the test
 Simulation.INSTANCE.addAdjacent(n1,n2);

            // Verify the results
    }
                                                                    @Test
    void testRemoveAdjacent() throws Exception {
            // Setup
                                        final CS4125.Model.TrafficControl.ITCM n1 = null;
                final CS4125.Model.TrafficControl.ITCM n2 = null;

        // Run the test
 Simulation.INSTANCE.removeAdjacent(n1,n2);

            // Verify the results
    }
                                                                    @Test
    void testDefaultNodes() throws Exception {
        // Run the test
 Simulation.INSTANCE.defaultNodes();

            // Verify the results
    }
                                                                    @Test
    void testCreateVehicle() throws Exception {
            // Setup
                                        final CS4125.Model.TrafficControl.ITCM start = null;
                final CS4125.Model.TrafficControl.ITCM end = null;

        // Run the test
 Simulation.INSTANCE.createVehicle(start,end);

            // Verify the results
    }
                                                                    @Test
    void testCreateVehicles() throws Exception {
            // Setup
                                        final java.util.List<CS4125.Model.TrafficControl.IEndpoint> nodes = java.util.Arrays.asList();

        // Run the test
 Simulation.INSTANCE.createVehicles(nodes,0);

            // Verify the results
    }
                                                                    @Test
    void testSetVCTimer() throws Exception {
        // Run the test
 Simulation.INSTANCE.setVCTimer(0);

            // Verify the results
    }
                                                                    @Test
    void testUpdateGraph() throws Exception {
        // Run the test
 Simulation.INSTANCE.updateGraph();

            // Verify the results
    }
                                                                    @Test
    void testAddVehicleToController() throws Exception {
            // Setup
                                        final CS4125.Model.Vehicle.Car v = new CS4125.Model.Vehicle.Car(null, null);

        // Run the test
 Simulation.INSTANCE.addVehicleToController(v);

            // Verify the results
    }
                                                                    @Test
    void testGetMetrics() throws Exception {
            // Setup
                                        final CS4125.Model.Metrics.Metric expectedResult = new CS4125.Model.Metrics.Metric();

        // Run the test
 final CS4125.Model.Metrics.Metric result =  Simulation.INSTANCE.getMetrics();

            // Verify the results
 assertEquals(expectedResult, result) ;
    }
                                                                    @Test
    void testAddVehicleToVehicleList() throws Exception {
            // Setup
                                        final CS4125.Model.Vehicle.IVehicle v = null;

        // Run the test
 Simulation.INSTANCE.addVehicleToVehicleList(v);

            // Verify the results
    }
                                                                    @Test
    void testAddVehicleAnim() throws Exception {
            // Setup
                                        final CS4125.Model.Vehicle.IVehicle v = null;

        // Run the test
 Simulation.INSTANCE.addVehicleAnim(v,0);

            // Verify the results
    }
                                                                }

