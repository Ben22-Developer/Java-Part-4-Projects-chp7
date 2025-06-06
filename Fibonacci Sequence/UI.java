import javax.swing.JOptionPane;

public class UI {

    public static void showMessageDialog (String display_msg) {
        JOptionPane.showMessageDialog(null,display_msg);
    }

    public static int showInputIntegerDialog (String display_msg) {
        return Integer.parseInt(JOptionPane.showInputDialog(display_msg));
    }
}
