package CS4125.utils;

public interface IGraphable extends Comparable {
    /**
     * Method for a graph item to calculate it's heuristic value path finding
     * @return calculated integer heuristic value
     */
    abstract int getHeuristic();
}
