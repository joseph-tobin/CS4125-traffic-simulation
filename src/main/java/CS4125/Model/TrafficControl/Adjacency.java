package CS4125.Model.TrafficControl;

import CS4125.Model.Vehicle.Vehicle;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Adjacency {
    private ITCM adj;
    private Queue<Vehicle> queue;
    private int maxCapacity;

    /**
     * Adjacency constructor -
     * @param adj adjacent node to calling node
     * @param x X Coord of calling node
     * @param y Y Coord of calling node
     */
    Adjacency(ITCM adj, float x, float y) {
        this.adj = adj;
        initQueue(x, y);
    }

    // TODO: 27-11-19 remove init queue, add distance to constructor
    /**
     * Function to calculate the maximum capacity of this adjacency and instantiate the queue
     * @param x X coordinate of owning ITCM
     * @param y Y coordinate of owning ITCM
     */
    private void initQueue(float x, float y) {
        maxCapacity = (int) Math.sqrt((adj.getY() - y) * (adj.getY() - y)
                                        + (adj.getX() - x) * (adj.getX() - x));

        queue = new ArrayBlockingQueue<>(maxCapacity);
    }

    ITCM getAdj() { return this.adj; }
    Queue<Vehicle> getQueue() { return this.queue; }
    int  getMaxCapacity() { return this.maxCapacity; }
}
