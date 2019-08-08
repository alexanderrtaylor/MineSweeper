
import java.util.ArrayList;
import java.util.List;

public class SquaresSorted {
    public static int[] sortedSquares(int[] A) {
        //Break array into 2 lists one for positive and one for negative
        //Do a for loop to iterate over array until >=0
        int index = indexForNonNegative(A);
        //List<Integer> negativeNumbers = returnSquaredNegativeArray(index, A);
        //List<Integer> positiveNumbers = returnSquaredPositiveArray(index, A);
        //List<Integer> totalList = new ArrayList<Integer>();
        //Combine arrays back together
        int positiveIndex = index;
        int[] totalListArray = new int[A.length];
        int negativeIndex = index - 1;
        index = 0;

        //-3, -2, -1, 4, 5, 6
        //1, 4, 9, 16
        while(index < A.length) {
            //check against last value of negative and if the positive is greater than or equal to and insert before that negative array value
            //if negative index is out of bounds
            if (negativeIndex < 0) {
                //only positive branch
                totalListArray[index] = A[positiveIndex] * A[positiveIndex];
                positiveIndex++;
            }
            //positive index out of bounds
            else if(positiveIndex >= A.length || A[positiveIndex] > -A[negativeIndex]){
                //only negative branch
                totalListArray[index] = A[negativeIndex] * A[negativeIndex];
                negativeIndex--;
            }
            else{
                totalListArray[index] = A[positiveIndex] * A[positiveIndex];
                positiveIndex++;
            }
            index++;
        }

       /* while(positiveIndex < positiveNumbers.size() || negativeIndex >= 0) {
            //check against last value of negative and if the positive is greater than or equal to and insert before that negative array value
            //if negative index is out of bounds
            if (negativeIndex < 0) {
                //only positive branch
                totalList.add(positiveNumbers.get(positiveIndex));
                positiveIndex++;
            }
            //positive index out of bounds
            else if(positiveIndex >= positiveNumbers.size() || positiveNumbers.get(positiveIndex) > negativeNumbers.get(negativeIndex)){
                //only negative branch
                totalList.add(negativeNumbers.get(negativeIndex));
                negativeIndex--;
            }
            else{
                totalList.add(positiveNumbers.get(positiveIndex));
                positiveIndex++;
            }
        }
        int[] totalListArray = new int[totalList.size()];
        for (int i =0; i < totalList.size(); i++){
            totalListArray[i] = totalList.get(i);
        }*/
        //For over the positive
        /*index--;
        for(int i = 0; i < positiveNumbers.size(); i++) {
            //[-1, 2, 4]
            //check against last value of negative and if the positive is greater than or equal to and insert before that negative array value
            if (positiveNumbers.get(i) >= negativeNumbers.get(index)) {
                positiveNumbers.add(i, negativeNumbers.get(index));
                if (index > 0)
                {
                    index--;
                }
                else{
                    break;
                }
            }
        }
        int[] totalListArray = new int[positiveNumbers.size()];
        for (int i =0; i < positiveNumbers.size(); i++){
            totalListArray[i] = positiveNumbers.get(i);
        }*/
        return  totalListArray;
    }

    private static int indexForNonNegative(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                return i;
            }
        }
        return A.length;
    }

    private static List returnSquaredNegativeArray(int index, int[] A) {
        List<Integer> negativeNumbers = new ArrayList<Integer>();
        for(int i = 0; i < index; i++){
            //Square negative array
            negativeNumbers.add(A[i] * A[i]);
        }
        return negativeNumbers;

    }
    private static List returnSquaredPositiveArray(int index, int[] A) {
        List<Integer> positiveNumbers = new ArrayList<Integer>();
        for(int i = index; i < A.length; i++){
            //Square Positive array
            positiveNumbers.add(A[i] * A[i]);
        }
        return positiveNumbers;
    }
}
