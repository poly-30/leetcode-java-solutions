import java.util.HashSet;
import java.util.Set;
class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] boxes = new HashSet[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashSet<>();
            cols[i] = new HashSet<>();
            boxes[i] = new HashSet<>();
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char val = board[i][j];
                if (val == '.') {
                    continue;
                }
                if (!rows[i].add(val)) {
                    return false;
                }
                if (!cols[j].add(val)) {
                    return false;
                }
                int boxIndex = (i / 3) * 3 + (j / 3);
                if (!boxes[boxIndex].add(val)) {
                    return false;
                }
            }
        }
        return true;
    }
}