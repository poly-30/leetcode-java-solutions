class Solution {
    private static final double EPSILON = 1e-6;

    public boolean judgePoint24(int[] card4s) {
        List<Double> nums = new ArrayList<>();
        for (int num : card4s) {
            nums.add((double) num);
        }
        return helper(nums);
    }

    private boolean helper(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < EPSILON;
        }
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.size(); j++) {
                if (i == j) continue;
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < nums.size(); k++) {
                    if (k != i && k != j) next.add(nums.get(k));
                }
                for (int op = 0; op < 4; op++) {
                    if (op == 0) next.add(nums.get(i) + nums.get(j));
                    if (op == 1) next.add(nums.get(i) - nums.get(j));
                    if (op == 2) next.add(nums.get(i) * nums.get(j));
                    if (op == 3) {
                        if (Math.abs(nums.get(j)) < EPSILON) continue;
                        next.add(nums.get(i) / nums.get(j));
                    }
                    if (helper(next)) return true;
                    next.remove(next.size() - 1);
                }
            }
        }
        return false;
    }
}