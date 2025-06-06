import java.util.ArrayList;

public class Knight {

    private int[][] chess_board;
    private int TOTAL_COLUMNS = 8;
    private int TOTAL_ROWS = 8;

    private int[] horizontal_moves = {2,1,-1,-2,-2,-1,1,2};
    private int[] vertical_moves = {-1,-2,-2,-1,1,2,2,1};

    private ArrayList<int[]> nextRow_Clm_Visit = new ArrayList<int[]>(10);
    private final int MARKED_VISITED_CELL = 9;

    private int currentRow, currentColumn, counter;


    public Knight () {
        this.chess_board = new int[TOTAL_ROWS][TOTAL_COLUMNS];
        this.currentRow = 4;
        this.currentColumn = 3;
        this.counter = 0;
        implementHeuristicAccessibility();
    }

    private boolean validateKnightMove (int move_number, int row, int clm) {
        int next_knight_row = row + vertical_moves[move_number];
        int next_knight_clm = clm + horizontal_moves[move_number];

        if (next_knight_row >= 0 && next_knight_row < 8 && next_knight_clm >= 0 && next_knight_clm < 8)
        {
            return true;
        }
        return false;
    }


    private void implementHeuristicAccessibility () {
        for (int i = 0; i < chess_board.length; i++) {
            for (int j = 0; j < chess_board[i].length; j++) {

                int accessible = 0;
                for (int k = 0; k < vertical_moves.length; k++) {
                    boolean is_valid = validateKnightMove(k,i,j);
                    if (is_valid) {
                        accessible ++;
                    }
                }
                chess_board[i][j] = accessible;
            }
        }
    }

    private void resetChessBoard () {
        implementHeuristicAccessibility();
        currentColumn = 0;
        currentRow = 0;
        counter = 0;
        UI.showMessageDialog("Board and Knight position reset done successfully");
    }

    private void printChessBoard () {

        String display_message = "";

        String title = "Chess Board Knight tour\n-------------------------------\n";

        for (int[] row : chess_board) {
            for (int cell : row) {
                display_message += cell + "      ";
            }
            display_message += "\n";
        }
        display_message = title + display_message;
        UI.showMessageDialog(display_message);
    }

    private String getCurrentKnightPosition () {
        return "[ "+currentRow+" ]["+currentColumn+" ]";
    }

    private int getCurrentCounterValue () {
        return counter;
    }

    private void incrementKnightNewMove () {
        chess_board[currentRow][currentColumn] = MARKED_VISITED_CELL;
        counter ++;
    }

    private void selectNextVisit() {

        int[] small_neighbour = new int[2];
        small_neighbour[0] = nextRow_Clm_Visit.get(0)[0];
        small_neighbour[1] = nextRow_Clm_Visit.get(0)[1];

        for (int[] cell : nextRow_Clm_Visit) {
            int chess_board_cell_val = chess_board[cell[0]][cell[1]];
            int chess_board_small_neighbour_val = chess_board[small_neighbour[0]][small_neighbour[1]];
            if (chess_board_small_neighbour_val > chess_board_cell_val) {
                small_neighbour[0] = cell[0];
                small_neighbour[1] = cell[1];
            }
        }

        currentRow = small_neighbour[0];
        currentColumn = small_neighbour[1];

        incrementKnightNewMove();
    }

    private void addNextVisit (int valid_row,int valid_clm) {

        if (chess_board[valid_row][valid_clm] != MARKED_VISITED_CELL) {
            chess_board[valid_row][valid_clm] --;
            int[] new_visit = {valid_row,valid_clm};
            nextRow_Clm_Visit.add(new_visit);
        }
    }


    private void decrementNeighboursAccessibility() {

        nextRow_Clm_Visit.clear();
        for (int i = 0; i < vertical_moves.length; i++) {
            boolean is_valid = validateKnightMove(i,currentRow,currentColumn);
            if (is_valid) {
                int valid_row = currentRow + vertical_moves[i];
                int valid_clm = currentColumn + horizontal_moves[i];
                addNextVisit(valid_row,valid_clm);
            }
        }
    }

    private void nextKnightVisitController () {

        decrementNeighboursAccessibility();
        if (nextRow_Clm_Visit.size() != 0)
            selectNextVisit();
    }

    private void knightMove () {

        int total_moves = TOTAL_COLUMNS * TOTAL_ROWS;

        incrementKnightNewMove();
        for (int i = 0; i < total_moves; i++) {
            nextKnightVisitController();
        }
        UI.showMessageDialog("Knight visited "+getCurrentCounterValue()+" cells");
    }

    public void knightCommands (int cmd) {
        switch (cmd) {
            case 1 -> knightMove();
            case 2 -> UI.showMessageDialog("Knight current position is "+getCurrentKnightPosition());
            case 3 -> UI.showMessageDialog("The counter's current value is "+getCurrentCounterValue()+"/"+(TOTAL_COLUMNS * TOTAL_ROWS));
            case 4 -> printChessBoard();
            case 5 -> resetChessBoard();
            default -> UI.showMessageDialog("Wrong press!");
        }
    }
}
