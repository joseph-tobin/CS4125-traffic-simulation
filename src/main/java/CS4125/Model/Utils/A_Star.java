package CS4125.Model.Utils;

/*
 * Austin Bohannon [18286119]
 * Niall Dillane [13132911]
 * Adam O'Mahony [16187504]
 */

import CS4125.Model.TrafficControl.ITCM;
import CS4125.Model.Utils.IGraphable;
import com.sun.org.apache.xpath.internal.objects.XObject;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.PriorityQueue;

/* A_Star
 * Previous Project modified to be use a generic design that will work with any class implementing IGraphable interface
 * A*.
 */
public class A_Star {

    private static List<IGraphable> route;

    public static class State implements Comparable<State> {

        // The goal state
        static IGraphable destination;

        // Private data
        IGraphable current; // Current IGraphable object
        State prev;
        int depth; // Depth in the state tree (0 if root)
        int heuristic;

        public State(IGraphable current, State prev, int depth) {
            this.current = current;
            this.prev = prev;
            this.depth = depth;
            this.heuristic = current.getHeuristic();
        }

        /**
         * Get a list from the Current IGraphable object of the next possible states
         * @return List of next possible States
         */
        public List<State> getNextStates() {
            ArrayList<State> possibleStates = new ArrayList<State>();
            List<IGraphable> graphables = current.getPossibleNext();
            for(IGraphable g : graphables) {
                possibleStates.add(new State(g, this, this.depth+1));
            }
            return possibleStates;
        }

        /* getEstimatedCost
         * Returns f = g + h.
         */
        public int getEstimatedCost() {
            int cost = (int)(depth + current.getEstimatedCost() * current.distanceTo(destination));
            return cost;
        }


        public static void setDestination(IGraphable dest) {
            State.destination = dest;
        }

        /* compareTo
         * Necessary to implement Comparable, which is in turn necessary to be used in a PriorityQueue.
         */
        @Override
        public int compareTo(State other) {
            int cost = this.getEstimatedCost() - other.getEstimatedCost();
            return cost;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj.getClass() == this.getClass()) {
              return this.current == ((State) obj).current;
            }
            return false;
        }

        // TODO: 28/11/2019 implement proper hash function
        @Override
        public int hashCode() {
            final int prime = 31;
            int hash = current.hashCode();

            return hash;
        }

        public List<IGraphable> generateRoute(List<IGraphable> route) {
            if(prev != null) {
                prev.generateRoute(route);
            }
//            System.out.println(((ITCM)current).getLabel() + " add to route");
            route.add(current);
            return route;
        }
    }

    /* End of State class */

    /**
     *
     * @param start Starting node of a searchable graph
     * @param end Destination node of a searchable graph
     * @return List of nodes in a graph describing the best route found from start to end nodes
     */
    public static List<IGraphable> findRoute(IGraphable start, IGraphable end) {
        List<IGraphable> route = new ArrayList<IGraphable>();
        HashSet<State> seen = new HashSet<State>();
        PriorityQueue<State> open = new PriorityQueue<State>();

        State.destination = end;

        State startState = new State(start, null, 0);
        seen.add(startState);
        open.add(startState);

        try {
            State temp = open.poll();

            // Ignore warning, open will always init with 1 state (Start state), check if temp becomes null in the loop
            while(!temp.current.equals(end)){
                for(State child : temp.getNextStates()){
                    if(!(seen.contains(child))){
                        open.add(child);
                        seen.add(child);
                    }
                }
                temp=open.poll();
                if(temp == null) {
                    System.out.println("Search Exhausted: A* Open set empty and did not find destination");
                    return null;
                }
            }
            route = temp.generateRoute(route);
        } catch (OutOfMemoryError e) {
            System.out.println("Error: Out of Memory, search space too large.");
            return null;
        } catch (NullPointerException e) {
            System.out.println("Search exhausted: unreachable destination");
            return null;
        }

        return route;
    }
}