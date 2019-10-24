package TrafficControl;

public abstract class TCM extends Node {

	public TCM(float x, float y){
		super(x,y);
	}

	// needs more vars and methods

	/**
	 * Abstract create method to be used in the TrafficControl.TCM factory
	 * @return
	 */
	abstract TCM create();

}