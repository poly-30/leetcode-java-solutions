class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int aliceArrival = calculateDayOfYear(arriveAlice);
        int aliceLeaving = calculateDayOfYear(leaveAlice);
        int bobArrival = calculateDayOfYear(arriveBob);
        int bobLeaving = calculateDayOfYear(leaveBob);
        
        // The overlap period starts at the later arrival and ends at the earlier leaving
        int overlapStart = Math.max(aliceArrival, bobArrival);
        int overlapEnd = Math.min(aliceLeaving, bobLeaving);
        
        // If start is after end, there is no overlap
        if (overlapStart > overlapEnd) {
            return 0;
        }
        
        return overlapEnd - overlapStart + 1;
    }
    
    private int calculateDayOfYear(String date) {
        int[] daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; i++) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }
}