/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode, Integer> nodeToDegree = new HashMap<>();
        updateDegree(graph, nodeToDegree);
        
        ArrayList<DirectedGraphNode> order = new ArrayList<>();
        LinkedList<DirectedGraphNode> queue = new LinkedList<>();
        queue.addAll(getFirstZeroDegreeNodes(nodeToDegree));
        while (!queue.isEmpty()) {
            DirectedGraphNode cur = queue.poll();
            order.add(cur);
            
            for (DirectedGraphNode neighbor : cur.neighbors) {
                nodeToDegree.put(neighbor, nodeToDegree.get(neighbor) - 1);
                if (nodeToDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }
        
        return order;
        
    }
    
    private void updateDegree(ArrayList<DirectedGraphNode> graph, HashMap<DirectedGraphNode, Integer> nodeToDegree) {
        for (DirectedGraphNode node : graph) {
            nodeToDegree.put(node, 0);
        }
        
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                nodeToDegree.put(neighbor, nodeToDegree.get(neighbor) + 1);
            }
        }
        
        
    }
    
    private ArrayList<DirectedGraphNode> getFirstZeroDegreeNodes(HashMap<DirectedGraphNode, Integer> nodeToDegree) {
        ArrayList<DirectedGraphNode> results = new ArrayList<>();
        for (DirectedGraphNode node : nodeToDegree.keySet()) {
            if (nodeToDegree.get(node) == 0) {
                results.add(node);
            }
        }
        return results;
    }
        
    
}