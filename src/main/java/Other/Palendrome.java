package Other;

public class Palendrome {
    public static boolean isPalindrome(String s) {

        if (s == null){
            throw new IllegalArgumentException("String cannot be null");
        }
        int left_index = 0;
        //s = s.toLowerCase().replaceAll("[^a-z0-9]", "");
        int right_index = s.length() - 1;
        //char[] stringArray = s.toCharArray();

        while (left_index < right_index){
            final char rightSide = Character.toLowerCase(s.charAt(right_index));
            final char leftSide = Character.toLowerCase(s.charAt(right_index));
            if (isNotAlphanumeric(leftSide)){
                left_index++;
            }
            else if(isNotAlphanumeric(rightSide)){
                right_index--;
            }
            else{
                if(rightSide != leftSide){
                    return false;
                }
                right_index--;
                left_index++;
            }
        }
        return true;
    }

    private static boolean isNotAlphanumeric(char a){

        if (97 <= a && a <= 122){
            return false;
        }
        if (48 <= a && a <= 57){
            return false;
        }
        return true;
    }
}
