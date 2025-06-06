import java.security.SecureRandom;
import java.util.Arrays;

public class QuestionC {

    private static double array[];
    private static int ARRAY_LENGTH = 100;

    public static void setLength () {
        array = new double[ARRAY_LENGTH];
    }

    public static void initializeArray () {
        SecureRandom randomize = new SecureRandom();
        for (int i = 0; i < array.length; i++) {
            double random = 1 + randomize.nextDouble(50.5);
            array[i] = random;
        }
    }

    public static void printArray () {
        System.out.println("All array elements");
        for (double elt: array)
            System.out.printf("%.2f, ",elt);
    }

    public static double getTotalOfArray () {
        double total = 0;
        for (int i = 0; i < array.length; i++) {
            total += array[i];
        }
        return total;
    }

}
