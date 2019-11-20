package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.Subject;
import CS4125.Model.Utils.IGraphable;

import java.util.List;

public class SimpleJunction extends Subject implements ITCM {

    private float x;
    private float y;
    private int capacity;
    private List<IGraphable> adjacent;
    private State currentState;
    private int currentStateNum;


    public enum State {
        GO(1),
        STOP(0);

        private int stateNum;
    private List<NodePair> adjacent;

    public SimpleJunction(int x, int y, List<NodePair> adj) {
        State(int num) {
            this.stateNum = num;
        }
        public int getStateNum() { return stateNum; }
    }

    public SimpleJunction(int x, int y, ArrayList<IGraphable> adj) {
        this.x = x;
        this.y = y;
        this.adjacent = adj;
        // GO is default state of SimpleJunction -> Pass through junction when possible
        currentState = State.GO;
        currentState.getStateNum();
    }

    /* ---- Override Methods ---- */
    @Override
    public float getX(){
        return this.x;
    }

    @Override
    public float getY(){
        return this.y;
    }

    @Override
    public List<NodePair> getAdjacent() {
        return this.adjacent;
    }

    @Override
    public void setX(float x){
        this.x = x;
    }

    @Override
    public void setY(float y){
        this.y = y;
    }

    @Override
    public void setAdjacent(List<NodePair> adj) {
        this.adjacent = adj;
    }


    /**
     * Update state of TCM and inform all Observers of the change in state
     * If stateNum = current state, don't update observers
     */
    @Override
    public void updateState(int stateNum) {

    }

    @Override
    public int getHeuristic() {
        return 0;
    }

    @Override
    public List<IGraphable> getPossibleNext() {
        return this.adjacent;
    }

    /**
     * Estimated cost = Average of how busy each adjacent
     * @return Estimated cost of going to this Node
     */
    @Override
    public float getEstimatedCost() {
        return 0;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
