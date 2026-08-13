class Solution {
    class Node {
        int maxLen;
        int prefixLen;
        int suffixLen;
        char leftChar;
        char rightChar;

        Node() {}
    }

    private Node[] tree;
    private char[] chars;
    private int n;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        chars = s.toCharArray();
        n = chars.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            
            chars[idx] = ch;
            update(1, 0, n - 1, idx, ch);
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;
        res.maxLen = Math.max(left.maxLen, right.maxLen);

        // Check if adjacent characters match across the midpoint boundary
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffixLen + right.prefixLen);
        }

        // Calculate prefix length for res
        if (left.prefixLen == leftLen && left.leftChar == right.leftChar) {
            res.prefixLen = left.prefixLen + right.prefixLen;
        } else {
            res.prefixLen = left.prefixLen;
        }

        // Calculate suffix length for res
        if (right.suffixLen == rightLen && right.rightChar == left.rightChar) {
            res.suffixLen = right.suffixLen + left.suffixLen;
        } else {
            res.suffixLen = right.suffixLen;
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            Node leaf = new Node();
            leaf.maxLen = 1;
            leaf.prefixLen = 1;
            leaf.suffixLen = 1;
            leaf.leftChar = chars[start];
            leaf.rightChar = chars[start];
            tree[node] = leaf;
            return;
        }

        int mid = start + (end - start) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        build(leftNode, start, mid);
        build(rightNode, mid + 1, end);

        tree[node] = merge(tree[leftNode], tree[rightNode], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            tree[node].leftChar = ch;
            tree[node].rightChar = ch;
            return;
        }

        int mid = start + (end - start) / 2;
        int leftNode = 2 * node;
        int rightNode = 2 * node + 1;

        if (idx <= mid) {
            update(leftNode, start, mid, idx, ch);
        } else {
            update(rightNode, mid + 1, end, idx, ch);
        }

        tree[node] = merge(tree[leftNode], tree[rightNode], mid - start + 1, end - mid);
    }
}