import java.util.Arrays;

public class QuestionB {

    private static int array[];
    private static int ARRAY_LENGTH = 5;

    public static void setLength () {
        array = new int[ARRAY_LENGTH];
    }

    public static void initializeArray () {
        Arrays.fill(array,8);
    }

    public static  void printArray () {

        System.out.println("All array elements");

        for (int i = 0; i <= array.length; i++) {

            try {
                System.out.print(array[i]+" ");
            }
            catch (IndexOutOfBoundsException exc) {
                System.out.println();
                System.out.println("Index "+i+" is out of array bounds!");
                System.out.println();
            }

        }
    }
}
