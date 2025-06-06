import javax.swing.JOptionPane;

public class UI {

    public static void showMessageDialogs (String display_message) {
        JOptionPane.showMessageDialog(null,display_message);
    }

    public static int showInputDialogs (String display_message) {
        return Integer.parseInt(JOptionPane.showInputDialog(display_message));
    }
}
