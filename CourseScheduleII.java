// https://www.lintcode.com/problem/course-schedule-ii/description

public class Solution {
    /*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
        HashSet<Integer>[] edges = new HashSet[numCourses];
        int[] inDegrees = new int[numCourses];
        
        for (int courseIdx = 0; courseIdx < numCourses; courseIdx ++) {
            edges[courseIdx] = new HashSet<>();
        }
        
        for (int prereqIdx = 0; prereqIdx < prerequisites.length; prereqIdx ++) {
            inDegrees[prerequisites[prereqIdx][0]] += 1;
            edges[prerequisites[prereqIdx][1]].add(prerequisites[prereqIdx][0]);
        }
        
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i ++) {
            if (inDegrees[i] == 0) queue.add(i);
        }
        
        int[] order = new int[numCourses];
        int count = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            order[count++] = cur;
            
            for (Integer neighbour : edges[cur]) {
                inDegrees[neighbour] -= 1;
                if (inDegrees[neighbour] == 0) {
                    queue.add(neighbour);
                }
            }
        }
        System.out.println(count); 
        if (count == numCourses) {
            return order;
        } else {
            return new int[0];
        }
        
    }
}