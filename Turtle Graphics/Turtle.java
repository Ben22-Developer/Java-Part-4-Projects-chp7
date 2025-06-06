import java.util.Arrays;

public class Turtle {

    private int[][] turtle_floor;
    private final int turtle_floor_rows = 20;
    private final int turtle_floor_cls = 20;

    private int[] curr_turtle_cell = {0,0};

    private enum DIRECTION {NORTH, EAST, SOUTH, WEST};
    DIRECTION curr_turtle_direction;

    private enum PEN_POSITION {UP, DOWN};
    PEN_POSITION curr_turtle_pen_position;

    public Turtle () {
        this.curr_turtle_direction = DIRECTION.NORTH;
        this.curr_turtle_pen_position = PEN_POSITION.UP;
        makeTurtleFloorArea();
    }

    private void makeTurtleFloorArea() {
        turtle_floor = new int[turtle_floor_rows][turtle_floor_cls];
    }

    private void resetPath () {
        for (int i = 0; i < turtle_floor.length; i++) {
            Arrays.fill(turtle_floor[i],0);
        }
        curr_turtle_cell[0] = 0;
        curr_turtle_cell[1] = 0;
        curr_turtle_direction = DIRECTION.NORTH;
        UI.showMessageDialogs("All the turtle's paths are back to initial");
    }

    private void turtlePenUp () {
        this.curr_turtle_pen_position = PEN_POSITION.UP;
        UI.showMessageDialogs("Currently the turtle's pen is up");
    }

    private void turtlePenDown () {
        this.curr_turtle_pen_position = PEN_POSITION.DOWN;
        UI.showMessageDialogs("Currently the turtle's pen is down");
    }

    private PEN_POSITION getCurr_turtle_pen_position () {
        return curr_turtle_pen_position;
    }

    private DIRECTION getCurr_turtle_direction () {
        return curr_turtle_direction;
    }

    private void turtleTurningRight () {
        switch (this.curr_turtle_direction) {
            case DIRECTION.NORTH:
                this.curr_turtle_direction = DIRECTION.EAST;
                UI.showMessageDialogs("Torture is now facing in east direction");
            break;
            case DIRECTION.EAST:
                this.curr_turtle_direction = DIRECTION.SOUTH;
                UI.showMessageDialogs("Torture is now facing in south direction");
            break;
            case DIRECTION.SOUTH:
                this.curr_turtle_direction = DIRECTION.WEST;
                UI.showMessageDialogs("Torture is now facing in west direction");
            break;
            case DIRECTION.WEST:
                this.curr_turtle_direction = DIRECTION.NORTH;
                UI.showMessageDialogs("Torture is now facing in north direction");
            break;
        }
    }

    private void turtleTurningLeft () {
        switch (this.curr_turtle_direction) {
            case DIRECTION.NORTH:
                this.curr_turtle_direction = DIRECTION.WEST;
                UI.showMessageDialogs("Torture is now facing in west direction");
            break;
            case DIRECTION.WEST:
                this.curr_turtle_direction = DIRECTION.SOUTH;
                UI.showMessageDialogs("Torture is now facing in south direction");
            break;
            case DIRECTION.SOUTH:
                this.curr_turtle_direction = DIRECTION.EAST;
                UI.showMessageDialogs("Torture is now facing in east direction");
            break;
            case DIRECTION.EAST:
                this.curr_turtle_direction = DIRECTION.NORTH;
                UI.showMessageDialogs("Torture is now facing in north direction");
            break;
        }
    }

    private void changeTurtleFacingDirection (int turn_turtle) {

        switch (turn_turtle) {
            case 3 -> turtleTurningRight();
            case 4 -> turtleTurningLeft();
        }
    }

    private void showCurrentPosition () {
        String curr_position = "Torture is now in cell: ";
        curr_position += "["+curr_turtle_cell[0]+"][" + curr_turtle_cell[1]+"]";
        UI.showMessageDialogs(curr_position);
    }

    private void updateTortureFloor (int row, int clm) {
        if (curr_turtle_pen_position == PEN_POSITION.DOWN) {
            turtle_floor[row][clm] = 1;
        }
        if (curr_turtle_pen_position == PEN_POSITION.UP) {
            turtle_floor[row][clm] = 0;
        }
    }

    private void moveForwardInNorthDirection (int steps_to_move) {
        int i = (steps_to_move == 1) ? 0 : 1;
        while (i < steps_to_move && curr_turtle_cell[0] < (turtle_floor_rows - 1)) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
            curr_turtle_cell[0] ++;
            i ++;
        }

        if (curr_turtle_cell[0] == 19) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
        }

        showCurrentPosition ();
    }

    private void moveForwardInEastDirection (int steps_to_move) {
        int i = (steps_to_move == 1) ? 0 : 1;
        while (i < steps_to_move && curr_turtle_cell[1] < (turtle_floor_cls - 1)) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
            curr_turtle_cell[1] ++;
            i ++;
        }

        if (curr_turtle_cell[1] == 19) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
        }

        showCurrentPosition ();
    }

    private void moveForwardInSouthDirection (int steps_to_move) {

        int i = (steps_to_move == 1) ? 0 : 1;
        while (i < steps_to_move && curr_turtle_cell[0] > 0) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
            curr_turtle_cell[0] --;
            i++;
        }

        if (curr_turtle_cell[0] == 0) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
        }

        showCurrentPosition ();
    }

    private void moveForwardInWestDirection (int steps_to_move) {
        int i = (steps_to_move == 1) ? 0 : 1;
        while (i < steps_to_move && curr_turtle_cell[1] > 0) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
            curr_turtle_cell[1] --;
            i ++;
        }

        if (curr_turtle_cell[1] == 0) {
            updateTortureFloor(curr_turtle_cell[0],curr_turtle_cell[1]);
        }

        showCurrentPosition ();
    }

    private void moveTurtleForward (int steps_to_move) {

        switch (curr_turtle_direction) {
            case DIRECTION.NORTH -> moveForwardInNorthDirection(steps_to_move);
            case DIRECTION.EAST -> moveForwardInEastDirection(steps_to_move);
            case DIRECTION.SOUTH -> moveForwardInSouthDirection(steps_to_move);
            case DIRECTION.WEST -> moveForwardInWestDirection(steps_to_move);
        }
    }

    private void turtleMoveForwardStepsInitialize () {

        int forward_steps_to_move = UI.showInputDialogs("Enter the steps turtle is going to move: ");
        moveTurtleForward(forward_steps_to_move);
    }

    private void display_turtle_moves () {

        String title = "Turtle moves\n----------------------\n";
        String turtle_moves = "";

        for (int i = 0; i < turtle_floor.length; i++) {
            for (int j = 0; j < turtle_floor[i].length; j++) {

                if (i == curr_turtle_cell[0] && j == curr_turtle_cell[1]) {
                    turtle_moves += "😁";
                    continue;
                }
                if (turtle_floor[i][j] == 0) {
                    turtle_moves += "    ";
                }
                else {
                    turtle_moves += "🐢";
                }
            }
            turtle_moves += "\n";
        }

        String display_msg = title + turtle_moves;
        UI.showMessageDialogs (display_msg);
    }

    public void turtleCommands (int command) {
        switch (command) {
            case 1:
                turtlePenUp();
            break;
            case 2:
                turtlePenDown();
            break;
            case 3:
                changeTurtleFacingDirection(3);
            break;
            case 4:
                changeTurtleFacingDirection(4);
            break;
            case 5:
                turtleMoveForwardStepsInitialize();
            break;
            case 6:
                display_turtle_moves();
            break;
            case 7:
                UI.showMessageDialogs("The pen is "+getCurr_turtle_pen_position());
            break;
            case 8:
                UI.showMessageDialogs("The turtle is facing "+getCurr_turtle_direction()+" direction");
            break;
            case 9:
                resetPath();
            break;
            default:
               UI.showMessageDialogs("You have made a wrong press double check!");
        }
    }
}
