class Solution {
    public int[] findRightInterval(int[][] intervals) {
        
        HashMap<Integer, Integer> map = new HashMap<>();
        int m = intervals.length;
        int n = intervals[0].length;
        int[] sp = new int[m];                  //array of starting points
        
        for(int i = 0; i < m; i++) {
            sp[i] = intervals[i][0];            
            map.put(sp[i], i);                  //(key=start_point, val=index)
        }
        
        Arrays.sort(sp);                        //sort array of starting points
        int[] result = new int[m];
        
        for(int i = 0; i < m; i++) {
            int l = 0, r = m - 1;
            boolean found = false;              //to see if result was found
            int min = -1;
            int ep = intervals[i][n - 1];       //ep = endpoint
            while(l <= r) {                     //binarySearch on arr of start points
                int mid = (l + ((r - l) / 2));
                if(sp[mid] >= ep) {
                    min = sp[mid];              
                    found = true;               
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
            result[i] = found ? map.get(min) : -1;
        }
        return result;
    }
}