class Solution {
    public String digitSum(String s, int k) {
        while (s.length() > k) {
            StringBuilder newS = new StringBuilder();
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                for (int j = 0; j < k && i + j < s.length(); j++) {
                    sum += s.charAt(i + j) - '0';
                }
                newS.append(sum);
            }
            s = newS.toString();
        }
        return s;
    }
}