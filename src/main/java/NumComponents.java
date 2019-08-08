import java.util.HashSet;

public class NumComponents {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static int numComponents(ListNode head, int[] G) {
        //1->2->3->4 and G is [1, 2, 4]
        //G into hash set and re-do
        int result = 0;
        boolean found = false;
        HashSet<Integer> gHash = new HashSet<>();
        for(int i =0; i<G.length;i++){
            gHash.add(G[i]);
        }
        ListNode iterator = head;
        while(iterator != null){
            int value = iterator.val;
            if (gHash.contains(value)) {
                if (!found){
                    result++;
                }
                found = true;
            }
            else{
                found = false;
            }
            iterator = iterator.next;
        }

        return result;
    }
}
