package CS4125.Model.TrafficControl;

import CS4125.Model.Utils.IGraphable;
import CS4125.Model.Utils.Subject;
import CS4125.Model.Vehicle.Car;
import CS4125.Model.Vehicle.IVehicle;

import java.util.ArrayList;
import java.util.List;

public class SimpleJunction extends Subject implements IEndpoint {

    private float x;
    private float y;
    private boolean endpoint;
    private String label;
    private List<ITCM> adjacent;
    private List<Adjacency> adjacencyObjs; // Mapping between 2 ITCM objects

    private State currentState;
    private int currentStateNum;

    public enum State {
        GO(1),
        STOP(0);

        private int stateNum;
        State(int num) {
            this.stateNum = num;
        }
        public int getStateNum() { return this.stateNum; }
    }

    public SimpleJunction(String label, int x, int y, boolean endpoint) {
        this.label = label;
        this.x = x;
        this.y = y;
        this.adjacent = new ArrayList<ITCM>();
        this.endpoint = endpoint;
        // GO is default state of SimpleJunction -> Pass through junction when possible
        currentState = State.GO;
        currentStateNum = currentState.getStateNum();
    }

    /**
     * Method to initialise an Adjacency object for each adjacent ITCM to this object
     * @param adj list of adjacent ITCM object passed to the ITCM constructor
     */
    private void initAdjacency(List<ITCM> adj) {
        adjacencyObjs = new ArrayList<Adjacency>(adj.size());
        for(ITCM tcm: adj) {
            adjacencyObjs.add(new Adjacency(tcm, this.x, this.y));
        }
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
    public String getLabel() {return this.label;}

    @Override
    public List<ITCM> getAdjacent() {
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
    public void setAdjacent(List<ITCM> adj) {
        this.adjacent = adj;
        initAdjacency(adj);
    }

    /**
     * Offer a Vehicle to a ITCM's adjacent queue to see if a vehicle can pass to the next ITCM
     * @param origin ITCM in which the vehicle is in a queue
     * @param v Vehicle object that wishes to enter this node's adjacent queue
     * @return Queue.offer();
     */
    @Override
    public boolean enterQueue(ITCM origin, IVehicle v) {
        for(Adjacency a: adjacencyObjs) {
            if(a.getAdj() == origin) {
                return a.getQueue().offer(v);
            }
        }
        return false;
    }

    /**
     * exit an adjacency queue todo refactor to adjacency to increase code quality
     * @param prevNode next node in a route
     */
    @Override
    public void exitQueue(ITCM prevNode) {
        for(Adjacency a: adjacencyObjs) {
            if(a.getAdj().equals(prevNode))
                a.getQueue().poll();
        }
    }

    /**
     * Get current queue size between this and an adjacent ITCM
     * @param dest Adjacent ITCM object
     * @return current queue, else -1 if dest not adjacent to this
     */
    @Override
    public int getCurrentQueue(ITCM dest) {
        for(Adjacency a: adjacencyObjs) {
            if(a.getAdj().equals(dest))
                return a.getQueue().size();
        }
        return -1;
    }

    @Override
    public void setEndpoint(boolean bool) {
        this.endpoint = bool;
    }

    @Override
    public boolean isEndpoint() {
        return this.endpoint;
    }

    /**
     * Update state of TCM and inform all Observers of the change in state
     * If stateNum = current state, don't update observers
     */
    @Override
    public void updateState(int stateNum) {
        // TODO: 26-11-19 write implementation for observer pattern
    }

    @Override
    public int getHeuristic() {
        return 0;
    }

    @Override
    public List<IGraphable> getPossibleNext() {
        return new ArrayList<IGraphable>(this.adjacent);
    }

    /**
     * Override method from IGraphable to allow A_Star to search through a list of Junctions
     * @return estimated cost of getting to this node
     */
    @Override
    public float getEstimatedCost() {
        float cost = 0;
        for(Adjacency a: adjacencyObjs) {
            cost += (float)a.getQueue().size() / a.getMaxCapacity();
        }
        return cost;
    }

    @Override
    public float distanceTo(IGraphable node) {
        float dist = (float) Math.sqrt(( ((ITCM)node).getY() - y) * (((ITCM)node).getY() - y)
                + (((ITCM)node).getX() - x) * (((ITCM)node).getX() - x));
        return dist;
    }

    /**
     * Implementation of Comparable Interface
     * @param o Object to compare to
     * @return  -1 if this.estimatedCost < other, 1 if this.estimatedCost() > other, 0 if both costs equal
     */
    @Override
    public int compareTo(Object o) {
        ITCM other = (ITCM) o;
        if( ((ITCM)o).getEstimatedCost() > this.getEstimatedCost())
            return -1;
        else if(((ITCM)o).getEstimatedCost()  < this.getEstimatedCost())
            return 1;
        else return 0;
    }
}
