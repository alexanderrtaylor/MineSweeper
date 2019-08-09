package Other;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class MorseCode {

    static class MorseHelper {
        byte value;
        byte length;

        MorseHelper(String morseValue){

            this.value = convertMorsetoByte(morseValue);
            this.length = (byte) morseValue.length();
        }

        static List<MorseHelper> createMorseArray(String[] morseValues){
            ArrayList<MorseHelper> helperArray = new ArrayList<>();
            for(String value : morseValues){
                helperArray.add(new MorseHelper(value));
            }
            return helperArray;
        }

        private byte convertMorsetoByte(String value){
            int total = 0;
            for (char morseChar: value.toCharArray()){
                total = (total << 1) + (morseChar == '-' ? 1 : 0);
            }
            return (byte)total;
        }
    }

    static String[] morseKey = {
            ".-",
            "-...",
            "-.-.",
            "-..",
            ".",
            "..-.",
            "--.",
            "....",
            "..",
            ".---",
            "-.-",
            ".-..",
            "--",
            "-.",
            "---",
            ".--.",
            "--.-",
            ".-.",
            "...",
            "-",
            "..-",
            "...-",
            ".--",
            "-..-",
            "-.--",
            "--.."
    };

    static List<MorseHelper> morseKeyBetter = MorseHelper.createMorseArray(morseKey);

    public static int uniqueMorseRepresentations(String[] words) {
        //for each letter in words it will convert into the index for the morseKey array a-a = 0; b-a = 1
        Set <Long> uniqueWords = new HashSet<>();
        //for each word in words
        for (String word : words) {
            //for each char(letter) in word swap
            long wordBuilder = 0;
            int length = 0;
            for (char i: word.toCharArray()) {
                //swap char for string morse value
                int index = i - 'a';
                wordBuilder = (wordBuilder <<  morseKeyBetter.get(index).length) + morseKeyBetter.get(index).value;
                length  +=  morseKeyBetter.get(index).length;
                //wordBuilder.append(morseKey[index]);
                //letter for loop done
            }
            wordBuilder = (wordBuilder <<  8) + length;
            //add transformed word to set if not already there
            uniqueWords.add(wordBuilder);
            //word for is done
        }
        //return count or length of set
        return uniqueWords.size();
    }
}
