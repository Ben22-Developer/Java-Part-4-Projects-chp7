import java.awt.*;

public class CrapStatistics {

    private static int[][] game_played_array;
    private static int total_played_games = CrapsSimulator.total_plays;

    private static void winningChanceAsThePlaysImproveAverage () {
        System.out.println();
        int i = 1;
        for (int[] win : game_played_array) {
            System.out.printf("\n\nFor "+i+" Game(s) the chances to win are: %.2f",((double) win[0]/total_played_games)*100);
            i ++;
        }
    }


    private static void averageLengthOfCrapGame () {

        double unit_total_average;
        double total_average = 0;
        int roll = 1;

        for (int[] unit_average : game_played_array) {
            unit_total_average = roll * (unit_average[0] + unit_average[1]);
            total_average += unit_total_average;
            roll++;
        }

        System.out.println("\n"+total_average);

        System.out.printf("\nThe average length of craps game: %.2f",(double)(total_average/total_played_games));
    }

    private static void winningCrapsAverage() {
        int win_chance, loose_chance;
        win_chance = 0;
        loose_chance = 0;
        for (int[] win : game_played_array)
            win_chance += win[0];

        for (int[] loose : game_played_array)
           loose_chance += loose[1];

        System.out.printf("\n Winning probability: %.2f\nLoosing probability: %.2f",((double) win_chance/total_played_games) * 100,((double) loose_chance/total_played_games) * 100);
    }


    private static void gamesForEachRoll () {
        System.out.println("Game Won");
        System.out.println("-----------------");
        System.out.println("Number of plays \t\t Games Won \t\t Games Lost");
        for (int i = 0; i < game_played_array.length; i++) {
            System.out.println((i+1)+"\t\t\t\t\t\t  "+game_played_array[i][0]+"\t\t\t"+game_played_array[i][1]+"\n");
        }

    }

    public static void getCrapStatistics (int[][] game_played) {
        game_played_array = game_played;
        gamesForEachRoll();
        winningCrapsAverage();
        averageLengthOfCrapGame();
        winningChanceAsThePlaysImproveAverage();
    }
}
