import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class DrawCircularArc extends JPanel {

    private static int start_down_arc_x,start_down_arc_y,arc1_width,arc1_height;
    private static int start_up_arc_x,start_up_arc_y,arc2_width,arc2_height;

    private void drawUpArc (Graphics g) {

        g.drawArc(start_up_arc_x - arc2_width, start_up_arc_y - 7, arc2_width, arc2_height,180,180);

        arc1_width += 40;
        arc1_height += 40;

        start_down_arc_x = start_up_arc_x - arc2_width;
        start_down_arc_y = start_up_arc_y - 20;
    }


    private void drawDownArc (Graphics g) {

        g.drawArc(start_down_arc_x, start_down_arc_y, arc1_width, arc1_height,0,180);

        start_up_arc_x = start_down_arc_x + arc1_width;
        start_up_arc_y = start_down_arc_y;

        arc2_width = arc1_width + 20;
        arc2_height = arc1_height + 20;
    }


    public void drawArc (Graphics g) {

        int screen_width, screen_height, iterate, total_iterate;

        screen_width = getWidth();
        screen_height = getHeight();
        setBackground(Color.BLACK);

        g.setColor(Color.BLUE);
        start_down_arc_x = screen_width/2;
        start_down_arc_y = screen_height/2;
        arc1_width = 30;
        arc1_height = 30;


        iterate = 0;
        total_iterate = 13;

        while (iterate < total_iterate) {

            drawDownArc(g);
            drawUpArc(g);
            iterate ++;
        }

    }

    public void paintComponent (Graphics g) {

        super.paintComponent(g);
        drawArc(g);
    }
}
