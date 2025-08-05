class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
         List<int[]> intersections = new ArrayList<>(); // to store all 
         // intersecting intervals
        // index "i" to iterate over the length of list a and index "j"
        // to iterate over the length of list b
        int i = 0, j = 0;
        // while loop will break whenever either of the lists ends
        while (i < firstList.length && j < secondList.length) {
            // Let's check if firstList[i] intersects secondList[j]
            // 1. start - the potential startpoint of the intersection
            // 2. end - the potential endpoint of the intersection
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            
            if (start <= end) // if this is an actual intersection
                intersections.add(new int[]{start, end}); // add it to the list
            // Move forward in the list whose interval ends earlier
            if (firstList[i][1] < secondList[j][1])
                i += 1;
            else
                j += 1;
        }

        return intersections.toArray(new int[0][]);
    }
}