package CS4125.Model.Vehicle;

import CS4125.Model.TrafficControl.ITCM;

public class Move {
    private ITCM start;
    private ITCM end;

    Move(ITCM s, ITCM e) {
        this.start = s;
        this.end = e;
    }

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
}
