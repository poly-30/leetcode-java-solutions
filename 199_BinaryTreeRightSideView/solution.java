import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<ArrayList<TreeNode>> q = new LinkedList<>();
        q.add(new ArrayList<>(Arrays.asList(root)));

        while (!q.isEmpty()) {
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            ArrayList<TreeNode> currentLevel = q.poll();

            for (TreeNode node : currentLevel) {
                if (node.left != null) {
                    nextLevel.add(node.left);
                }
                if (node.right != null) {
                    nextLevel.add(node.right);
                }
            }

            if (!nextLevel.isEmpty()) {
                ans.add(nextLevel.get(nextLevel.size() - 1).val);
                q.add(nextLevel);
            }
        }

        ans.add(0, root.val);  // Include root's value as the first visible node
        return ans;
    }
}