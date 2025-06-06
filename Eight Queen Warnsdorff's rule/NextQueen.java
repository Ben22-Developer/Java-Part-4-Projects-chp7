

public class NextQueen {

    private int[][] chess_board;
    private final int CHESS_BOARD_ROW_LENGTH = 8;
    private final int CHESS_BOARD_CLM_LENGTH = 8;

    private final int all_queens = 8;
    private final int queen_palace = 55;
    private final int queen_region = 40;

    private int[][] all_queen_region = { {0,-1} , {-1,0} , {0,1} , {1,0} , {1,-1} , {1,1} , {-1,-1} , {-1,1} };
    private boolean[] queens_rows,queens_clms;

    private int curr_queen_row;
    private int curr_queen_clm;

    public NextQueen () {
        chess_board = new int[CHESS_BOARD_ROW_LENGTH][CHESS_BOARD_CLM_LENGTH];
        initializeArrays();
        curr_queen_row = 0;
        curr_queen_clm = 0;
    }


    private boolean validateQueenNeighbor (int index, int row, int clm) {

        int neighbor_row_cell = row + all_queen_region[index][0];
        int neighbor_clm_cell = clm + all_queen_region[index][1];

        if (neighbor_row_cell >= 0 && neighbor_row_cell <= 7 && neighbor_clm_cell >= 0 && neighbor_clm_cell <= 7) {
            return true;
        }
        return false;
    }

    private int checkIfNeighborIsNotQueenOrQueenRegion (int row, int clm) {
        if (chess_board[row][clm] == queen_palace || chess_board[row][clm] == queen_region) {
            return 0;
        }
        return 1;
    }

    private int collectAllValidNeighbors (int index, int row, int clm) {

        int neighbors = 0;
        int neighbor_row_cell = all_queen_region[index][0] + row;
        int neighbor_clm_cell = all_queen_region[index][1] + clm;

        while (neighbor_row_cell >= 0 && neighbor_row_cell <= 7 && neighbor_clm_cell >= 0 && neighbor_clm_cell <= 7) {

            neighbors += checkIfNeighborIsNotQueenOrQueenRegion (neighbor_row_cell,neighbor_clm_cell);
            neighbor_row_cell += all_queen_region[index][0];
            neighbor_clm_cell += all_queen_region[index][1];
        }

        return neighbors;
    }

    private int affectedRegionsWhenQueenIsOnThisCell (int row, int clm) {

        int neighbors = 0;

        for (int i = 0; i < all_queen_region.length; i++) {
            boolean is_valid = validateQueenNeighbor(i,row,clm);
            if (is_valid) {
                neighbors += collectAllValidNeighbors (i, row, clm);
            }
        }

        return neighbors;
    }

    private void collectChessBoardCellValues () {

        for (int i = 0; i < chess_board.length; i++) {
            for (int j = 0; j < chess_board[i].length; j++) {

                if (chess_board[i][j] == queen_palace) {
                    break;
                }

                if (chess_board[i][j] == queen_region) {
                    continue;
                }

                chess_board[i][j] = affectedRegionsWhenQueenIsOnThisCell(i,j);
            }
        }
    }


     private void initializeQueenRowsAndClmArrays () {
        queens_rows = new boolean[all_queens];
        queens_clms = new boolean[all_queens];
     }

    private void initializeArrays () {
        collectChessBoardCellValues();
        initializeQueenRowsAndClmArrays();
    }

    private void chooseNextQueenRowAndClm () {

        collectChessBoardCellValues();

        printChessBoard();

        int small_cell_val = chess_board[0][0];

        for (int i = 0; i < chess_board.length; i++) {
            for (int j = 0; j < chess_board[i].length; j++) {

                if (queens_rows[i]) {
                    break;
                }

                if (queens_rows[j]) {
                    continue;
                }

                if (small_cell_val > chess_board[i][j]) {
                    small_cell_val = chess_board[i][j];
                    curr_queen_row = i;
                    curr_queen_clm = j;
                }
            }
        }

        System.out.println("[ " +curr_queen_row+" ][ "+curr_queen_clm+" ]");
    }

    private void setQueen_region (int index) {

        int neighbor_row_cell = curr_queen_row + all_queen_region[index][0];
        int neighbor_clm_cell = curr_queen_clm + all_queen_region[index][1];

        while (neighbor_row_cell >= 0 && neighbor_row_cell <= 7 && neighbor_clm_cell >= 0 && neighbor_clm_cell <= 7) {

            if (chess_board[neighbor_row_cell][neighbor_clm_cell] != queen_palace) {
                chess_board[neighbor_row_cell][neighbor_clm_cell] = queen_region;
            }

            neighbor_row_cell += all_queen_region[index][0];
            neighbor_clm_cell += all_queen_region[index][1];
        }
    }

    private void setQueen_palace_and_region () {

        chess_board[curr_queen_row][curr_queen_clm] = queen_palace;

        queens_rows[curr_queen_row] = true;
        queens_clms[curr_queen_clm] = true;


        for (int i = 0; i < all_queen_region.length; i++) {

            while (true) {

                boolean is_valid = validateQueenNeighbor(i, curr_queen_row, curr_queen_clm);
                if (is_valid) {
                    setQueen_region(i);
                }
                break;
            }
        }
    }

    private void placeQueensOnBoard () {

        for (int i = 0; i < /*4*/ all_queens; i++) {
            if (i != 0) {
                chooseNextQueenRowAndClm();
            }
            setQueen_palace_and_region();
        }

        UI.showMessageDialog("Queens are smartly placed on the board");
    }

    private void printChessBoard () {

        String display_message, chess_board_title, chess_board_keys, chess_board_view;
        int i = 0;

        chess_board_title = "Chess Board\n--------------------------------\n";
        chess_board_keys = queen_region+": These are all the queen's region, no other queen can be in N,S,NE,NW, SE, and SW compass direction\n"+queen_palace+": This is where the actual queen settles (The palace)\n";

        chess_board_view = "\n\n";
        chess_board_view += "      ";
        for (i = 0; i < all_queens; i++) {
            chess_board_view += ("0"+i + "    ");
        }

        chess_board_view += "\n";
        i = 0;

        for (int[] row : chess_board) {
            chess_board_view += (i + "    ");
            for (int cell : row) {
                chess_board_view += (cell + "    ");
            }
            chess_board_view += "\n";
            i ++;
        }

        display_message = chess_board_title + chess_board_keys + chess_board_view;
        UI.showMessageDialog(display_message);
    }

    public void nextQueenCommands (int cmd) {

        switch (cmd) {
            case 1 -> placeQueensOnBoard ();
            case 2 -> printChessBoard ();
            default -> UI.showMessageDialog("Wrong Press");
        }
    }

    public void delete () {
        all_queen_region = null;
        chess_board = null;
    }
}
