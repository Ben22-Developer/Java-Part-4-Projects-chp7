import java.security.SecureRandom;

public class CrapsSimulator {

    private static int ARRAY_LENGTH = 20;
    private static int ROW_LENGTH = 2;
    private static int[][] game_played;
    public static int total_plays = 100 /*1000000*/;
    private static enum GameStatus {WIN, CONTINUE, LOSS};
    private static GameStatus current_game_status;
    private static int next_target;
    private static SecureRandom randomize = new SecureRandom();

    public static void initializeGamePlayedArr () {
        game_played = new int[ARRAY_LENGTH][ROW_LENGTH];
    }

    private static void deleteRecords () {
        game_played = null;
    }

    private static void printWinLossRecords () {
        System.out.println("Total records");
        System.out.println("--------------------");
        int i = 0;
        for (int[] game : game_played) {
            System.out.println("\nNumber "+(i)+" game");
            System.out.println("Wins: "+game[0]+"\t\t Losses: "+game[1]);
            i++;
        }
    }


    private static void updateForWin (int index) {
        try {
            game_played[index][0] ++;
        }
        catch (IndexOutOfBoundsException e) {
            game_played[19][0] ++;
        }
    }

    private static void updateForLoss (int index) {
        try {
            game_played[index][1] ++;
        }
        catch (IndexOutOfBoundsException e) {
            game_played[19][1] ++;
        }
    }

    private static boolean makeWinOrLossDecision (int play_times) {
        if (current_game_status == GameStatus.WIN) {
            updateForWin(play_times);
            return false;
        }
        else if (current_game_status == GameStatus.LOSS) {
            updateForLoss(play_times);
            return false;
        }
        else {
            return true;
        }
    }

    private static int randomPlayValue () {
        int random1 = 1 + randomize.nextInt(5);
        int random2 = 1 + randomize.nextInt(5);
        int random_play = random1 + random2;
        return random_play;
    }

    private static int computerPlayAfterFirstGame (int play_times) {

        while(current_game_status == GameStatus.CONTINUE) {

            int comp_play = randomPlayValue();
            play_times ++;

//            System.out.println(play_times+" games played: "+comp_play);

            if (comp_play == next_target) {
                current_game_status = GameStatus.WIN;
            }
            else if (comp_play == 7) {
                current_game_status = GameStatus.LOSS;
            }
        }

        return play_times;
    }


    private static void firstGame () {
        int comp_play = randomPlayValue();

//        System.out.println("First game, comp play: "+comp_play);

        switch (comp_play) {
            case 7:
            case 11:
                current_game_status = GameStatus.WIN;
            break;
            case 2:
            case 12:
                current_game_status = GameStatus.LOSS;
            break;
            default:
                current_game_status = GameStatus.CONTINUE;
                next_target = comp_play;
        }
    }

    private static void computerGamePlayingCoordinator () {

        int play_times = 0;

        if (play_times == 0) {
            firstGame();
        }


        boolean can_game_continue = makeWinOrLossDecision(play_times);

        if (can_game_continue) {
//            System.out.println("Other plays: ");
            play_times = computerPlayAfterFirstGame(play_times);
            makeWinOrLossDecision(play_times);
        }
    }



    public static void startPlaying () {

        for (int i = 0; i < total_plays; i++) {
//            System.out.println("\nNumber "+i+" play");
//            System.out.println("-------------------------");
            computerGamePlayingCoordinator();
        }

//        printWinLossRecords();
        CrapStatistics.getCrapStatistics(game_played);
        deleteRecords();
    }
}
