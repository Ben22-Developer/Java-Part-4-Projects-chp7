import javax.swing.JOptionPane;

public class UI {

    public static void showMessageDialog (String display) {
        JOptionPane.showMessageDialog(null,display);
    }

    public static int showInputIntegerDialog (String display) {
         return Integer.parseInt(JOptionPane.showInputDialog(display));
    }

    public static String showInputStringDialog (String display) {
        return JOptionPane.showInputDialog(display);
    }
}
