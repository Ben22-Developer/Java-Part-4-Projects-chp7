public class Main {

    public static void main (String[] a) {

        NextQueen queen = new NextQueen();
        String display = "Press 1 to place queen on the board\nPress 2 to print the chess board\nPress 0 to end the program";

        int user_choice = UI.showInputIntegerDialog(display);

        while (user_choice != 0) {
            queen.nextQueenCommands(user_choice);
            user_choice = UI.showInputIntegerDialog(display);
        }

        queen.delete();
        queen = null;
        UI.showMessageDialog("Thank you for using our app");
    }
}
 