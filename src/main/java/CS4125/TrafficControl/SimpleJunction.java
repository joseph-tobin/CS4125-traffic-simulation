package CS4125.TrafficControl;

import java.util.ArrayList;

public class SimpleJunction extends TCM {


    /**
     * Models Traffic light's 3 state
     */
    enum State {
        GO(0);

        private int value;
        private State(int value) {
            this.value = value;
        }
        public int getValue() { return value; }
    }

    private State state;
    private int stateValue;

    SimpleJunction(int x, int y, ArrayList<Node> adj) {
        super(x, y, adj);
        this.state = State.GO;
        this.stateValue = state.getValue();
        // Call to Subject's setState(int state) method to notify observers of state change
        setState(stateValue);
    }

    /**
     * Override TCM create method for use in TCM Factory
     * @param x x coordinate of node
     * @param y y coordinate of node
     * @param adj list of adjacent nodes
     * @return Roundabout object
     */
    @Override
    TCM create(int x, int y, ArrayList<Node> adj) {
        return new SimpleJunction(x, y, adj);
    }
}
