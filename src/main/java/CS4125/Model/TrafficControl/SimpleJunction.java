package CS4125.Model.TrafficControl;

import CS4125.Model.utils.IGraphable;

import java.util.List;

public class SimpleJunction implements ITCM {

    private float x;
    private float y;
    private int capacity;
    private List<NodePair> adjacent;

    public SimpleJunction(int x, int y, List<NodePair> adj) {
        this.x = x;
        this.y = y;
        this.adjacent = adj;
    }

    public SimpleJunction(int x, int y) {
        this.x = x;
        this.y = y;
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

    @Override
    public void updateState() {

    }

    @Override
    public int getHeuristic() {
        return 0;
    }

    @Override
    public List<IGraphable> getPossibleNext() {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
