//package CS4125.View;
//
//import CS4125.View.EventHandlers.UIController;
//import javafx.scene.Node;
//import javafx.scene.shape.Circle;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * A dummy Sim to show how UI interaction can be implemented
// */
//public class UISim {
//    private UIController controller;
//    private ArrayList<NodeDelay> nodes;
//
//    public UISim(UIController controller) {
//        this.controller = controller;
//    }
//
//    public void run(){
//        nodes = new ArrayList<NodeDelay>();
//        nodes.add(new NodeDelay("Simple Junction", "A", 300, 400, 1000));
//        nodes.add(new NodeDelay("Simple Junction", "B", 100, 400, 2000));
//        nodes.add(new NodeDelay("Simple Junction", "C", 300, 200, 5000));
//        for (NodeDelay n : nodes){
//            controller.addNode(n);
//        }
//
//        controller.addEdge(nodes.get(0), nodes.get(1)); // Adding roads between nodes
//        controller.addEdge(nodes.get(1), nodes.get(2));
//
//        // Adding car animated along path in list of NodeDelay (
//        controller.addCar(new NodeDelay[]{
//                nodes.get(0),
//                nodes.get(1),
//                nodes.get(2)});
//    }
//
//    public void addNode(String tcmType, String name, int x, int y, int delay){
//        nodes.add(new NodeDelay(tcmType, name, x, y, delay));
//        controller.addNode(nodes.get(nodes.size()-1));
//    }
//
//    public void addEdge(NodeDelay n1, NodeDelay n2) {
//        controller.addEdge(n1, n2);
//    }
//
//}
