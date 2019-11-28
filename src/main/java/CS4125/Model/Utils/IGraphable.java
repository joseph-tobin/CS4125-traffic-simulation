package CS4125.Model.Utils;

import java.util.List;

public interface IGraphable extends Comparable {
    /**
     * Method for a graph item to calculate it's heuristic value path finding
     *
     * @return calculated integer heuristic value or 0 if end point found
     */
    abstract int getHeuristic();

    /**
     * Method to get the next possible states from the current IGraphable state
     *
     * @return List of possible next IGraphable
     */
    abstract List<IGraphable> getPossibleNext();

    /**
     * Calculate the estimated cost of going to a Node
     * @return float - Estimated cost.
     */
    abstract float getEstimatedCost();

    abstract float distanceTo(IGraphable node);

    abstract boolean equals(Object o);
    abstract int hashCode();
}