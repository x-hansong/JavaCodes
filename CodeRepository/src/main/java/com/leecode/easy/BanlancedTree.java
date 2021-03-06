package com.leecode.easy;

import com.leecode.datastructure.TreeNode;

/**
 * Created by xhans on 2016/2/14.
 */
public class BanlancedTree {
    public int maxDepth(TreeNode root){
        if (root == null){
            return 0;
        }else {
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
        }
    }

    public boolean isBalanced(TreeNode root) {
        if (root == null){
            return true;
        }
        if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1){
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }
}
