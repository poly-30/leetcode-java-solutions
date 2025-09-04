class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, null, null);
    }
    
    private boolean isValid(TreeNode node, Integer min, Integer max) {
        if (node == null) return true;
        int val = node.val;
        if (min != null && val <= min) return false;
        if (max != null && val >= max) return false;
        return isValid(node.left, min, val) && isValid(node.right, val, max);
    }
}
