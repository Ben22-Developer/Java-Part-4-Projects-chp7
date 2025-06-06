import javax.swing.JOptionPane;

public class UI {

    public static void showMessageDialog (String display) {
        JOptionPane.showMessageDialog(null,display);
    }

    public static int inputIntegerMessageDialog (String display) {
        return Integer.parseInt(JOptionPane.showInputDialog(display));
    }

    public static String inputStringMessageDialog (String display) {
        return JOptionPane.showInputDialog(display);
    }
}
