public class BestHandAnalysis {

    private static DeckOfCards deckOfCards;

    private static boolean[] hand1_cards_bool, hand2_cards_bool;

    private static Card[] hand_1_cards, hand_2_cards;

    public static int getBestHand (DeckOfCards deckOfCards) {

        if (!deckOfCards.get_if_cards_can_be_dealt()) {
            UI.showMessageDialog("Please first shuffle cards");
            return 0;
        }

        BestHandAnalysis.deckOfCards = deckOfCards;

        hand_1_cards = deckOfCards.getHand_1_cards();
        hand_2_cards = deckOfCards.getHand_2_cards();

        if (hand1_cards_bool != null) {
            hand1_cards_bool = null;
            hand2_cards_bool = null;
        }

        final int ARRAY_LENGTH = 10;

        hand1_cards_bool = new boolean[ARRAY_LENGTH];
        hand2_cards_bool = new boolean[ARRAY_LENGTH];

        return getEachHandsData();
    }

    private static int getEachHandsData () {
        CardsAnalysis.analyze_player_dealt_cards(deckOfCards, false);
        CardsAnalysis.getPlayersHandCards_ForAnalysis(hand1_cards_bool, hand2_cards_bool);
        return handAnalysis();
    }

    private static int handAnalysis () {

        int best_hand = getBestHand_HighLevelAnalysis();

        switch (best_hand) {
            case 0 -> {
                UI.showMessageDialog("Computer has the best cards");
                return 0;
            }
            case 1 -> {
                UI.showMessageDialog("User has the best cards");
                return 1;
            }
            case 2 -> {
                best_hand = getBestHand_LowLevelAnalysis();

                if (best_hand == 0) {
                    UI.showMessageDialog("Computer has the best cards");
                    return 0;
                }
                else if (best_hand == 1) {
                    UI.showMessageDialog("User has the best cards");
                    return 1;
                }
                else {
                    UI.showMessageDialog("It's a tie");
                }
            }
        }
        return 2;
    }

    private static int getBestHand_HighLevelAnalysis () {

        int bestHand = 2;

        for (int i = 0; i < hand1_cards_bool.length; i++) {

            if (hand1_cards_bool[i] && !hand2_cards_bool[i]) {
                bestHand = 0;
                return bestHand;
            }

            if (!hand1_cards_bool[i] && hand2_cards_bool[i]) {
                bestHand = 1;
                return bestHand;
            }

            if (hand1_cards_bool[i] && hand2_cards_bool[i]) {

                boolean is_normal_straight = (i == 6) ? true : false;
                bestHand = extractSameLevelRepeatingCards(is_normal_straight);

                return bestHand;
            }
        }
        return bestHand;
    }

    private static int extractSameLevelRepeatingCards (boolean is_normal_straight) {

        if (is_normal_straight) {

            boolean[] hand_1_cards_bool, hand_2_cards_bool;

            hand_1_cards_bool = new boolean[13];
            hand_2_cards_bool = new boolean[13];

            normal_straight_extract (hand_1_cards_bool, BestHandAnalysis.hand_1_cards);
            normal_straight_extract (hand_2_cards_bool, BestHandAnalysis.hand_2_cards);

            return getTheHighestNormalStraight (hand_1_cards_bool, hand_2_cards_bool);
        }

        int[] hand_1_repeating_cards, hand_2_repeating_cards;
        int high_repetition;

        hand_1_repeating_cards = new int[13];
        hand_2_repeating_cards = new int[13];
        high_repetition = 0;

        high_repetition = extract (hand_1_repeating_cards, hand_1_cards, high_repetition);
        extract (hand_2_repeating_cards, hand_2_cards, high_repetition);

        return compareHandsWithHighRepetition (hand_1_repeating_cards, hand_2_repeating_cards, high_repetition);
    }


    private static int extract (int[] hand_repeating_cards_array, Card[] cards, int high_repetition) {

        int i;

        for (i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            hand_repeating_cards_array[getFace(cards[i])]++;
        }

        if (high_repetition == 0) {
            high_repetition = hand_repeating_cards_array[0];
            for (i = 0; i < hand_repeating_cards_array.length; i++) {
                if (hand_repeating_cards_array[i] > high_repetition) {
                    high_repetition = hand_repeating_cards_array[i];
                }
            }
        }

        return high_repetition;
    }

    private static int compareHandsWithHighRepetition (int[] hand_1_repeating_cards, int[] hand_2_repeating_cards, int high_repeat) {

        for (int i = hand_1_repeating_cards.length - 1; i >= 0; i--) {

            if (hand_1_repeating_cards[i] > hand_2_repeating_cards[i] && hand_1_repeating_cards[i] == high_repeat) {
                return 0;
            }

            if (hand_2_repeating_cards[i] > hand_1_repeating_cards[i] && hand_2_repeating_cards[i] == high_repeat) {
                return 1;
            }
        }

        return 2;
    }

    private static void normal_straight_extract (boolean[] extracted_cards, Card[] cards) {
        for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            extracted_cards[cards[i].getFaceIndex(cards[i])] = true;
        }
    }

    private static int getTheHighestNormalStraight (boolean[] hand_1_cards_bool, boolean[] hand_2_cards_bool) {

        for (int i = 0; i < hand_1_cards_bool.length; i++) {

            if (hand_1_cards_bool[i] && !hand_2_cards_bool[i]) {
                return 1;
            }

            if (!hand_1_cards_bool[i] && hand_2_cards_bool[i]) {
                return 0;
            }
        }

        return 2;
    }


    private static int getBestHand_LowLevelAnalysis () {

        int best_hand;
        boolean[] hand_1_cards_rank, hand_2_cards_rank;

        hand_1_cards_rank = new boolean[13];
        hand_2_cards_rank = new boolean[13];

        getRanks (hand_1_cards_rank, hand_1_cards);
        getRanks (hand_2_cards_rank, hand_2_cards);

        best_hand = compareBothHands (hand_1_cards_rank, hand_2_cards_rank);

        return best_hand;
    }

    private static void getRanks (boolean[] hand_cards_ranking, Card[] hand_cards) {

        for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            hand_cards_ranking[getFace(hand_cards[i])] = true;
        }
    }


    public static int getFace (Card a_card) {

        switch (a_card.getCard_face()) {

            case DEUCE -> {
                return 0;
            }

            case THREE -> {
                return 1;
            }

            case FOUR -> {
                return 2;
            }

            case FIVE -> {
                return 3;
            }

            case SIX -> {
                return 4;
            }

            case SEVEN -> {
                return 5;
            }

            case EIGHT -> {
                return 6;
            }

            case NINE -> {
                return 7;
            }

            case TEN -> {
                return 8;
            }

            case JACK -> {
                return 9;
            }

            case QUEEN -> {
                return 10;
            }

            case KING -> {
                return 11;
            }

            case ACE -> {
                return 12;
            }
        }
        return 0;
    }

    private static int compareBothHands (boolean[] hand1_cards_rank, boolean[] hand2_cards_bool_rank) {

        for (int i = hand1_cards_rank.length - 1; i >= 0; i--) {

            if (hand1_cards_rank[i] && !hand2_cards_bool_rank[i]) {
                return 0;
            }

            if (!hand1_cards_rank[i] && hand2_cards_bool_rank[i]) {
                return 1;
            }
        }

        return 2;
    }
}
