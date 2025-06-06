import javax.swing.JOptionPane;

public class UI {

    public static void showMessageDialog (String display_message) {
        JOptionPane.showMessageDialog(null,display_message);
    }

    public static int showInputIntegerDialog (String display_message) {
        return Integer.parseInt(JOptionPane.showInputDialog(display_message));
    }
}
