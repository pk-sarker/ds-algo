package algo.BFS;

import Common.KeyValuePair;
import Common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CountGoodNodes {

    public static int goodNodes(TreeNode root) {

        Queue<KeyValuePair<TreeNode, Integer>> queue = new LinkedList<>();

        queue.offer(new KeyValuePair(root, Integer.MIN_VALUE));
        int count =0;
        while(!queue.isEmpty()) {
            KeyValuePair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.key;
            Integer value = pair.value;

            if (node.val >= value) {
                count++;
                System.out.println(" Node: " + node.val + " count: " + count);
            }
            int max = Math.max(node.val, value);

            if (node.left != null) {
                queue.add(new KeyValuePair(node.left, max));
            }
            if (node.right != null) {
                queue.add(new KeyValuePair(node.right, max));
            }
        }

        return count;
    }

    public static int goodNodesDFS(TreeNode root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    public static int dfs(TreeNode root, int maxSoFar) {
        if (root == null) return 0;
        int res = root.val >= maxSoFar ? 1 : 0;
        res += dfs(root.left, Math.max(maxSoFar, root.val));
        res += dfs(root.right, Math.max(maxSoFar, root.val));
        return res;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(3, new TreeNode(1, new TreeNode(3), null), new TreeNode(4, new TreeNode(1), new TreeNode(5)));

        System.out.println(CountGoodNodes.goodNodes(root));

        System.out.println(CountGoodNodes.goodNodesDFS(root));

        System.out.println(CountGoodNodes.goodNodes(new TreeNode(2)));
    }

}
