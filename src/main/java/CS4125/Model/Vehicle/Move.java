package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;

public class Move {
    private ITCM start;
    private ITCM end;
    private int cost;

    public ITCM getStart() {
        return start;
    }

    public void setStart(ITCM start) {
        this.start = start;
    }

    public ITCM getEnd() {
        return end;
    }

    public void setEnd(ITCM end) {
        this.end = end;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
