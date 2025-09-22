class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] freq = new int[101]; // Because input values are between 1 and 100
        for (int num : nums) {
            freq[num]++;
        }
        int maxFreq = 0;
        for (int count : freq) {
            if (count > maxFreq) {
                maxFreq = count;
            }
        }
        int ans = 0;
        for (int count : freq) {
            if (count == maxFreq) {
                ans += count;
            }
        }
        return ans;
    }
}
