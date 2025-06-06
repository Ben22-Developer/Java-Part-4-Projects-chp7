import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        LinkedList head, temp, new_node;
        int user_press, data;

        head = null;
        temp = null;

        System.out.print("Enter 1 to continue or 0 to stop: ");
        user_press = input.nextInt();

        while (user_press != 0) {
            System.out.print("Enter linked list data: ");
            data = input.nextInt();

            new_node = new LinkedList(data);

            if (head == null) {
                head = new_node;
                temp = new_node;
            }
            else {
                temp.setNext(new_node);
                temp = new_node;
            }

            System.out.print("Enter 1 to continue or 0 to stop: ");
            user_press = input.nextInt();
        }

        System.out.println("The List\n--------------------\n");

        temp = head;

        while (temp != null) {
            if (temp.getNext() != null) {
                System.out.print(temp.getData() + " --> ");
            }
            else {
                System.out.print(temp.getData());
            }

            temp = temp.getNext();
        }

        head = null;
        new_node = null;

    }
}
