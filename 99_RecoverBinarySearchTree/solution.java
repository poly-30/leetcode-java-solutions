class Solution {
    private TreeNode first;
    private TreeNode second;
    private TreeNode prev;
    public void recoverTree(TreeNode root) {
        prev = new TreeNode(Integer.MIN_VALUE);
        inorderTraversal(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }
    private void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        if (first == null && prev.val > node.val) {

            first = prev;
        }

        if (first != null && prev.val > node.val) {
            second = node;
        }
        prev = node;
        inorderTraversal(node.right);
    }
}