// https://www.lintcode.com/problem/sequence-reconstruction/description
public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        
        int[] inDegrees = new int[org.length];
        HashSet<Integer>[] edges = new HashSet[org.length];
        HashSet<Integer> realEdges = new HashSet<>();
         
        for (int i = 0; i < org.length; i ++) {
            edges[i] = new HashSet<>();
        }
        
        for (int i = 0; i < seqs.length; i ++) {
            for (int j = 0; j < seqs[i].length; j ++) {
                realEdges.add(seqs[i][j]);
            }
        }
        
        if (realEdges.size() != org.length) return false;
        
        for (int i = 0; i < seqs.length; i ++) {
            for (int j = 0; j < seqs[i].length - 1; j ++) {
                if (!edges[seqs[i][j] - 1].contains(seqs[i][j+1] - 1)) {
                    inDegrees[seqs[i][j+1] - 1] += 1;
                    edges[seqs[i][j] - 1].add(seqs[i][j+1] - 1);
                }
            }
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        ArrayList<Integer> order = new ArrayList<>();
        for (int i = 0; i < org.length; i ++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
       
        
        if (queue.size() > 1) return false;
       
        int count = 0;
        
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            System.out.println("cur:"+cur);
            order.add(cur);
            count += 1;
            
            for (Integer neighbour : edges[cur]) {
                inDegrees[neighbour] -= 1;
                if (inDegrees[neighbour] == 0) {
                    
                    queue.add(neighbour);
                }
            }
            
            if (queue.size() > 1) return false;
        }
        
        if (count == org.length) {
            for (int i = 0; i < count; i ++) {
                if (org[i] != (order.get(i) + 1)) {
                    return false;
                }
            }
            return true;
        } else return false;
    }
}