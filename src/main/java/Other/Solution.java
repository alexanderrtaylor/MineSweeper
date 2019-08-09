package Other;

import java.util.Collections;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public static int numJewelsInStones(String J, String S) {
        int count=0;
        if (J == null||S == null){
            return 0;
        }
        Set<Character> jHash = new HashSet<Character>();
        for (char i: J.toCharArray()
             ) {
            jHash.add(i);
        }
        //((HashSet) jHash).addAll(Arrays.asList(J.toCharArray()));

        for (char i: S.toCharArray()){
            if (jHash.contains(i)){
                count++;
            }
        }
        /*for(int i=0; i<S.length(); i++){
            for(int j=0; j<J.length(); j++){
                if (J.toCharArray()[j] == S.toCharArray()[i]){// need to find a better way to index this string
                    count++;
                    continue;
                }
            }
        }*/
        return count;
    }
}