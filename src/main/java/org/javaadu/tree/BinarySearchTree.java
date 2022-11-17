package org.javaadu.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree {

    TreeNode root;

    public void insert(int val) {
        if (root == null) {
            root = new TreeNode(val);
        } else {
            insertNode(root, val);
        }
    }

    private void insertNode(TreeNode node, int val) {
        if (node.val > val) {
            if (node.left == null) {
                node.left = new TreeNode(val);
            } else {
                insertNode(node.left, val);
            }
        } else if (node.val < val) {
            if (node.right == null) {
                node.right = new TreeNode(val);
            } else {
                insertNode(node.right, val);
            }
        }
    }

    public TreeNode find(int val) {
        return findNode(root, val);
    }

    private TreeNode findNode(TreeNode node, int val) {
        if (node == null) {
            return null;
        }
        if (node.val == val) {
            return node;
        } else if (node.val > val) {
            return findNode(node.left, val);
        } else {
            return findNode(node.right, val);
        }
    }

    /**
     * 查找前驱节点---找到当前节点，对其左子树进行向右遍历到底
     *
     * @param val
     * @return
     */
    public TreeNode findPre(int val) {
        TreeNode treeNode = findNode(root, val);
        if (treeNode == null) {
            return null;
        }
        TreeNode p = treeNode.left;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    /**
     * 查找后继节点---找到当前节点，对其右子树进行向左遍历到底
     *
     * @param val
     * @return
     */
    public TreeNode findPost(int val) {
        TreeNode treeNode = findNode(root, val);
        if (treeNode == null) {
            return null;
        }
        TreeNode p = treeNode.right;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    /**
     * 查找最大节点---直接向右遍历到底
     *
     * @return
     */
    public int findMaxVal() {
        if (root == null) {
            return -1;
        }
        TreeNode p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p.val;
    }

    /**
     * 查找最小节点---直接向左遍历到底
     *
     * @return
     */
    public int findMinVal() {
        if (root == null) {
            return -1;
        }
        TreeNode p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.val;
    }


    public void delete(int val) {
        //找到当前节点
        TreeNode cur = root;
        TreeNode parent = null;
        while (cur != null && cur.val != val) {
            parent = cur;
            if (cur.val < val) {
                //要删除的节点在右子树
                cur = cur.right;
            } else {
                //要删除的节点在左子树
                cur = cur.left;
            }
        }
        if (cur == null) {
            return;
        }

        //情况1：2个孩子，右子树继承parent，左子树移动到右子树里的最小节点之左
        if (parent != null && cur.left != null && cur.right != null) {
            TreeNode leftChild = cur.left;
            TreeNode rightChild = cur.right;

            TreeNode rightChildMinNode = rightChild.left;
            while (rightChildMinNode.left != null) {
                rightChildMinNode = rightChildMinNode.left;
            }
            rightChildMinNode.left = leftChild;

            parent.right = rightChild;
            return;
        }

        //情况2：0个孩子
        //情况3：1个孩子
        TreeNode child;
        if (cur.left != null) {
            child = cur.left;
        } else if (cur.right != null) {
            child = cur.right;
        } else {
            child = null;
        }
        if (parent == null) {
            root = child;
        } else if (parent.left == cur) {
            parent.left = child;
        } else if (parent.right == cur) {
            parent.right = child;
        }
    }

    public void inOrderPrint() {
        inOrderPrintWithNode(root);
        System.out.println();
    }

    private void inOrderPrintWithNode(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderPrintWithNode(node.left);
        System.out.print(node.val + " ");
        inOrderPrintWithNode(node.right);
    }

    public void preOrderPrint() {
        preOrderPrintWithNode(root);
        System.out.println();
    }

    private void preOrderPrintWithNode(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.val + " ");
        preOrderPrintWithNode(node.left);
        preOrderPrintWithNode(node.right);
    }

    public void postOrderPrint() {
        postOrderPrintWithNode(root);
        System.out.println();
    }

    private void postOrderPrintWithNode(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderPrintWithNode(node.left);
        postOrderPrintWithNode(node.right);
        System.out.print(node.val + " ");
    }

    public void levelOrderPrint() {
        if (root == null) {
            return;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            System.out.print(treeNode.val + " ");
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            }
        }
        System.out.println();
    }

    public int maxDepthSolution1() {
        if (root == null) {
            return 0;
        }
        return maxDepthSolutionWithNode(root);
    }

    private int maxDepthSolutionWithNode(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxDepthSolutionWithNode(node.left), maxDepthSolutionWithNode(node.right)) + 1;
    }

    public int maxDepthSolution2() {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        //层序遍历的终止条件
        while (!queue.isEmpty()) {
            //注意！！！这里num要提前算好，因为在for循环过程中还会改变queue的值
            int num = queue.size();
            //利用for循环，将当前层的节点都遍历完
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                if (node != null && node.left != null) {
                    queue.offer(node.left);
                }
                if (node != null && node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans++;
        }
        return ans;
    }

    public int maxDepthSolution3() {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node != null && node.left != null) {
                    queue.offer(node.left);
                }
                if (node != null && node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }

    /**
     * 验证二叉搜索树
     */
    private TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        //左子树
        boolean isLeft = isValidBST(root.left);

        //根节点
        if (pre != null && pre.val >= root.val) {
            //二叉搜索树中不会出现相同的节点，如果出现也是非法的
            return false;
        }
        pre = root;

        //右子树
        boolean isRight = isValidBST(root.right);

        //综合判断
        return isLeft && isRight;
    }

    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(3);
        binarySearchTree.insert(1);
        binarySearchTree.insert(0);
        binarySearchTree.insert(9);
        binarySearchTree.insert(8);
        binarySearchTree.inOrderPrint();
        System.out.println("max:" + binarySearchTree.findMaxVal()); //9
        System.out.println("min:" + binarySearchTree.findMinVal()); //0
        System.out.println("pre val:" + binarySearchTree.findPre(9).val); //8
        System.out.println("post val:" + binarySearchTree.findPost(3).val); //8
        System.out.println("isBinarySearchTree:" + binarySearchTree.isValidBST(binarySearchTree.root));

        System.out.println("pre order:");
        binarySearchTree.preOrderPrint(); //3,1,0,9,8

        System.out.println("post order");
        binarySearchTree.postOrderPrint(); //0,1,8,9,3

        System.out.println("level order");
        binarySearchTree.levelOrderPrint();//3,1,9,0,8

        BinarySearchTree binarySearchTree2 = new BinarySearchTree();
        binarySearchTree2.insert(3);
        binarySearchTree2.insert(1);
        binarySearchTree2.insert(0);
        binarySearchTree2.insert(10);
        binarySearchTree2.insert(4);
        binarySearchTree2.insert(5);
        binarySearchTree2.insert(6);
        binarySearchTree2.insert(7);
        binarySearchTree2.insert(11);
        System.out.println("binarySearchTree2 inorder:");
        binarySearchTree2.inOrderPrint();
        binarySearchTree2.delete(5);
        System.out.println("binarySearchTree2 delete 5:");
        binarySearchTree2.inOrderPrint();
        System.out.println("isBinarySearchTree:" + binarySearchTree2.isValidBST(binarySearchTree2.root));

        System.out.println(binarySearchTree2.maxDepthSolution1());
        System.out.println(binarySearchTree2.maxDepthSolution2());
        System.out.println(binarySearchTree2.maxDepthSolution3());
    }
}

