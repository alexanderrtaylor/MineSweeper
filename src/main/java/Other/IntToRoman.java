package Other;

public class IntToRoman {

    private static char[] romanSource = {'M','D','C','L','X','V', 'I'};

    public static String intToRoman(int num) {
        //beginning roman numeral I want to parse from
        int currentRoman = 1000;
        int index = 0;
        StringBuilder result = new StringBuilder();

        //Go until I have iterated past 1
        while (currentRoman > 0){

            //Amount of times the current numeral shows up
            int numberOfCurrentRoman = 0;

            //Checking for if the value is at least 1
            if ((num / currentRoman) >= 1){
                numberOfCurrentRoman = num / currentRoman;
                //Adding it to the string
                for (int j = 0; j < numberOfCurrentRoman; j++) {
                    result.append(romanSource[index]);
                }
            }
            //Moving num to the next value by subtracting out the current roman numeral value
            if ((num % currentRoman) >= 0){
                num = num % currentRoman;
            }

            //moving to the next order of numeral. If it was a order of 10 it needs to half
            if (index % 2 == 0){
                //only need to check for 2 orders on 10's
                num = checkOrder2(currentRoman, result, num, index);
                currentRoman = currentRoman/2;
            }
            //if it was an order of 5 it needs to drop to an order of 10
            else{
                //only need to check for 1 order less on 5's
                num = checkOrder1(currentRoman, result, num, index);
                currentRoman = currentRoman/5;
            }

            index++;
        }
        return result.toString();
    }
    private static int checkOrder2(int romanNumeral, StringBuilder string, int number, int index) {
        if (romanNumeral / 10 != 0) {
            //checking for things like 9's
            if ((romanNumeral - (romanNumeral / 10)) <= number) {
                string.append(romanSource[index + 2]);
                string.append(romanSource[index]);
                return number % (romanNumeral / 10);
            }
        }
        return number;
    }
    private static int checkOrder1(int romanNumeral, StringBuilder string, int number, int index){
        if(romanNumeral / 5 != 0){
            //checking for things like 4's
            if ((romanNumeral - (romanNumeral / 5)) <= number) {
                string.append(romanSource[index + 1]);
                string.append(romanSource[index]);
                return number % (romanNumeral / 5);
            }
        }
        return number;
    }
}