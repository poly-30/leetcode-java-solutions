class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> prev = new HashSet<>();
        for (int num : arr) {
            Set<Integer> cur = new HashSet<>();
            cur.add(num);
            for (int x : prev) {
                cur.add(num | x);
            }
            res.addAll(cur);
            prev = cur;
        }
        return res.size();
    }
}