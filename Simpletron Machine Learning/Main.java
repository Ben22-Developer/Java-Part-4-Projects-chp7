import java.sql.Array;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Integer> user_commands = new ArrayList<>();

    private static void filterUserCommands (String user_commands_str) {

        if (!user_commands.isEmpty()) {
            user_commands.clear();
        }

        int user_command_nbr;

        for (String user_command : user_commands_str.split(",",0)) {
            user_command_nbr = Integer.parseInt(user_command);
            user_commands.add(user_command_nbr);
        }
    }

    private static String collectUserCommands () {
        return UI.showInputStringDialog("Enter the commands below\nExample: 1009,1008,1108,4300\nLast 2 digits are the memory index and first 2 digits are the operation to be accomplished\nType 10 to read\nType 11 to write \nType 20 to load to the accumulator\nType 21 to store from the accumulator\nType 30 for addition\nType 31 for subtraction\nType 32 for division\nType 33 for multiplication\nType 40 to branch to a specific location\nType 41 to branch to a specific location if accumulator is negative\nType 42 to branch to a specific location if the accumulator is zero\nType 43 to end up the program (optional) \nPress 'Enter' to exit this command option program\n");
    }

    public static void maini (String[] args) {

        String displaying_msg, error_msg;
        int user_press;
        Instructions user_instructions;

        user_instructions = new Instructions();

        displaying_msg = "* Welcome to Simpletron! *\n" + "* Please enter your program one instruction *\n" + "* (or data word) at a time. I will display *\n" + "* the location number and a question mark (?). *\n" + "* You then type the word for that location. *\n" + "* Type -99999 to stop entering your program. *\nPress 1 to continue \nPress 0 to stop";
        error_msg = "Wrong press!";

        user_press = UI.showInputIntegerDialog(displaying_msg);

        while (user_press != 0) {

            switch (user_press) {
                case 1:
                    user_instructions.setup();
                    user_instructions.takeUserInput();
                    user_instructions.executeUserCommands();
                break;
                default:
                    UI.showMessageDialog(error_msg);
            }
            user_press = UI.showInputIntegerDialog(displaying_msg);
        }

        user_instructions = null;
    }
}
//1050,1051,1052,1053,1054,1055,1056,1057,2050,2190,2051,2191,3190,4116,2091,4017,2090,2190,2052,2191,3190,4124,2091,4025,2090,2190,2053,2191,3190,4132,2091,4033,2090,2190,2054,2191,3190,4140,2091,4041,2090,2190,2055,2191,3190,4148,2091,4049,2090,2190,1190
//1050,1051,1052,1053,1054,1055,2050,2190,2051,2191,3190,4114,2091,4015,2090,2190,2052,2191,3190,4122,2091,4023,2090,2190,   1190,4400
