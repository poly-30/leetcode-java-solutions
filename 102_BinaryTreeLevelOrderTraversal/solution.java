import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    /**
     * Given the root of a binary tree, return the level order traversal of its nodes' values.
     * (i.e., from left to right, level by level).
     *
     * @param root The root of the binary tree.
     * @return A list of lists of integers representing the level order traversal.
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // The final list that will store all levels.
        List<List<Integer>> result = new ArrayList<>();

        // If the tree is empty, return an empty list.
        if (root == null) {
            return result;
        }

        // Use a queue for the Breadth-First Search (BFS) traversal.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Continue traversal as long as there are nodes to process.
        while (!queue.isEmpty()) {
            // Get the number of nodes at the current level.
            int levelSize = queue.size();
            
            // Create a temporary list to store nodes for the current level.
            List<Integer> currentLevel = new ArrayList<>();

            // Process all nodes at the current level.
            for (int i = 0; i < levelSize; i++) {
                // Remove the node from the front of the queue.
                TreeNode currentNode = queue.poll();
                
                // Add its value to the current level's list.
                currentLevel.add(currentNode.val);

                // If the node has a left child, add it to the queue for the next level.
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                }

                // If the node has a right child, add it to the queue for the next level.
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                }
            }
            
            // Add the completed level to the final result list.
            result.add(currentLevel);
        }

        return result;
    }
}
