import javax.swing.plaf.synth.SynthTextAreaUI;
import java.security.SecureRandom;

public class RandomRoll {

    private static int ARRAY_LENGTH = 13;
    private static int[] random_array_occurrence;

    public static void initializeArray () {
        random_array_occurrence = new int[ARRAY_LENGTH];
    }

    private static int getRolledIndex () {
        SecureRandom randomize = new SecureRandom();
        int roll1 = 1 + randomize.nextInt(6);
        int roll2 = 1 + randomize.nextInt(6);
        int sum = roll1 + roll2;
        return sum;
    }

    public static void randomRolls () {
        int total_rolls = 36000000;

        for (int i = 0; i < total_rolls; i++) {
            int rolled_index = getRolledIndex();
            random_array_occurrence[rolled_index] ++;
        }
    }

    public static void printArray () {

        System.out.println("The times each roll has occurred");
        System.out.println("---------------------------------------");

        for (int i = 2; i < random_array_occurrence.length; i++) {
            if (random_array_occurrence[i] < random_array_occurrence[6])
                System.out.println("Index "+i+" Occurred: "+random_array_occurrence[i]+" times");
            else if (random_array_occurrence[i] > random_array_occurrence[6])
                System.out.println("...");
            else
                System.out.println("Index "+i+" Occurred: "+random_array_occurrence[i]+" times --> @@ most frequent");
        }
    }

    public static void delete () {
        random_array_occurrence = null;
    }
}
