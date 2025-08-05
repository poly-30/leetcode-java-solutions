class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If the current node is null, create a new TreeNode with the given value
        if (root == null) {
            return new TreeNode(val);
        }

        // If the value to insert is less than the current node's value,
        // insert it into the left subtree
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else {
            // Otherwise, insert it into the right subtree
            root.right = insertIntoBST(root.right, val);
        }

        // Return the unchanged root pointer
        return root;
    }
}