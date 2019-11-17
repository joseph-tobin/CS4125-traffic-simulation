package CS4125.TrafficControl;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import java.util.ArrayList;

public abstract class TCM extends Node {

	public TCM(float x, float y){ super(x,y); }
	public TCM(float x, float y, ArrayList<Node> adj){ super(x,y,adj);}

	private int currentQueue;
	private int queueMax;

	/**
	 * Abstract create method to be used in the CS4125.TrafficControl.TCM factory
	 * @return
	 */
	abstract TCM create(int x, int y, ArrayList<Node> adj); // TODO: 08-11-19 reconsider the implementation of the factory methods


}