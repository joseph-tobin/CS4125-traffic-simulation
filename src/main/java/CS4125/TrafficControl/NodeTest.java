package CS4125.TrafficControl;

import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;

class NodeTest {

    private Node nodeUnderTest;

    @BeforeEach
    void setUp() {
        nodeUnderTest = new Node(0.0f, 0.0f, new ArrayList<>(Arrays.asList()));
    }
}
