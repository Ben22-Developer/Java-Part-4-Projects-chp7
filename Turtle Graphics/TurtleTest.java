public class TurtleTest {
    public static void main (String[] args) {

        int user_choice = 1;
        Turtle tortue = new Turtle();
        UI.showMessageDialogs("Welcome to our application of torture Graphics view\n              🐢🐢🐢🐢🐢🐢🐢🐢🐢🐢");

        String display_message = "Press 1 to put the turtle's pen up\nPress 2 to put the turtle's pen down\nPress 3 to turn the turtle to the right side\nPress 4 to turn the turtle to the left side\nPress 5 to move the turtle forwards\nPress 6 to display the full torture's path\nPress 7 and get the turtle pen position\nPress 8 and get the turtle facing direction\nPress 9 to reset the torture's path back and turtle's floor back to initial\nPress 0 to end the app";
        user_choice = UI.showInputDialogs(display_message);

        while (user_choice != 0) {
            tortue.turtleCommands(user_choice);
            user_choice = UI.showInputDialogs(display_message);
        }

        UI.showMessageDialogs("              🐢🐢🐢🐢🐢🐢🐢🐢🐢🐢\nThank you for using our app be back soon!");;
        tortue = null;
    }
}
