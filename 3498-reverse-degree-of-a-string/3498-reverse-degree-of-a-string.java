class Solution {
    public int reverseDegree(String s) {
        int totalReverseDegree = 0;
        
        for (int i = 0; i < s.length(); i++) {
            int reversedAlphabetIndex = 26 - (s.charAt(i) - 'a');
            int stringIndex = i + 1; // 1-indexed
            
            totalReverseDegree += reversedAlphabetIndex * stringIndex;
        }
        
        return totalReverseDegree;
    }
}