import java.util.Vector;
class Solution {
    private int vect_top(Vector<Integer> v) {
        if (v.isEmpty()) {
            return Integer.MIN_VALUE; 
        }
        return v.get(v.size() - 1);
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Vector<Integer> v1 = new Vector<>();
        int rem = 0;
        for (int i : pushed) {
            v1.add(i);
            while (!v1.isEmpty() && rem < popped.length) {
                int top = vect_top(v1);
                if (top == popped[rem]) {
                    v1.remove(v1.size() - 1); 
                    rem++;
                } else {
                    break;
                }
            }
        }
        return v1.isEmpty();
    }
}