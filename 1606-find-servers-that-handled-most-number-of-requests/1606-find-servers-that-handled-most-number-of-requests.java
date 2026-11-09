import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Array to keep track of the number of requests handled by each server
        int[] requestCount = new int[k];
        
        // TreeSet to store currently available (idle) server IDs
        TreeSet<Integer> available = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            available.add(i);
        }
        
        // Min-heap to track busy servers sorted by their task completion time: [end_time, server_id]
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        for (int i = 0; i < arrival.length; i++) {
            int arrivalTime = arrival[i];
            
            // 1. Free up all servers that have finished their tasks by the current arrival time
            while (!busyHeap.isEmpty() && busyHeap.peek()[0] <= arrivalTime) {
                int[] finished = busyHeap.poll();
                available.add(finished[1]);
            }
            
            // 2. If no servers are currently available, the request is dropped
            if (available.isEmpty()) {
                continue;
            }
            
            // 3. Find the preferred server using circular lookup starting at i % k
            int preferredServer = i % k;
            Integer targetServer = available.ceiling(preferredServer);
            
            // If no server ID >= preferredServer is found, wrap around to the smallest available ID
            if (targetServer == null) {
                targetServer = available.first();
            }
            
            // 4. Assign the request to the target server
            requestCount[targetServer]++;
            busyHeap.offer(new int[]{arrivalTime + load[i], targetServer});
            available.remove(targetServer);
        }
        
        // Find the maximum number of requests handled by any single server
        int maxRequests = 0;
        for (int count : requestCount) {
            maxRequests = Math.max(maxRequests, count);
        }
        
        // Collect all server IDs that achieved this maximum count
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}