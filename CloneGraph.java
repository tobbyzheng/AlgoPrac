/* https://www.lintcode.com/problem/clone-graph/description */

/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     
 *     UndirectedGraphNode(int x) {
 *          label = x; 
 *          neighbors = new ArrayList<UndirectedGraphNode>(); 
       }
 * }
 */


public class Solution {
    /*
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        // write your code here
        
        if (node == null) {
            return null;
        }
        
        // initial
        LinkedList<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();
        HashMap<Integer, UndirectedGraphNode> createdNodes = new HashMap<>();
        
        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode cur = queue.poll();
            // create a new instance or get existing copied instance:
            UndirectedGraphNode copy = getNode(createdNodes, cur.label);
            for (UndirectedGraphNode n : cur.neighbors) {
                UndirectedGraphNode cn = getNode(createdNodes, n.label);
                copy.neighbors.add(cn);
                if (!set.contains(n)) {
                    queue.add(n);
                    set.add(n);
                }      
            }
        }
        
        return createdNodes.get(node.label);
    }
    
    
    private UndirectedGraphNode getNode(HashMap<Integer, UndirectedGraphNode> createdNodes, int label) {
        if (!createdNodes.containsKey(label)) {
            UndirectedGraphNode tmp = new UndirectedGraphNode(label);
            createdNodes.put(label, tmp);
            return tmp;
        } else {
            return createdNodes.get(label);
        }
    }
}