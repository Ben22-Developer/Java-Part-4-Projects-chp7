import java.util.Scanner;
import java.util.ArrayList;

public class Fibonacci {



    private void fibonacci (int range) {

        int i, next_index_val;
        ArrayList<Integer> fibonacci_list = new ArrayList<>();

        fibonacci_list.add(0);
        fibonacci_list.add(1);
        i = 2;

        while (i < range) {

            next_index_val = fibonacci_list.get(i - 2) + fibonacci_list.get(i - 1);
            fibonacci_list.add(next_index_val);
            i++;
        }

        printList (fibonacci_list);

        fibonacci_list = null;
    }

    public static void printList (ArrayList<Integer> list) {

        String msg_to_display = "";

        msg_to_display += "[ ";

        for (int i = 0; i < list.size(); i++) {

            if (i == list.size() - 1) {
                msg_to_display += list.get(i) + " ]";
                continue;
            }

            msg_to_display += list.get(i) +", ";
        }

        UI.showMessageDialog(msg_to_display);
    }

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);
        String msg = "Press 1 to enter the range of fibonacci values to be printed (range has to be > 2)\nPress 0 to stop the app";
        int user_press = UI.showInputIntegerDialog(msg);
        Fibonacci own_class_obj = new Fibonacci();

        while (user_press != 0) {

            switch (user_press) {
                case 1 -> {
                    int range = UI.showInputIntegerDialog("Enter the fibonacci values range ( range > 2) ");
                    if (range > 2) {
                        own_class_obj.fibonacci(range);
                    }
                    else {
                        UI.showMessageDialog("Fibonacci value range has to be > 2");
                    }
                }

                default -> {
                    UI.showMessageDialog("Wrong press!");
                }

            }

            user_press = UI.showInputIntegerDialog(msg);
        }

        UI.showMessageDialog("Thank u for using our app");
    }
}
