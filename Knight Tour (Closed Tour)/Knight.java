import java.util.Scanner;
import java.util.ArrayList;

public class Knight {

    private int[][] chess_board;

    private int curr_row, curr_clm, knight_visits;

    private int[][] valid_knight_path = { {2,1}, {2,-1}, {-2,1}, {-2,-1}, {1,2}, {-1,2}, {-1,-2}, {1,-2} };

    private ArrayList<int[]> next_visits = new ArrayList<>(10);
    private ArrayList<int[]> starting_knight_row_clm = new ArrayList<>();

    public Knight (int start_row, int start_clm) {
        this.curr_row = start_row;
        this.curr_clm = start_clm;
    }

    private void makeChessBoard () {

        if (chess_board != null) {
            chess_board = null;
        }

        final int TOTAL_CLM = 8;
        final int TOTAL_ROWS = 8;

        chess_board = new int[TOTAL_ROWS][TOTAL_CLM];
        constructKnightPaths();
    }

    private void constructKnightPaths () {
        for (int i = 0; i < chess_board.length; i++) {
            for (int j = 0; j < chess_board[i].length; j++) {
                chess_board[i][j] = getKnight_visitsFromThisCell(i,j);
            }
        }
    }

    private int getKnight_visitsFromThisCell (int row, int clm) {

        int paths = 0;
        for (int i = 0; i < valid_knight_path.length; i++) {
            paths += calculateCellValue(row, clm, i);
        }

        return paths;
    }

    private int calculateCellValue (int row, int clm, int index) {

        int next_row = row + valid_knight_path[index][0];
        int next_clm = clm + valid_knight_path[index][1];

        return (checkIfCellNeighborIsValid (next_row, next_clm)) ? 1 : 0;
    }

    private void printChessBoard () {

        System.out.println("Chess Board Visual");
        System.out.println("-----------------------\n");

        for (int i = 0; i < chess_board.length; i++) {
            for (int j = 0; j < chess_board[i].length; j++) {
                System.out.print(chess_board[i][j]+"\t");
            }
            System.out.println();
        }
    }

    private void closedTourCheck () {

        initializeStartingPoints();

        int[] curr_starting_row_clm = {curr_row, curr_clm};

        starting_knight_row_clm.add(curr_starting_row_clm);

        int closed_tour = 1;
        int i = 0;

        while (true) {

            moveKnight(i);

            boolean is_closed_tour = checkIfItsClosedTour(i);

            if (is_closed_tour) {
                System.out.println("Attempted closed tour "+closed_tour+" times");
                break;
            }
            else {
                closed_tour ++;
                initializeStartingPoints();
                addNextStartingPoints(i);
            }
            i++;
            curr_row = starting_knight_row_clm.get(i)[0];
            curr_clm = starting_knight_row_clm.get(i)[1];
        }

        starting_knight_row_clm.clear();
    }

    private boolean checkIfItsClosedTour (int index) {

        for (int i = 0; i < valid_knight_path.length; i++) {

             int row = starting_knight_row_clm.get(index)[0] + valid_knight_path[i][0];
             int clm = starting_knight_row_clm.get(index)[1] + valid_knight_path[i][1];

             if (row == curr_row && clm == curr_clm) {
                 return true;
             }
        }
        return false;
    }

    public void initializeStartingPoints () {
        makeChessBoard();
        knight_visits = 0;
    }

    private void addNextStartingPoints (int index) {

        for (int i = 0; i < valid_knight_path.length; i++) {

            int next_start_row_candidate = starting_knight_row_clm.get(index)[0] + valid_knight_path[i][0];
            int next_start_clm_candidate = starting_knight_row_clm.get(index)[1] + valid_knight_path[i][1];

            if (checkIfCellNeighborIsValid(next_start_row_candidate, next_start_clm_candidate)) {

                boolean is_starting_point_before = checkIfCellNeighborHasNotBeenStartingPointBefore(next_start_row_candidate, next_start_clm_candidate);

                if (!is_starting_point_before) {
                    int[] next_starting_point = { next_start_row_candidate, next_start_clm_candidate };
                    starting_knight_row_clm.add(next_starting_point);
                }
            }
        }
    }

    private boolean checkIfCellNeighborHasNotBeenStartingPointBefore (int next_start_row, int next_start_clm) {

        for (int[] starting_points : starting_knight_row_clm) {

            if (starting_points[0] == next_start_row && starting_points[1] == next_start_clm) {
                return true;
            }
        }
        return false;
    }

    private void moveKnight(int index) {

        int attempts = 0;
        final int MAX_ATTEMPTS = 1000;


        while (true && attempts < MAX_ATTEMPTS) {
            markCellVisited();

            if (knight_visits == 64) {
                System.out.println("\nThe last visited cell is: [ "+curr_row+" ][ "+curr_clm+" ]\nThe starting point was: [ "+starting_knight_row_clm.get(index)[0]+" ][ "+starting_knight_row_clm.get(index)[1]+" ]");
                break;
            }
            updateCurrCellNeighbors();
            selectNextKnightVisit();
            attempts ++;
        }
    }

    private void markCellVisited () {

        final int VISITED_MARK = 9;
        if (chess_board[curr_row][curr_clm] != VISITED_MARK) {
            chess_board[curr_row][curr_clm] = VISITED_MARK;
            knight_visits ++;
        }
    }

    private void updateCurrCellNeighbors () {

        for (int i = 0; i < valid_knight_path.length; i++) {

            int next_row_candidate = valid_knight_path[i][0] + curr_row;
            int next_clm_candidate = valid_knight_path[i][1] + curr_clm;

            if (checkIfCellNeighborIsValid (next_row_candidate, next_clm_candidate)) {

                chess_board[next_row_candidate][next_clm_candidate] -= 1;
                int[] next_visit_candidate = {next_row_candidate,next_clm_candidate};
                next_visits.add(next_visit_candidate);
                next_visit_candidate = null;
            }
        }
    }

    private void selectNextKnightVisit () {

        int small_val = Integer.MAX_VALUE;

        int[] next_visit = {};
        next_visit = new int[2];

        for (int[] next_visit_candidate_array : next_visits) {
            if (small_val > chess_board[next_visit_candidate_array[0]][next_visit_candidate_array[1]]) {

                small_val = chess_board[next_visit_candidate_array[0]][next_visit_candidate_array[1]];
                next_visit[0] = next_visit_candidate_array[0];
                next_visit[1] = next_visit_candidate_array[1];
            }
        }

        curr_row = next_visit[0];
        curr_clm = next_visit[1];

        next_visits.clear();
    }

    private boolean checkIfCellNeighborIsValid (int row, int clm) {
        return (row >= 0 && row <= 7 && clm >= 0 && clm <= 7 && chess_board[row][clm] != 9) ? true : false;
    }

    private void changeStartingPoints () {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter the next starting row: ");
        int custom_curr_row = input.nextInt();

        while (custom_curr_row < 0 || custom_curr_row > 7) {

            System.out.print("Invalid row\nThe row index must be between 0 and 7\nRe enter the index or tap -1 to exit\nEnter: ");
            custom_curr_row = input.nextInt();

            if (custom_curr_row == -1) {
                return;
            }
        }

        System.out.print("Enter the next starting clm: ");
        int custom_curr_clm = input.nextInt();

        while (custom_curr_clm < 0 || custom_curr_clm > 7) {

            System.out.print("Invalid clm\nThe clm index must be between 0 and 7\nRe enter the index or tap -1 to exit\nEnter: ");
            custom_curr_clm = input.nextInt();

            if (custom_curr_clm == -1) {
                return;
            }
        }

        curr_row = custom_curr_row;
        curr_clm = custom_curr_clm;

        System.out.println("\nNow the knight is at index [ "+custom_curr_row+" ][ "+custom_curr_clm+" ] in the chess board, and it's ready to start the tour");
    }

    public void KnightCmd (int cmd) {

        switch (cmd) {
            case 1:
                closedTourCheck();
            break;
            case 2:
                printChessBoard();
            break;
            case 3:
                changeStartingPoints();
            break;
            default:
                System.out.println("Wrong Press");
        }
    }

    public void delete () {
        chess_board = null;
    }

}
