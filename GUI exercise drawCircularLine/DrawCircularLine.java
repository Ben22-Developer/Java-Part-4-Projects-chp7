import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JPanel;

public class DrawCircularLine extends JPanel {

    private static int start_down_line_x,start_down_line_y,end_down_line_x,end_down_line_y;
    private static int start_left_line_x,start_left_line_y,end_left_line_x,end_left_line_y;
    private static int start_up_line_x,start_up_line_y,end_up_line_x,end_up_line_y;
    private static int start_right_line_x,start_right_line_y,end_right_line_x,end_right_line_y;
    private static int range;

    private void drawRightwardsLine (Graphics g) {

        g.drawLine(start_right_line_x, start_right_line_y, end_right_line_x, end_right_line_y);

        start_down_line_x = end_right_line_x;
        start_down_line_y = end_right_line_y;
        end_down_line_x = start_down_line_x;
        end_down_line_y = start_down_line_y + (range + 10);
    }


    private void drawUpwardsLine (Graphics g) {

        g.drawLine(start_up_line_x, start_up_line_y, end_up_line_x, end_up_line_y);

        start_right_line_x = end_up_line_x;
        start_right_line_y = end_up_line_y;
        end_right_line_x = start_right_line_x + range;
        end_right_line_y = start_right_line_y;
    }



    private void drawLeftwardsLine(Graphics g) {

        g.drawLine(start_left_line_x, start_left_line_y, end_left_line_x, end_left_line_y);

        range += 20;

        start_up_line_x = end_left_line_x;
        start_up_line_y = end_left_line_y;
        end_up_line_x = start_up_line_x;
        end_up_line_y = start_up_line_y - range;
    }


    private void drawDownwardsLine (Graphics g) {

        g.drawLine(start_down_line_x, start_down_line_y, end_down_line_x, end_down_line_y);

        start_left_line_x = end_down_line_x;
        start_left_line_y = end_down_line_y;
        end_left_line_x = start_left_line_x - (range + 10);
        end_left_line_y = start_left_line_y;
    }

    private void drawCircularSquareLines (Graphics g) {

        int screen_width, screen_height;
        int iteration, total_iteration;

        screen_width = getWidth();
        screen_height = getHeight();

        setBackground(Color.BLACK);
        g.setColor(Color.GREEN);

        range = 20;
        start_down_line_x = screen_width/2;
        start_down_line_y = screen_height/2;
        end_down_line_x = start_down_line_x;
        end_down_line_y = start_down_line_y + range;


        iteration = 0;
        total_iteration = 30;

        while (iteration < total_iteration) {

            drawDownwardsLine(g);
            drawLeftwardsLine(g);
            drawUpwardsLine(g);
            drawRightwardsLine(g);

            iteration ++;
        }
    }

    public void paintComponent (Graphics g) {

        super.paintComponent(g);

        drawCircularSquareLines(g);
    }
}
