package CS4125.utils;

/*
 * Austin Bohannon [18286119]
 * Niall Dillane [13132911]
 * Adam O'Mahony [16187504]
 */

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashSet;
import java.util.PriorityQueue;

/* A_Star
 * Previous Project modified to route find through a list of adjacent Nodes
 * A*.
 */
public class A_Star {
    /* State
     * Holds a single state of the game, calculating future moves and keeping
     * track of the previous state.
     */
    public static class State implements Comparable<State> {
        // Board size constants (set in main)
        public static short BOARD_WIDTH = 0;
        public static short BOARD_HEIGHT = 0;
        public static short BOARD_SIZE = 0;

        // The goal state
        static short[] endBoard;

        // Private data
        State prev; // Previous board State (null if root)
        short[] board; // The board
        int depth; // Depth in the state tree (0 if root)
        int heuristic;

        /* Constructor
         * Sets variables.
         */
        public State(State prev, short[] board, int depth) {
            this.prev = prev;
            this.board = board;
            this.depth = depth;
            this.heuristic = heuristic();
        }

        /* getNextStates
         * Calculates all possible moves and returns them as an ArrayList.
         */
        public ArrayList<State> getNextStates() {
            // Find 0
            short index = -1;
            for(short i = 0; i < BOARD_SIZE; i++) {
                if (board[i] == 0) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                throw new IndexOutOfBoundsException("Board does not contain a 0");
            }

            // Calculate next states
            ArrayList<State> possibleStates = new ArrayList<State>();
            // Check to switch with one above
            if (index > BOARD_WIDTH - 1) {
                short[] newBoard = new short[BOARD_SIZE];
                System.arraycopy(board, 0, newBoard, 0, board.length);
                newBoard[index] = newBoard[index - BOARD_WIDTH];
                newBoard[index - BOARD_WIDTH] = 0;
                possibleStates.add(new State(this, newBoard, depth + 1));
            }
            // Check to switch with one below
            if (index < BOARD_WIDTH * (BOARD_HEIGHT - 1)) {
                short[] newBoard = new short[BOARD_SIZE];
                System.arraycopy(board, 0, newBoard, 0, board.length);
                newBoard[index] = newBoard[index + BOARD_WIDTH];
                newBoard[index + BOARD_WIDTH] = 0;
                possibleStates.add(new State(this, newBoard, depth + 1));
            }
            // Check to switch with one to left
            if (index%BOARD_WIDTH > 0) {
                short[] newBoard = new short[BOARD_SIZE];
                System.arraycopy(board, 0, newBoard, 0, board.length);
                newBoard[index] = newBoard[index - 1];
                newBoard[index - 1] = 0;
                possibleStates.add(new State(this, newBoard, depth + 1));
            }
            // Check to switch with one to right
            if (index%BOARD_WIDTH < BOARD_WIDTH - 1) {
                short[] newBoard = new short[BOARD_SIZE];
                System.arraycopy(board, 0, newBoard, 0, board.length);
                newBoard[index] = newBoard[index + 1];
                newBoard[index + 1] = 0;
                possibleStates.add(new State(this, newBoard, depth + 1));
            }

            return possibleStates;
        }

        /* getEstimatedCost
         * Returns f = g + h.
         */
        public int getEstimatedCost() {
            return depth + heuristic;
        }

        /* getHeuristic
         * Returns the heuristic value.
         */
        public int getHeuristic() {
            return heuristic;
        }

        /* setEndBoard
         * Sets the goal board. Used to calculate heuristic.
         */
        public static void setEndBoard(short[] endBoard) {
            State.endBoard = endBoard;
        }

        /* heuristic
         * Calculates the heuristic function for this state.
         * Uses sum of distances from tiles to end locations
         * Should only be called once by the constructor.
         */
        int heuristic() {
            /* Board where the location of each index's no. is stored at that index
             * e.g. Location of no. 3 in endBoard is stored at element 3 of refBoard
             */
            short [] refBoard = new short[BOARD_SIZE];
            for(short i = 0; i < BOARD_SIZE; i++)
                refBoard[endBoard[i]] = i;

            int h = 0, dest=0, delta_x=0, delta_y=0;
            for (int curr = 0; curr < BOARD_SIZE; curr++) {
                if (board[curr] != endBoard[curr] && board[curr] != 0) {
                    dest = refBoard[board[curr]];
                    delta_x = Math.abs(dest%BOARD_WIDTH - curr%BOARD_WIDTH);
                    delta_y = Math.abs(dest/BOARD_WIDTH - curr/BOARD_WIDTH);
                    h += delta_x + delta_y;
                }
            }
            return h; // + depth for f (but he wants h for now)
        }

        /* toString
         * Outputs the State as a String.
         */
        @Override
        public String toString() {
            String output = "\n";
            String horiLine = "";
            for (int i = 0; i < BOARD_WIDTH - 1; i++)
                horiLine+="\u2501";

            for(short i = 0; i < BOARD_SIZE; i++) {
                if (i % BOARD_WIDTH != 0)
                    output += "\u2503";
                if (BOARD_SIZE > 10 && board[i] < 10)
                    output += ' ';
                if (board[i] == 0)
                    output += "   ";
                else
                    output += " " + board[i] + " ";

                if (i % BOARD_WIDTH == BOARD_WIDTH - 1 && i / BOARD_WIDTH != BOARD_WIDTH - 1){
                    output += "\n ";
                    for (int j = 0; j < BOARD_WIDTH; j++){
                        if (j != 0)
                            output += "\u2501";
                        if (j != BOARD_WIDTH-1)
                            output += horiLine + "\u254B";
                    }
                    output += horiLine.substring(0, horiLine.offsetByCodePoints(0,BOARD_WIDTH-2)) + "\n";
                }
            }
            output += "\n\n";
            return output;
        }

        /* winPath
         * Recursively prints the path from the root node to this State.
         */
        public String winPath(){
            if(this.prev != null)
                return (this.prev).winPath() + this.toString();
            return this.toString();
        }

        /* compareTo
         * Necessary to implement Comparable, which is in turn necessary to be used in a PriorityQueue.
         */
        @Override
        public int compareTo(State other) {
            return getEstimatedCost() - other.getEstimatedCost();
        }

        /* 	equals
         *	Necessary to implement hashSet, used for seen (closed) set
         */
        @Override
        public boolean equals(Object o) {
            for(int i = 0; i < BOARD_SIZE; i++) {
                if (this.board[i] != ((State) o).board[i])
                    return false;
            }
            return true;
        }

        /*	hashCode
         *	Must be implemented whenever equals() is overridden
         */
        @Override
        public int hashCode() {
            int result = 0;
            int itrMax = (this.board.length == 9) ? this.board.length : 8;

            for(int i = 0; i < itrMax; i++) {
                result *= this.board.length;
                result += this.board[i];
            }
            return result;
        }
    }

    /* Validator
     * Validate and handle user input.
     */
    protected static class Validator {
        private static String inputPat15 = "^([0-9.,A-F]\\s+){15}[0-9.,A-F]$";
        private static String inputPat8 = "^([0-8.]\\s+){8}[0-8.]$";
        private static Pattern r15 = Pattern.compile(inputPat15);
        private static Pattern r8 = Pattern.compile(inputPat8);
        private static Matcher stringMatcher;

        /* parse
         * Parses a string with the inputPat regex to confirm the user has adhered to the format.
         */
        protected static Boolean parse(String input) {
            stringMatcher = (State.BOARD_SIZE == 16) ? r15.matcher(input) : r8.matcher(input);
            if (!stringMatcher.find()){
                return false;
            }

            return true;
        }

        /* formatInput
         * Converts a string to a short[]
         */
        private static short[] formatInput(String input){
            String[] strArr = input.split("\\s+");
            short[] shortArr = new short[strArr.length];

            for(int i = 0; i < shortArr.length; i++){
                shortArr[i] = Short.parseShort( strArr[i], 16 );
            }

            return shortArr;
        }

        /* containsDuplicates
         * Checks whether a short[] has duplicates in it.
         */
        protected static Boolean containsDuplicates(short[] arr){
            boolean[] seen = new boolean[State.BOARD_SIZE];
            for(int i = 0; i < arr.length; i++){
                short val = arr[i];
                if(val >= State.BOARD_SIZE || val < 0) {
                    return true;
                }
                if(seen[val]) {
                    return true;
                }

                seen[val] = true;
            }

            return false;
        }

        /* getBoard
         * Takes the name of the board to show to the user ("Start"/"End") and returns a fully validated board.
         */
        public static short[] getBoard(String name, String level) {
            String rules = "8";
            if(level=="15 Puzzle")
                rules = "9 and letters A through F"; // space saving boiiii

            while(true) {
                String input = JOptionPane.showInputDialog(null,
                        name + " State:\n* Numbers 0 through " + rules +"\n* In any order\n* Separated by spaces");
                if(input == null) {
                    // The user hit "Cancel"
                    System.exit(0);
                }
                if(!parse(input)) {
                    JOptionPane.showMessageDialog(null, "Incorrect format. Try again.");
                    continue;
                }
                short[] output = formatInput(input);
                if(containsDuplicates(output)) {
                    JOptionPane.showMessageDialog(null, "Must contain all numbers 0 through " + rules + ",\nand cannot have duplicates. \nTry again.");
                    continue;
                }

                return output;
            }
        }
    }

    /* main
     * Takes input from the user, validates it, and prints the state and its children to the screen.
     */
    public static void main(String[] args) {
        String response = getGameSize();

        // Get boards
        short board_start[] = Validator.getBoard("Start", response);
        short board_end[] = Validator.getBoard("End", response);

        // Set goal board
        State.setEndBoard(board_end);

        // Create starting State
        State state_start = new State(null, board_start, 0);

        // Set up our sets
        HashSet<State> seen = new HashSet<State>();
        PriorityQueue<State> open = new PriorityQueue<State>();

        // Set up our starting state
        seen.add(state_start);
        open.add(state_start);

        // Run A*
        try {
            State temp = open.poll();
            while(temp.getHeuristic() != 0){
                for(State child : temp.getNextStates()){
                    if(!(seen.contains(child))){
                        open.add(child);
                        seen.add(child);
                    }
                }

                temp=open.poll();
                if(temp==null){
                    System.out.println("Search exhausted; Unsolvable board.");
                    System.exit(0);
                }
            }

        } catch (OutOfMemoryError e) {
            System.out.println("Error: Out of Memory, puzzle is solvable.");
            System.exit(0);
        }
    }

    /* getGameSize
     * Uses a JOptionPane to determine the board size: either 8-puzzle or 15-puzzle
     */
    public static String getGameSize() {
        // Determine 8 or 15 Puzzle
        String options[] = {"8 Puzzle", "15 Puzzle"};
        String response = (String)JOptionPane.showInputDialog(null, "Choose whether you would like to play the 8 Puzzle or the 15 puzzle", null, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (response == null) {
            // User clicked 'Cancel'
            System.exit(0);
        }
        else if (response == options[0]) {
            State.BOARD_WIDTH = State.BOARD_HEIGHT = 3;
        } else {
            State.BOARD_WIDTH = State.BOARD_HEIGHT = 4;
        }
        State.BOARD_SIZE = (short)(State.BOARD_WIDTH * State.BOARD_HEIGHT);

        return response;
    }
}