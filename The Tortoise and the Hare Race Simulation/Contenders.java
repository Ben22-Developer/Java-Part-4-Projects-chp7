import java.util.ArrayList;

public class Contenders {

    private int curr_position;

    private String contender_symbol, name, slip;

    public Contenders (String contender_symbol, String name, String slip) {
        this.contender_symbol = contender_symbol;
        this.name = name;
        this.slip = slip;
        curr_position = 0;
    }

    public String geContender_symbol () {
        return this.contender_symbol;
    }

    public String geContender_slip() {
        return this.slip;
    }

    public void updateContenderPath (int cell) {
        if (cell < 0) {
            removePath(cell);
        }
        else if (cell > 0) {
            addPath(cell);
        }
    }

    private void addPath (int cell) {

        curr_position += cell;

        if (curr_position > 69) {
            curr_position = 69;
        }
    }

    private void removePath (int cells_to_remove) {

        curr_position += cells_to_remove;

        if (curr_position < 0) {
            curr_position = 0;
        }
    }

    public int getLastContenderPosition () {

        System.out.println("\n"+this.name+" is at "+curr_position);
        return curr_position;
    }
}
