package tree;

import java.util.LinkedList;
import java.util.Queue;

public class MyBinaryTree {

    public static void main(String[] args) {
        Queue<TreeNode> queue = new LinkedList<>();
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
