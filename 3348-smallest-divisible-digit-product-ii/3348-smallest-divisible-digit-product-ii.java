class Solution {
    public String smallestNumber(String num, long t) {
        int[] need = fact(t);
        if (need == null) return "-1";
        int n = num.length();
        int[] fc = red(need);
        int rl = sum(fc);
        if (rl > n) return build(fc, rl);

        int[] tot = new int[4];
        for (int i = 0; i < n; i++) add(tot, de(num.charAt(i) - '0'));

        int fz = num.indexOf('0');
        if (fz == -1) {
            fz = n;
            if (covers(need, tot)) return num;
        }

        int[] pre = tot.clone();
        for (int i = n - 1; i >= 0; i--) {
            int d = num.charAt(i) - '0';
            pre = diff(pre, de(d));
            int sp = n - 1 - i;
            if (i > fz) continue;
            int[] base = diff(need, pre);
            for (int g = d + 1; g <= 9; g++) {
                int[] dc = red(diff(base, de(g)));
                int c = sum(dc);
                if (c <= sp) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i).append((char) ('0' + g));
                    for (int k = 0; k < sp - c; k++) sb.append('1');
                    asc(sb, dc);
                    return sb.toString();
                }
            }
        }
        return build(fc, n + 1);
    }

    int[] fact(long t) {
        int[] p = {2, 3, 5, 7}, e = new int[4];
        for (int i = 0; i < 4; i++) while (t % p[i] == 0) { t /= p[i]; e[i]++; }
        return t == 1 ? e : null;
    }

    int[] de(int d) {
        switch (d) {
            case 2: return new int[]{1,0,0,0};
            case 3: return new int[]{0,1,0,0};
            case 4: return new int[]{2,0,0,0};
            case 5: return new int[]{0,0,1,0};
            case 6: return new int[]{1,1,0,0};
            case 7: return new int[]{0,0,0,1};
            case 8: return new int[]{3,0,0,0};
            case 9: return new int[]{0,2,0,0};
            default: return new int[]{0,0,0,0};
        }
    }

    void add(int[] a, int[] b) { for (int i = 0; i < 4; i++) a[i] += b[i]; }

    int[] diff(int[] a, int[] b) {
        int[] r = new int[4];
        for (int i = 0; i < 4; i++) r[i] = Math.max(0, a[i] - b[i]);
        return r;
    }

    boolean covers(int[] need, int[] have) {
        for (int i = 0; i < 4; i++) if (have[i] < need[i]) return false;
        return true;
    }

    int[] red(int[] ne) {
        int c2 = ne[0], c3 = ne[1], c5 = ne[2], c7 = ne[3];
        int c8 = c2 / 3, r2 = c2 % 3;
        int c9 = c3 / 2, t3 = c3 % 2;
        int c4 = r2 / 2, t2 = r2 % 2;
        int c6 = 0;
        if (t2 == 1 && t3 == 1) { t2 = 0; t3 = 0; c6 = 1; }
        if (t3 == 1 && c4 == 1) { t2 = 1; c6 = 1; t3 = 0; c4 = 0; }
        int[] dc = new int[10];
        dc[2]=t2; dc[3]=t3; dc[4]=c4; dc[5]=c5; dc[6]=c6; dc[7]=c7; dc[8]=c8; dc[9]=c9;
        return dc;
    }

    int sum(int[] dc) { int s = 0; for (int v : dc) s += v; return s; }

    void asc(StringBuilder sb, int[] dc) {
        for (int d = 2; d <= 9; d++) for (int k = 0; k < dc[d]; k++) sb.append((char)('0'+d));
    }

    String build(int[] dc, int len) {
        int c = sum(dc);
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < len - c; k++) sb.append('1');
        asc(sb, dc);
        return sb.toString();
    }
}