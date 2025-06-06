import java.security.SecureRandom;

public class QuestionE {

    private static double array[];
    private static int ARRAY_LENGTH = 99;

    public static void initializeArray () {
        array = new double[ARRAY_LENGTH];
        SecureRandom randomize = new SecureRandom();

        for (int i = 0; i < array.length; i++) {
            double randomVal = randomize.nextDouble(50);
            array[i] = randomVal;
        }
    }

    public static void print () {
        System.out.println("Array elements");
        for (double elt : array)
            System.out.printf("%.2f, ",elt);
    }

    public static double getSmallestElement () {
        double smallest = array[0];

        for (double elt : array) {
            if (smallest > elt) {
                smallest = elt;
            }
        }

        return smallest;
    }

}
