
                                                                                                                                
package CS4125.Model.Utils;

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

class A_StarTest {

    @Test
    void testFindRoute() throws Exception {
    // Setup
                final CS4125.Model.Utils.IGraphable start = null;
        final CS4125.Model.Utils.IGraphable end = null;
        final java.util.List<CS4125.Model.Utils.IGraphable> expectedResult = java.util.Arrays.asList();

    // Run the test
 final java.util.List<CS4125.Model.Utils.IGraphable> result =  A_Star.findRoute(start,end);

        // Verify the results
 assertEquals(expectedResult, result) ;
    }
                                                                }

