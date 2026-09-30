class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] result = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                depth++;
                result[i] = depth % 2; // Assign based on current depth level
            } else {
                result[i] = depth % 2; // Assign matching closing bracket to same group
                depth--;
            }
        }

        return result;
    }
}