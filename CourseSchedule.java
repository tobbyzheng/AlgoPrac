// https://www.lintcode.com/problem/course-schedule/description
public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: true if can finish all courses or false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // write your code here
        
        ArrayList<Integer>[] edges = new ArrayList[numCourses];
        int[] inDegrees = new int[numCourses];
        
        for (int courseIdx = 0; courseIdx < numCourses; courseIdx ++) {
            edges[courseIdx] = new ArrayList<>();
        }
        
        for (int prereqIdx = 0; prereqIdx < prerequisites.length; prereqIdx ++) {
            inDegrees[prerequisites[prereqIdx][0]] += 1;
            edges[prerequisites[prereqIdx][1]].add(prerequisites[prereqIdx][0]);
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++) {
            if (inDegrees[i] == 0) queue.add(i);
        }
        
        //ArrayList<Integer> order = new ArrayList<>();
        int count = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            //order.add(cur);
            count += 1;
            
            for (Integer neighbour : edges[cur]) {
                inDegrees[neighbour] -= 1;
                if (inDegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }
        
        return count == numCourses;
    } 
} 