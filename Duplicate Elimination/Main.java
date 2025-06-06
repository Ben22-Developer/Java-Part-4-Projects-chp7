import java.util.Scanner;

public class Main {

    public static void main (String[] args) {

        Scanner input = new Scanner(System.in);

        int index,elt,user_choice;

        System.out.println("For this program u can't enter integer duplicates in an array of 'int'!");

        user_choice = 1;

        String msgToDisplay = "\nPress 1 to insert an element in the array\nPress 2 to get an element's index\nPress 3 to remove an element if present in the array\nPress 4 to print the array\nPress 5 to get the size of the array\nPress 0 to end the program\n";

        System.out.println(msgToDisplay);
        System.out.print("Press: ");
        user_choice = input.nextInt();


        while (user_choice != 0) {

            switch (user_choice) {

                case 1:
                    Array.arrayInsert();
                break;
                case 2:
                    System.out.print("Enter the element: ");
                    elt = input.nextInt();
                    index = Array.getIndex(elt);
                    System.out.println(index < 0 ? "Element not fount" : "Element found at index: "+index);
                break;
                case 3:
                    System.out.print("Enter the index: ");
                    index = input.nextInt();
                    boolean isDelete = Array.removeArrayElement(index);
                    System.out.println(isDelete ? "Element at index "+index+" is successfully deleted" : "The index u inserted is null");
                break;
                case 4:
                    Array.printArray();
                break;
                case 5:
                    System.out.println("Array size: "+Array.getArray_size());
                break;
                default:
                    System.out.println("Wrong unexpected press");
            }
            System.out.println("\n"+msgToDisplay);
            System.out.print("Press: ");
            user_choice = input.nextInt();
        }

        Array.delete();
    }
}
