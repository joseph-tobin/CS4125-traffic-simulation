package CS4125.TrafficControl;


/**
 * Base class of all TCM decorator classes,
 * Must be declared abstract as it does not implement any methods defined in ITCM.
 */
public abstract class TCMDecorator implements ITCM {
    private ITCM tcm;

    TCMDecorator(ITCM tcm) {
        this.tcm = tcm;
    }

    public ITCM getTcm() {
        return this.tcm;
    }
}
