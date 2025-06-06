import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class DrawCircularLinesTest {

    public static void main (String[] args) {

        int draw = Integer.parseInt(JOptionPane.showInputDialog("Welcome the adventurer\nPress 1 to draw circular squares\nPress 2 to draw circular arcs"));

        DrawCircularLine canvas;
        DrawCircularArc canvas2;

        JFrame window = new JFrame();

        switch (draw) {
            case 1:
                canvas = new DrawCircularLine();
                window.add(canvas);
            break;
            case 2:
                canvas2 = new DrawCircularArc();
                window.add(canvas2);
            break;
            default:
            JOptionPane.showMessageDialog(null,"You have pressed a wrong answer please re-load the application!");
            return;
        }

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(700,700);
        window.setVisible(true);

    }
}
