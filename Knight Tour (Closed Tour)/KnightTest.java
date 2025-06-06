import java.util.Scanner;

public class KnightTest {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);
        String cmd_msg = "\nPress 1 to move the knight\nPress 2 to print the chessboard\nPress 3 to change the starting points\nPress 0 to stop the program\nPress: ";

        int start_row, start_clm, user_choice;

        start_row = 1;
        start_clm = 0;

        Knight a_knight = new Knight(start_row,start_clm);

        System.out.print(cmd_msg);
        user_choice = input.nextInt();

        while (user_choice != 0) {

            a_knight.KnightCmd(user_choice);

            System.out.print(cmd_msg);
            user_choice = input.nextInt();
        }


        System.out.print("Thank u fou running our program!");
        a_knight = null;
    }
}
