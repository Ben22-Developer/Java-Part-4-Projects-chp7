public class KnightTourTest {
    public static void main (String[] args) {
        Knight the_knight = new Knight();
        String display_msg = "Press 1 to move the knight\nPress 2 to view the knight's current position\nPress 3 to get the counter's value\nPress 4 to print the chess board\nPress 5 to reset the chess board\nPress 0 to stop the app";
        int user_choice = UI.showInputIntegerDialog(display_msg);

        while (user_choice != 0) {
            the_knight.knightCommands(user_choice);
            user_choice = UI.showInputIntegerDialog(display_msg);
        }

        the_knight = null;
        UI.showMessageDialog("Thank u for using our app be back soon!");
    }
}
