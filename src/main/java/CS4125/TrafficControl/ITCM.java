package CS4125.TrafficControl;


import CS4125.utils.IGraphable;

/**
 * Interface that all Traffic Control Management classes will implement
 * Defines methods that all types of TCMs require
 */
public interface ITCM extends IGraphable {
    // TODO: 17-11-19 See if saving all possible states as an enum here is possible/ worth while

    abstract void updateState();
}
