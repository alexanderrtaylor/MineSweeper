public class BinaryTree {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    class Solution {
        public int rangeSumBST(TreeNode root, int L, int R) {

            int sum = 0;
            if (root == null){
                return 0;
            }
            if(L <= root.val && R >= root.val){
                sum = root.val + sum;
            }
            if(L < root.val && root.left != null){
                sum = sum + rangeSumBST(root.left, L, R);
            }
            if(R > root.val && root.right != null){
                sum = sum + rangeSumBST(root.right, L, R);
            }

            return sum;

            /*10
             0    15
            3 7     18
            first example 3 and 5
            second 7 and 15
            third 15 and 18


            10
            5    15
            3 7  13  18
            1  6

               10
              8  16

            */

        }
    }
}
