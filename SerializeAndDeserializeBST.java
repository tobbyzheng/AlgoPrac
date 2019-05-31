// https://www.lintcode.com/problem/serialize-and-deserialize-binary-tree/description

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class Solution {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "{}";
        }
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        
        // initial
        queue.add(root);
        
        for (int i = 0; i < queue.size(); i ++) {
            TreeNode cur = queue.get(i);
            if (cur == null) {
                continue;
            }
            
            queue.add(cur.left);
            queue.add(cur.right);
        }
        
        while (queue.getLast() == null) {
            queue.removeLast();
        }
        
        StringBuilder sb = new StringBuilder();
        //sb.append("{");
        Iterator<TreeNode> it = queue.iterator();
        
        while (it.hasNext()) {
            TreeNode tn = it.next();
            if (tn == null) {
                sb.append("#");
            } else {
                sb.append(tn.val);
            }
            
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        //sb.append("}");
        //System.out.println(sb.toString());
        
        return sb.toString();
    }
    

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data == "{}") {
            return null;
        }
        
        String[] values = data.split(",");
        
        // initial
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        boolean isLeftChild = true;
        int queueIdx = 0;
        ArrayList<TreeNode> queue =new ArrayList<>();
        queue.add(root);
        
        for (int i = 1; i < values.length; i ++) {
            if (!values[i].equals("#")) {
                TreeNode newNode = new TreeNode(Integer.valueOf(values[i]));
                if (isLeftChild) {
                    queue.get(queueIdx).left = newNode;
                } else {
                    queue.get(queueIdx).right = newNode;
                }
                queue.add(newNode);
            }
            
            if (!isLeftChild) {
                queueIdx += 1;
            }
            
            isLeftChild = !isLeftChild;
        }
        
        return root;
        
        
    }
}