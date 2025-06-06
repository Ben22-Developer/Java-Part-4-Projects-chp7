import java.util.Scanner;
import java.util.Arrays;

public class Array {

    private static int ARRAY_LENGTH = 10;
    private static int[] array = new int[ARRAY_LENGTH];
    private static int array_size = 0;
    private static Scanner input = new Scanner(System.in);


    public static void delete () {
        array = null;
    }

    public static void printArray() {
        if (array_size == 0) {
            System.out.println("Array is empty!");
            return;
        }

        System.out.println("Array elements");
        System.out.println("----------------------");

        for (int i = 0; i < array_size; i++) {
            System.out.println("Element "+(i+1)+" : "+array[i]);
        }
    }

    public static int getArray_size () {
        return array_size;
    }

    public static boolean removeArrayElement (int index) {

        if (index >= array_size)
            return false;

        int i;
        for (i = index; i < array_size - 1; i++) {
            array[i] = array[i+1];
        }

        array[i] = 0;
        array_size --;
        return true;
    }

    private static void sortArray () {
        Arrays.sort(array,0,array_size);
    }

    public static int getIndex (int element) {

        int start, mid, end;

        start = 0;
        end = array_size - 1;
        mid = (start + end)/2;

        while (start <= end) {
            if (array[mid] > element) {
                end = mid - 1;
            }
            else if (array[mid] < element) {
                start = mid + 1;
            }
            else {
                return mid;
            }
            mid = (start + end)/2;
        }

        return -1;
    }

    public static boolean checkIfPresentInArray (int num) {
        for (int i = 0; i < array_size; i++) {
            if (num == array[i])
                return true;
        }
        return false;
    }

    public static void arrayInsert () {

        int elements_to_input, i;

        System.out.print("Enter the number of elements you want to insert: ");
        elements_to_input = input.nextInt();

        i = 0;

        while (i < elements_to_input && array_size < array.length) {

            System.out.print("Enter an element which you have not entered before: ");
            int num = input.nextInt();

            if (array_size != 0) {
                boolean elementIsPresent = checkIfPresentInArray (num);

                if (elementIsPresent) {
                    System.out.println("The element is already present in the array!");
                    continue;
                }
                array[array_size] = num;
            }
            else {
                array[0] = num;
            }

            i++;
            array_size ++;
            sortArray();
        }
    }
}
