public class Main {

    public static void main(String[] args) {

        String welcome_msg, error_msg, guide_msg;
        int user_press;

        welcome_msg = "Welcome to the Polling\nRead and follow instructions to get your best results";
        guide_msg = "Press 1 to start the survey\nPress 0 to end the program";
        error_msg = "Wrong press";

        UI.showMessageDialog(welcome_msg);

        user_press = UI.inputIntegerMessageDialog(guide_msg);

        while (user_press != 0) {

            switch (user_press) {
                case 1:
                    Polling.setup();
                    Polling.survey_Controller();
                break;
                default:
                UI.showMessageDialog(error_msg);
            }

            user_press = UI.inputIntegerMessageDialog(guide_msg);
        }
    }
}
