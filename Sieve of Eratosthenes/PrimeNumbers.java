import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumbers {

    private boolean[] isPrime;
    private int ARRAY_LENGTH;

    public PrimeNumbers (int range) {
        this.ARRAY_LENGTH = range;
        initializeIsPrimeArray();
    }

    private void initializeIsPrimeArray () {

        if (isPrime != null) {
            isPrime = null;
        }
        isPrime = new boolean[ARRAY_LENGTH];

        for (int i = 2; i < isPrime.length; i++) {
         isPrime[i] = true;
        }
    }

    private void getPrimeNumbers () {

        calculatePrimeNumbers();
        printResults();
    }

    private void calculatePrimeNumbers () {

        for (int i = 2; i < isPrime.length; i++) {
            int num = i;
            int k = 2;
            int multiple = num * k;

            while (multiple < isPrime.length) {
                isPrime[multiple] = false;
                k ++;
                multiple = k * num;
            }
        }
    }

    private void printResults () {

        ArrayList<Integer> prime_numbers = new ArrayList<>();
        ArrayList<Integer> non_prime_numbers = new ArrayList<>();

        for (int i = 2; i < isPrime.length; i++) {

            if (isPrime[i]) {
                prime_numbers.add(i);
            }
            else {
                non_prime_numbers.add(i);
            }
        }

        System.out.println("Prime numbers\n----------------------------");
        printNumbers(prime_numbers);
        System.out.println("\nNon Prime numbers\n----------------------------");
        printNumbers(non_prime_numbers);
    }

    private void printNumbers (ArrayList<Integer> numbers) {

        for (int i = 0; i < numbers.size(); i++) {

            if (i != numbers.size() - 1) {
                System.out.print(numbers.get(i)+", ");
            }
            else {
                System.out.print(numbers.get(i));
            }
        }
    }

    private void reconstructIsPrimeArray () {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the array length (array length > 2)");
        System.out.print("Enter: ");
        int arr_length = input.nextInt();

        while (arr_length <= 2) {
            System.out.println("Enter the array length (array length > 2) or press -1 to return");
            System.out.print("Enter: ");
            arr_length = input.nextInt();

            if (arr_length == -1)
                return;
        }

        ARRAY_LENGTH = arr_length;
        initializeIsPrimeArray();
        System.out.println("Array is set to default.");
    }

    private void printArray () {

        System.out.println("NB: It will print if number is prime or not if you have run the first command (press)\n");

        for (int i = 2; i < isPrime.length; i++) {
            System.out.println(i+": is a prime number ? "+isPrime[i]);
        }
    }

    public void PrimeNumbersCmd (int cmd) {

        switch (cmd) {
            case 1 -> getPrimeNumbers();
            case 2 -> printArray();
            case 3 -> reconstructIsPrimeArray();
            default -> System.out.println("Wrong press!");
        }
    }

}
