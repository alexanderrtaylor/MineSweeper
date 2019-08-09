import java.util.ArrayList;
import java.util.List;

public class allPossibleFBT {

    public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }

    public static List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> returnedList = new ArrayList<>();


        if ((N % 2) == 1) {
            if (N == 1) {
                TreeNode basicNode = new TreeNode(0);
                returnedList.add(basicNode);
            } else if (N ==3) {
                TreeNode basicNode = new TreeNode(0);
                returnedList.add(basicNode);
                basicNode.left = new TreeNode(0);
                basicNode.right = new TreeNode(0);
            }
            else
                {
                for (int i = 0; i < N-1; i ++) {
                    List<TreeNode> left = allPossibleFBT(1 + i);
                    List<TreeNode> right = allPossibleFBT(N - i -2);
                    for (int j = 0; j < left.size(); j++) {
                        for (int k = 0; k < right.size(); k++) {
                            TreeNode basicNode = new TreeNode(0);
                            basicNode.left = left.get(j);
                            basicNode.right = right.get(k);
                            returnedList.add(basicNode);
                        }
                    }
                }
            }
        }
                /*if(returnedList.size()){
                        //Left side
                        returnedList.get(i).left = allPossibleFBT(1 + i).get(i);
                        //Right side
                        returnedList.get(i).right = allPossibleFBT(N - i).get(i);
                    }
                    else{
                        returnedList.add(allPossibleFBT(1).get(i));
                        //Left side
                        returnedList.get(i).left = allPossibleFBT(1 + i).get(i);
                        //Right side
                        returnedList.get(i).right = allPossibleFBT(N - i).get(i);
                    }

                }*/
                /*for(int i = 0; i< N/2; i++){
                    //Left side
                    if(returnedList.size()){
                        returnedList.get(i).left = allPossibleFBT(1 + i*2).get(i);
                    }
                    else{
                        returnedList.add(allPossibleFBT(1).get(i));
                        returnedList.get(i).left = allPossibleFBT(1 + i*2).get(i);
                    }
                    //Right side
                    returnedList.get(i).right = allPossibleFBT(1 - i*2).get(i);
                }*/
        return returnedList;
    }


}
