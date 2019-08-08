import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    private static Map<Character, Integer> romanSource = new HashMap()
    {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};


    public static int romanToInt(String s) {
        int result = 0;
        if (s == null){
            throw new IllegalArgumentException("String cannot be null");
        }

        Integer previousValue = 0;
        for(int index = s.length()-1; index >= 0; index--){
            Integer current = romanSource.get(s.charAt(index));
            //do the loop without this if
            if (current == null){
                throw new IllegalArgumentException("Invalid character in String");
            }
            else if (current < previousValue) {
                result -= current;
            }
            else{
                result += current;
            }
            previousValue = current;
        }
//        if (s.length() != 0) {
//            result += romanSource.get(s.charAt(s.length() - 1));
//        }
        /*for(int index = 0; index < s.length() - 1; index++){
            char current = s.charAt(index);
            //do the loop without this if
            char next = s.charAt(index + 1);
            if (romanSource.get(next) == null){
                throw new IllegalArgumentException("Invalid character in String");
            }
            else if (romanSource.get(current) < romanSource.get(next)) {
                result -= romanSource.get(current);
            }
            else{
                result += romanSource.get(current);
            }
        }
        if (s.length() != 0) {
            result += romanSource.get(s.charAt(s.length() - 1));
        }*/
        return result;
    }
}
