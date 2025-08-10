class Solution {
public boolean reorderedPowerOf2(int n) {
String sig = signature(n);
for (int i = 0; i < 31; i++) { // 2^0 to 2^30 covers up to 1,073,741,824 (> 1e9)
int pow = 1 << i;
if (sig.equals(signature(pow))) return true;
}
return false;
}
private String signature(int x) {
    int[] cnt = new int;
    while (x > 0) {
        cnt[x % 10]++;
        x /= 10;
    }
    // Build a compact string signature like "#c0#c1...#c9"
    StringBuilder sb = new StringBuilder(40);
    for (int c : cnt) {
        sb.append('#').append(c);
    }
    return sb.toString();
}
}