import java.util.*;

class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);  // For rainy days, result is always -1
        
        TreeSet<Integer> sunnyDays = new TreeSet<>();  // Indices of sunny days
        Map<Integer, Integer> lastRainedLakes = new HashMap<>();  // lake -> last day it rained
        
        for (int day = 0; day < n; day++) {
            int lake = rains[day];
            
            if (lake > 0) {  // Rainy day
                if (lastRainedLakes.containsKey(lake)) {
                    // Lake was already full - need to find a sunny day to dry it before today
                    Integer dryDay = sunnyDays.higher(lastRainedLakes.get(lake));
                    if (dryDay == null) {
                        // No sunny day available to dry this lake -> flood occurs
                        return new int[0];
                    }
                    result[dryDay] = lake;  // Dry this lake on that sunny day
                    sunnyDays.remove(dryDay);
                }
                // Update last day this lake was filled
                lastRainedLakes.put(lake, day);
            } else {
                // Sunny day, can dry any lake, initially assign 1 to dry arbitrary lake if unused later
                sunnyDays.add(day);
                result[day] = 1;
            }
        }
        
        // If any sunny days left unused, keep them as drying lake 1 (arbitrary)
        return result;
    }
}
