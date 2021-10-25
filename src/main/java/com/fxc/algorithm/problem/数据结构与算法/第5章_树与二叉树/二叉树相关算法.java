package com.fxc.algorithm.problem.数据结构与算法.第5章_树与二叉树;

import com.fxc.algorithm.problem.util.TreeNode;
import org.springframework.boot.autoconfigure.web.servlet.JspTemplateAvailabilityProvider;

import java.util.*;

/**
 * 二叉树遍历算法
 * 1、前序遍历非递归版
 * 2、中序遍历非递归版本
 * 3、后序遍历非递归版本
 * 4、二叉树上的查找算法
 * 5、统计二叉树节点个数
 * 6、通过层序遍历来完成统计二叉树节点个数
 * 7、求二叉树的最大深度
 * 8、判断两棵二叉树是否相等
 * 9、求二叉树根节点到目标节点的路径
 *
 * @author FXC
 */
public class 二叉树相关算法 {

    public static void main(String[] args) {
        二叉树相关算法 tool = new 二叉树相关算法();
        TreeNode root = new TreeNode(1, new TreeNode(2, null, 3), new TreeNode(4, 5, 6));
        System.out.println("**************** preorder ****************");
        tool.preorder(root);
        System.out.println("**************** inorder ****************");
        tool.inorder(root);
        System.out.println("**************** postorder ****************");
        tool.postorder(root);
        System.out.println("**************** countNode ****************");
        System.out.println("二叉树节点个数为：" + tool.countNode(root));
        System.out.println("**************** countNodeByLevelTraverse ****************");
        System.out.println("二叉树节点个数为：" + tool.countNodeByLevelTraverse(root));
        System.out.println("**************** maxDepth -- excepted:4 ****************");
        System.out.println("二叉树最大深度为: " + tool.maxDepth(new TreeNode(1, root, null)));
        System.out.println("**************** isSame -- excepted: true ****************");
        System.out.println("二叉树最大深度为: " + tool.isSame(root, root));
        System.out.println("**************** isSame -- excepted: false ****************");
        System.out.println("二叉树最大深度为: " + tool.isSame(root, new TreeNode(1, root, null)));
        System.out.println("**************** findPath -- excepted: 1 4 5 ****************");
        List<TreeNode> path = tool.findPath(root, root.right.left);
        System.out.println("路径为: ");
        for (TreeNode node : path) {
            System.out.print(node.val + "\t");
        }
        System.out.println();
        System.out.println("**************** countLeaf -- excepted: 3 ****************");
        System.out.println("二叉树叶节点个数为：" + tool.countLeaf(root));
    }

    /**
     * 前序遍历非递归版
     *
     * @param root 树的根节点
     */
    public void preorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode tmp = stack.pop();
            printNode(tmp);
            while (tmp != null) {
                if (tmp.left != null) {
                    printNode(tmp.left);
                }
                if (tmp.right != null) {
                    stack.push(tmp.right);
                }
                tmp = tmp.left;
            }
        }
    }

    /**
     * 中序遍历非递归版本
     *
     * @param root 二叉树根节点
     */
    public void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 将栈顶节点所有左孩子入栈
            while (stack.peek() != null) {
                stack.push(stack.peek().left);
            }
            // 弹出空节点，也就是说到了左子数的最左边了
            stack.pop();
            // 对当前节点操作 以及 处理右子树
            if (!stack.isEmpty()) {
                TreeNode tmp = stack.pop();
                // doSomething
                printNode(tmp);
                stack.push(tmp.right);
            }
        }
    }

    /**
     * 后序遍历非递归版本
     *
     * @param root 二叉树根节点
     */
    public void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        // last为指向当前遍历过程中最后一个访问的节点
        TreeNode last = null;
        // 标志当前节点右子数是否已经遍历完毕
        boolean flag = false;

        while (!stack.isEmpty()) {
            // 迭代将左子树全部入栈
            while (stack.peek() != null) {
                stack.push(stack.peek().left);
            }
            // 将空节点弹出，也就是说到了最左边了
            stack.pop();
            while (!stack.isEmpty()) {
                TreeNode tmp = stack.peek();
                // 当右子数不为空 且 右子数没有被遍历过
                if (tmp.right != null && tmp.right != last) {
                    stack.push(tmp.right);
                    flag = false;
                }
                // 右子数已经处理完毕
                else {
                    printNode(tmp);
                    stack.pop();
                    last = tmp;
                    flag = true;
                }
                // 如果是新加入的右子数节点，那么需要跳出内层循环，重新执行外层循环的逻辑
                if (!flag) {
                    break;
                }
            }
        }
    }

    /**
     * 二叉树上的查找算法
     *
     * @param root   二叉树根节点
     * @param target 要查找的目标节点
     * @return 返回 true：查找成功 false：查找失败
     */
    public boolean searchNode(TreeNode root, TreeNode target) {
        if (root == null) {
            return false;
        }
        if (root.equals(target)) {
            return true;
        }
        return searchNode(root.left, target) || searchNode(root.right, target);
    }

    /**
     * 统计二叉树节点个数
     *
     * @param root 二叉树根节点
     * @return 二叉树节点个数
     */
    public int countNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNode(root.left) + countNode(root.right);
    }

    /**
     * 通过层序遍历来完成统计二叉树节点个数
     *
     * @param root 二叉树根节点
     * @return 二叉树节点个数
     */
    public int countNodeByLevelTraverse(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        while (!queue.isEmpty()) {
            TreeNode tmp = queue.poll();
            count++;
            if (tmp.left != null) {
                queue.offer(tmp.left);
            }
            if (tmp.right != null) {
                queue.offer(tmp.right);
            }
        }
        return count;
    }

    /**
     * 求二叉树的最大深度
     *
     * @param root 二叉树根节点
     * @return 二叉树的最大深度
     */
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 判断两棵二叉树是否相等
     *
     * @param r1 树1的根节点
     * @param r2 树2的根节点
     * @return true: 相等; false: 不相等;
     */
    public boolean isSame(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        if (r1 == null || r2 == null) {
            return false;
        }
        if (r1.val != r2.val) {
            return false;
        }
        return isSame(r1.left, r2.left) && isSame(r1.right, r2.right);
    }

    public Stack<TreeNode> path = new Stack<>();
    /**
     * 求二叉树根节点到目标节点的路径
     * @param root 根节点
     * @param target 目标节点
     * @return 路径
     */
    public List<TreeNode> findPath(TreeNode root, TreeNode target) {
        inorderRecur(root, target);
        return path;
    }

    private void inorderRecur(TreeNode root, TreeNode target) {
        if (root == null || (!path.isEmpty() && path.peek().equals(target))) {
            return;
        }
        path.add(root);
        if (path.peek().equals(target)) {
            return;
        }
        inorderRecur(root.left, target);
        if (path.peek().equals(target)) {
            return;
        }
        inorderRecur(root.right, target);
        if (path.peek().equals(target)) {
            return;
        }
        path.pop();
    }

    /**
     * 统计二叉树所有叶子节点数目
     *
     * @param root 根节点
     * @return 二叉树所有叶子节点数目
     */
    public int countLeaf(TreeNode root) {
       if (root == null) {
           return 0;
       }
       if (root.left == null && root.right == null) {
           return 1;
       }
       return countLeaf(root.left) + countLeaf(root.right);
    }

    /**
     * 打印当前节点的值
     *
     * @param root 当前节点
     */
    private void printNode(TreeNode root) {
        System.out.println(root.val);
    }

}