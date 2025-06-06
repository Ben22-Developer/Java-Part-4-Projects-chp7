import java.util.Arrays;

public class Main {

    private static DeckOfCards deckOfCards = new DeckOfCards();

    private static void shuffle_cards () {

        DeckOfCards.setHands();
        DeckOfCards.shuffleTheCards();

        for (int player = 0; player < 2; player++) {
            deckOfCards.setPlayerHand();
        }

        UI.showMessageDialog("Cards shuffled!");
        CardsAnalysis.has_analyzed = false;
    }

    public static void dealt_cards_display (String...players_score) {

        boolean cards_can_be_dealt = deckOfCards.get_if_cards_can_be_dealt();

        if (!cards_can_be_dealt) {
            UI.showMessageDialog("Please first shuffle cards");
            return;
        }

        String display = "";

        for (int j = 0; j < 2; j++) {

            if (j == 0) {
                display += "\n Computer cards\n------------------------\n";
            }
            else {
                display += "\n User cards\n------------------------\n";
            }

            for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
                display += deckOfCards.dealCard().print() + "\n";
            }
            deckOfCards.setCurrentCardTo_0();
        }

//        System.out.println();

        if (players_score.length == 0)
            UI.showMessageDialog(display);
        else
            UI.showMessageDialog( Arrays.toString(players_score.clone())+"\n"+display);
    }

    public static String user_cards (String prompt) {

        String display = "";
        Card[] player_cards = deckOfCards.getHand_2_cards();

        display += "\nPlayer cards\n------------------------\n";
        for (int i = 0; i < player_cards.length; i++) {
            display += player_cards[i].print() +" ( "+(i + 1)+" )\n";
        }

        return UI.showInputStringDialog(display+prompt);
    }


    public static void main (String[] args) {

        String welcome_msg, wrong_press_msg, end_msg;

        welcome_msg = "Press 1 to shuffle cards\nPress 2 to display dealt cards of a deck cards: "+deckOfCards.getCards_to_be_dealt()+"\nPress 3 for card analysis\nPress 4 to analyze and get the best hand\nPress 5 to play poker total rounds are: "+DeckOfCards.getTotal_rounds()+"\nPress 0 to stop the app";
        wrong_press_msg = "U have pressed wrongly";
        end_msg = "Thank u for using our app be back soon!";

        int user_press = UI.showInputIntegerDialog(welcome_msg);

        while (user_press != 0) {

            switch (user_press) {

                case 1 -> {
                    shuffle_cards ();
                }

                case 2 -> {
                    dealt_cards_display();
                }

                case 3 -> {
                    CardsAnalysis.analyze_player_dealt_cards(deckOfCards, true);
                }

                case 4 -> {
                    BestHandAnalysis.getBestHand(deckOfCards);
                }

                case 5 -> {

                    int continue_game = UI.showInputIntegerDialog("Wish u the best luck hero \nDraw well the champion is of "+DeckOfCards.getTotal_rounds()+" rounds"+"\nPress 1 to continue\nPress 0 to return back");

                    while (continue_game != 0 && DeckOfCards.getRounds() < DeckOfCards.getTotal_rounds()) {

                        shuffle_cards ();
                        PlayerDraw.playerDraw_fn(deckOfCards);

                        if (DeckOfCards.getRounds() < DeckOfCards.getTotal_rounds()) {
                            continue_game = UI.showInputIntegerDialog("\nPress 1 to continue\nPress 0 to return back");
                        }
                    }

                    if (continue_game != 0) {
                        DeckOfCards.getWinner();
                    }
                    DeckOfCards.setPointsToDefault();
                }

                default -> {
                    UI.showMessageDialog(wrong_press_msg);
                }
            }

            user_press = UI.showInputIntegerDialog(welcome_msg);
        }

        UI.showMessageDialog(end_msg);
    }
}
