import java.security.DigestException;
import java.util.ArrayList;

public class PlayerDraw {

    private static DeckOfCards deckOfCards;

    private static boolean[] player_1_cards_status;

    private static ArrayList<Integer> user_draws_array = new ArrayList<>(5);

//    Remember each player must draw only once
    public static void playerDraw_fn (DeckOfCards deckOfCards_para) {

        if (!deckOfCards_para.get_if_cards_can_be_dealt()) {
            UI.showMessageDialog("Please first shuffle cards");
            return;
        }

        if (player_1_cards_status != null) {
            player_1_cards_status = null;
        }

        if (!user_draws_array.isEmpty()) {
            user_draws_array.clear();
        }

        deckOfCards = deckOfCards_para;

        player_1_cards_status = new boolean[10];

        getEachHandsData();
    }

    private static void getEachHandsData () {

        CardsAnalysis.analyze_player_dealt_cards(deckOfCards, false);
        CardsAnalysis.getPlayersHandCards_ForAnalysis( player_1_cards_status, null);

        int cards_to_draw = checkPlayerCardStatus (player_1_cards_status);

        if (cards_to_draw != 0) {
            makePlayerDraw_FnCtrl (cards_to_draw, deckOfCards.getHand_1_cards());
        }

        String user_draws = Main.user_cards("\nRound "+(DeckOfCards.getRounds()+1)+" / "+DeckOfCards.getTotal_rounds()+"\nEnter the index of your cards to draw below, each draw must be separated from another by comma\nExample: 1,2,4\nUse the index as specified above\nYou can press 0 if you don't want to draw");

        getUserDraws (user_draws);

        CardsAnalysis.has_analyzed = false;

        int the_winner = BestHandAnalysis.getBestHand(deckOfCards);

        DeckOfCards.incrementWinnerPoints (the_winner);

        Main.dealt_cards_display(DeckOfCards.getPlayersPoints());

    }

    private static int checkPlayerCardStatus (boolean[] player_cards_status) {

        for (int i = 0; i < player_cards_status.length; i++) {
            if (player_cards_status[i]) {
                return getCardsToDraw(i);
            }
        }

//        no win status at all
        return 4;
    }

    private static int getCardsToDraw (int player_card_status) {

//        Top status royal flush -> Normal Straight
        if (player_card_status <= 6) {
            return 0;
        }

//        Three of a kind
        else if (player_card_status == 7) {
            return 2;
        }

//        Two pairs
        else if (player_card_status == 8) {
            return 1;
        }

//      One pair
        return 3;
    }

    private static void makePlayerDraw_FnCtrl (int cards_to_draw, Card[] player_cards) {

        int[] high_face_in_player_cards;

        high_face_in_player_cards = new int[13];

        getHighFaceInPlayerCards (high_face_in_player_cards, player_cards);

        if (cards_to_draw == 4) {
            eliminateLowerRankCards(high_face_in_player_cards, player_cards);
        }
        else {
            eliminateUnneededCards (high_face_in_player_cards, player_cards);
        }
        makePlayerDraw(cards_to_draw, player_cards);
    }

    private static void getHighFaceInPlayerCards (int[] player_face_array, Card[] player_cards) {

        int i;
        for (i = 0; i < player_cards.length; i++) {
            player_face_array[BestHandAnalysis.getFace(player_cards[i])] += 1;
        }
    }

//    for pairs, 3 of a kind
    private static void eliminateUnneededCards (int[] player_face_array, Card[] player_cards) {

        int i;
        for (i = 0; i < player_face_array.length; i++) {
            if (player_face_array[i] == 1) {
                Face.FACE face_name = getFaceName(i);
                remove_a_card (face_name, player_cards);
            }
        }

        re_arrangePlayerCards (player_cards);
    }

//    when based on the highest rank only
    private static void eliminateLowerRankCards (int[] player_face_array, Card[] player_cards) {

        int i;
        for (i = player_face_array.length - 1; i >= 0; i--) {

            if (player_face_array[i] == 1) {
                break;
            }
        }

        removeLowerRanksCards (player_cards, i);
    }

    private static void removeLowerRanksCards (Card[] player_cards, int index) {

        Face.FACE face_name = getFaceName(index);

        for (int i = 0; i < player_cards.length; i++) {

            if (!player_cards[i].getCard_face().equals(face_name)) {
                player_cards[i] = null;
            }
            else {
                player_cards[0] = player_cards[i];
                if (i != 0) {
                    player_cards[i] = null;
                }
            }
        }
    }

    private static void makePlayerDraw (int cards_to_draw, Card[] player_cards) {
        deckOfCards.playerDraw(player_cards,cards_to_draw);
    }

    private static Face.FACE getFaceName (int index) {

        Face.FACE face_name = null;

        switch (index) {

            case 0 -> {
                face_name = Face.FACE.DEUCE;
            }
            case 1 -> {
                face_name = Face.FACE.THREE;
            }
            case 2 -> {
                face_name = Face.FACE.FOUR;
            }
            case 3 -> {
                face_name = Face.FACE.FIVE;
            }
            case 4 -> {
                face_name = Face.FACE.SIX;
            }
            case 5 -> {
                face_name = Face.FACE.SEVEN;
            }
            case 6 -> {
                face_name = Face.FACE.EIGHT;
            }
            case 7 -> {
                face_name = Face.FACE.NINE;
            }
            case 8 -> {
                face_name = Face.FACE.TEN;
            }
            case 9 -> {
                face_name = Face.FACE.JACK;
            }
            case 10 -> {
                face_name = Face.FACE.QUEEN;
            }
            case 11 -> {
                face_name = Face.FACE.KING;
            }
            case 12 -> {
                face_name = Face.FACE.ACE;
            }
        }
        return face_name;
    }

    private static void remove_a_card (Face.FACE face_name, Card[] player_cards) {

        int i;

        for (i = 0; i < player_cards.length; i++) {
            if (player_cards[i] != null && player_cards[i].getCard_face().equals(face_name)) {
                break;
            }
        }

        player_cards[i] = null;
    }

    private static void re_arrangePlayerCards (Card[] player_cards) {

        for (int i = 0; i < player_cards.length; i++) {

            if (player_cards[i] == null) {
               boolean can_continue = swapNulAnd_NonNull (player_cards,i);
               if (!can_continue) {
                   break;
               }
            }
        }
    }

    private static boolean swapNulAnd_NonNull (Card[] player_cards, int start_from) {

        for (int j = start_from; j < player_cards.length; j++) {
            if (player_cards[j] != null) {
                Card temp = player_cards[j];
                player_cards[j] = null;
                player_cards[start_from] = temp;
                return true;
            }
        }
        return false;
    }

    private static void getUserDraws (String user_draws) {

        if (user_draws.equals("0") || user_draws.isEmpty()) {
            return;
        }

        for (String index_str: user_draws.split(",")) {
            user_draws_array.add(Integer.parseInt(index_str) - 1);
        }

        removeCardsOfUser (deckOfCards.getHand_2_cards());
        makePlayerDraw(user_draws_array.size(), deckOfCards.getHand_2_cards());
    }

    private static void removeCardsOfUser (Card[] user_cards) {

        for (int i = 0; i < user_draws_array.size(); i++) {
            try {
                user_cards[user_draws_array.get(i)] = null;
            }
            catch (IndexOutOfBoundsException e) {
                UI.showMessageDialog("Index "+(i+1)+" is invalid!\nValid indexes are from 1 to 5");
            }
        }
        re_arrangePlayerCards(user_cards);
    }
}