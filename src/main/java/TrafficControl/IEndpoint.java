package TrafficControl;

import TrafficControl.Node;

public interface IEndpoint {

	int capacity = 0;

	public abstract int getCapacity();

	public abstract void setCapacity(int capacity);
}