import java.util.Scanner;

public class CLI {
    public static void main (String[] args) {

        int i;
        double[] array;
        Scanner inp = new Scanner(System.in);
        int ARRAY_LENGTH = args.length != 0 ? Integer.parseInt(args[0]) : 10;
        System.out.println("Array size --> "+ARRAY_LENGTH);

        array = new double[ARRAY_LENGTH];
        i = 0;
        while (i < array.length) {
            System.out.print("Enter array element: ");
            double num = inp.nextDouble();
            array[i] = num;
            i++;
        }
        double sum = 0;
        for (double num : array)
            sum += num;
        System.out.println("The sum of the array --> "+sum);
        array = null;
    }
}
