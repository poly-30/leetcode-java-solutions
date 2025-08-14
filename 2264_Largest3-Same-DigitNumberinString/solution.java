class Solution {
    public String largestGoodInteger(String num) {
        // Check from largest digit to smallest
        for (int d = 9; d >= 0; d--) {
            String triplet = String.valueOf(d).repeat(3); // create ddd
            if (num.contains(triplet)) {
                return triplet;
            }
        }
        return "";
    }
}
