package CS4125.Model.TrafficControl;

import CS4125.Model.Vehicle.Car;
import CS4125.Model.Vehicle.IVehicle;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Adjacency {
    private ITCM adj;
    private Queue<IVehicle> queue;
    private int maxCapacity;

    /**
     * Adjacency constructor - create an Adjacency association between 2 nodes, where adj.x == x AND adj.y == y is NOT true
     * @param adj adjacent node to calling node
     * @param x X Coord of calling node where
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
        try {
            maxCapacity = (int) Math.sqrt((adj.getY() - y) * (adj.getY() - y)
                    + (adj.getX() - x) * (adj.getX() - x));
            maxCapacity = (int) Math.ceil(maxCapacity / 10); // make sure the max queue is at least 1
            queue = new ArrayBlockingQueue<>(maxCapacity);
        } catch (Exception e) {
            System.out.println("Check node coordinates");
            System.out.println(e.getMessage());
        }
    }

    ITCM getAdj() { return this.adj; }
    Queue<IVehicle> getQueue() { return this.queue; }
    int  getMaxCapacity() { return this.maxCapacity; }
}
