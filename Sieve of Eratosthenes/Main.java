import java.util.Scanner;

public class Main {
    public static void main (String[] args) {

        int default_prime_number_range = 10;
        int user_press;

        String cmd = "\n\nPress 1 to get the prime numbers, default range: "+default_prime_number_range+"\nPress 2 to just print the array of numbers\nPress 3 to customize the prime number array range\nPress 0 to end the program\nPress: ";

        Scanner input = new Scanner(System.in);

        PrimeNumbers obj = new PrimeNumbers(default_prime_number_range);

        System.out.print(cmd);
        user_press = input.nextInt();

        while (user_press != 0) {
            System.out.println();
            obj.PrimeNumbersCmd(user_press);
            System.out.print(cmd);
            user_press = input.nextInt();
        }

        obj = null;
        System.out.println("Thank u for running our code");
    }
}
