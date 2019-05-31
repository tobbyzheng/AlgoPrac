/* https://www.lintcode.com/problem/number-of-islands/description */

public class Solution {
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int count = 0;
        
        for (int x = 0; x < grid.length; x ++) {
            for (int y = 0; y < grid[0].length; y ++) {
                if (grid[x][y] && !visited[x][y]) {
                    System.out.println(x+","+y);
                    count += 1;
                    bfs(grid, visited, x, y);
                }
            }
        }
        return count;
    }
    
    private void bfs(boolean grid[][], boolean[][] visited, int startX, int startY) {
        int[] changeX = {0, 0, 1, -1}; // up, down, right, left
        int[] changeY = {1, -1, 0, 0}; 
        
        LinkedList<Integer> queueX = new LinkedList<>();
        LinkedList<Integer> queueY = new LinkedList<>();
        queueX.add(startX);
        queueY.add(startY);
        visited[startX][startY] = true;

        while (!queueX.isEmpty()) {
            int curX = queueX.poll();
            int curY = queueY.poll();

            for (int i = 0; i < 4; i ++) {
                int endX = startX + changeX[i];
                int endY = startY + changeY[i];

                if (endX >= 0 && endX < grid.length && endY >= 0 && endY < grid[0].length && grid[endX][endY] && !visited[endX][endY]) {
                    queueX.add(endX);
                    queueY.add(endY);
                    visited[endX][endY] = true;
                }
            }
        }
    }
} 