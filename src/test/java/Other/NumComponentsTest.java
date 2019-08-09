package Other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumComponentsTest {

    private NumComponents.ListNode createLinkedList(int[] testList){
        NumComponents.ListNode head = null;
        NumComponents.ListNode previous = null;
        for(int i = 0; i < testList.length; i++){
            NumComponents.ListNode current = new NumComponents.ListNode(testList[i]);
            if (i == 0){
                head = current;
            }
            if (previous != null) {
                previous.next = current;
            }
            previous = current;
        }

        return head;
    }
    @Test
    void numComponentsTest() {
        NumComponents.ListNode testList = createLinkedList(new int[]{0,1,2,3,4});
        int[] testArray = {0,3,1,4};
        Assertions.assertEquals(2, NumComponents.numComponents(testList, testArray));
    }
    @Test
    void numComponentsTestSimple() {
        NumComponents.ListNode testList = createLinkedList(new int[]{0,1,2,3});
        int[] testArray = {0,1,3};
        Assertions.assertEquals(2, NumComponents.numComponents(testList, testArray));
    }
    @Test
    void numComponentsTestSimple3() {
        NumComponents.ListNode testList = createLinkedList(new int[]{0,1,2});
        int[] testArray = {0,2};
        Assertions.assertEquals(2, NumComponents.numComponents(testList, testArray));
    }
    @Test
    void numComponentsTestSimple2() {
        NumComponents.ListNode testList = createLinkedList(new int[]{0,1,2});
        int[] testArray = {2,1,0};
        Assertions.assertEquals(1, NumComponents.numComponents(testList, testArray));
    }
    @Test
    void numComponentsTestComplex() {
        NumComponents.ListNode testList = createLinkedList(new int[]{1,2,0,4,3});
        int[] testArray = {3,4,0,2,1};
        Assertions.assertEquals(1, NumComponents.numComponents(testList, testArray));
    }
}