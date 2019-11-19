package CS4125.TrafficControl;

import CS4125.utils.IGraphable;

import java.util.ArrayList;
import java.util.List;

public class SimpleJunction implements ITCM {

    private float x;
    private float y;
    private int capacity;
    private ArrayList<ITCM> adjacent;

    public SimpleJunction(int x, int y, ArrayList<ITCM> adj) {
        this.x = x;
        this.y = y;
        this.adjacent = adj;
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
