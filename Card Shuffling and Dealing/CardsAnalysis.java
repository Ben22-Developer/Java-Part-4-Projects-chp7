public class CardsAnalysis {

    private static boolean high_straight_hand1, four_kind_hand1, full_house_hand1, flush_hand1, normal_straight_hand1, three_kind_hand1, two_pair_hand1, pair_hand1;
    private static boolean high_straight_hand2, four_kind_hand2, full_house_hand2, flush_hand2, normal_straight_hand2, three_kind_hand2, two_pair_hand2, pair_hand2;

    private static int curr_player;

    private static DeckOfCards deckOfCards;
    private static boolean can_be_straight, can_be_full_house;

    public static boolean has_analyzed = false;

    private static String display_face, display_flush, display_straight, display_full_house;

    private static void setFieldVariablesToDefault () {

        can_be_straight = false;
        can_be_full_house = false;

        high_straight_hand1 = false;
        four_kind_hand1 = false;
        full_house_hand1 = false;
        flush_hand1 = false;
        normal_straight_hand1 = false;
        three_kind_hand1 = false;
        two_pair_hand1 = false;
        pair_hand1 = false;

        high_straight_hand2 = false;
        four_kind_hand2 = false;
        full_house_hand2 = false;
        flush_hand2 = false;
        normal_straight_hand2 = false;
        three_kind_hand2 = false;
        two_pair_hand2 = false;
        pair_hand2 = false;

        display_face = "";
        display_flush = "";
        display_straight = "";
        display_full_house = "";
    }

    public static void analyze_player_dealt_cards (DeckOfCards deckOfCards, boolean showUI) {

        if (CardsAnalysis.deckOfCards == null) {
            CardsAnalysis.deckOfCards = deckOfCards;
        }

        boolean cards_can_be_dealt = deckOfCards.get_if_cards_can_be_dealt();

        if (!cards_can_be_dealt) {
            UI.showMessageDialog("Please first shuffle cards");
            return;
        }


        if (!has_analyzed) {
            setFieldVariablesToDefault();

            int players = 2;

            for (curr_player = 0; curr_player < players; curr_player++) {
                display_face += (curr_player == 0) ? "Hand 1 card pair and kinds analysis\n----------------------------\n" : "\nHand 2 card pair and kinds analysis\n--------------------------------\n";
                display_face += cardFaceAnalysis () +"\n";
            }

            for (curr_player = 0; curr_player < players; curr_player++) {
                display_flush += (curr_player == 0) ? "Hand 1 flush cards analysis\n----------------------------\n" : "\nHand 2 flush cards analysis\n--------------------------------\n";
                display_flush += flushCardsAnalysis () +"\n";
            }


            for (curr_player = 0; curr_player < players; curr_player++) {
                display_straight += (curr_player == 0) ? "Hand 1 straight cards analysis\n----------------------------\n" : "\nHand 2 straight cards analysis\n--------------------------------\n";
                display_straight += straightCardsAnalysis () +"\n";
            }

            for (curr_player = 0; curr_player < players; curr_player++) {
                display_full_house += (curr_player == 0) ? "Hand 1 full house cards analysis\n----------------------------\n" : "\nHand 2 full house cards analysis\n--------------------------------\n";
                display_full_house += fullHouseCardAnalysis () +"\n";
            }
            has_analyzed = true;
        }

        if (showUI) {
            UI.showMessageDialog("(A) " + display_face + "\n(B)" + display_flush + "\n(C)" + display_straight + "\n(D)" + display_full_house);
        }
    }

    private static String cardFaceAnalysis () {

        int total_card_faces = 13;
        int[] card_face_appearance;

        card_face_appearance = new int[total_card_faces];

        collectSameFaceCards (card_face_appearance);
        String display = printSameFaceCard (card_face_appearance);
        return display;
    }

    private static void collectSameFaceCards (int[] card_face_array) {

        for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            manipulateFaceBasedArray (card_face_array);
        }
        deckOfCards.setCurrentCardTo_0();
    }

    private static String printSameFaceCard (int[] card_face_array) {

        String display = "";

        int a_pair,  three_of_kind, four_of_kind;

        a_pair = 0;
        three_of_kind = 0;
        four_of_kind = 0;

        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

        for (int i = 0; i < card_face_array.length; i++) {

            switch (card_face_array[i]) {

                case 2 -> {
                    display += "Pair for: "+faces[i]+"\n";
                    a_pair ++;
                }

                case 3 -> {
                    display += "Three of a kind for: "+faces[i];
                    three_of_kind ++;
                }

                case 4 -> {
                    display += "Four of a kind for: "+faces[i];
                    four_of_kind ++;
                }
            }
        }

        display = filterFaceCardDisplay (display, a_pair, three_of_kind, four_of_kind);
        currPlayerHandAnalysis_SameFace (a_pair, three_of_kind, four_of_kind);
        return display;

    }

    private static String filterFaceCardDisplay (String display, int a_pair, int three_of_kind, int four_of_kind) {

        if (a_pair == 0 && three_of_kind == 0 && four_of_kind == 0) {
            can_be_straight = true;
            return "There is no relationship in user's face cards!";
        }

        if (a_pair != 0) {
            display += a_pair+" pair(s)";
            can_be_full_house = true;
        }

        if (three_of_kind != 0) {
            can_be_full_house = true;
        }
        return display;
    }

    private static void currPlayerHandAnalysis_SameFace (int a_pair, int three_of_kind, int four_of_kind) {

        switch (curr_player) {
            case 0 -> {
                hand1_FaceCardAnalysis (a_pair, three_of_kind, four_of_kind);
            }
            case 1 -> {
                hand2_FaceCardAnalysis (a_pair, three_of_kind, four_of_kind);
            }
        }

    }

    private static void hand1_FaceCardAnalysis (int a_pair, int three_of_kind, int four_of_kind) {

        if (a_pair == 1) {
            pair_hand1 = true;
        }

        if (a_pair == 2) {
            two_pair_hand1 =true;
        }

        if (three_of_kind > 0) {
            three_kind_hand1 = true;
        }

        if (four_of_kind > 0) {
            four_kind_hand1 = true;
        }
    }

    private static void hand2_FaceCardAnalysis (int a_pair, int three_of_kind, int four_of_kind) {

        if (a_pair == 1) {
            pair_hand2 = true;
        }

        if (a_pair == 2) {
            two_pair_hand2 =true;
        }

        if (three_of_kind > 0) {
            three_kind_hand2 = true;
        }

        if (four_of_kind > 0) {
            four_kind_hand2 = true;
        }
    }

    private static String flushCardsAnalysis () {

        int[] flush_array;
        int suits_length;

        suits_length = 4;

        flush_array = new int[suits_length];

        collectFlushArrayData (flush_array);
        String display = printFlushFn (flush_array);

        return display;
    }

    private static void collectFlushArrayData (int[] flush_array) {

        for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            Card a_card = deckOfCards.dealCard();
            int face_index = a_card.getSuitIndex(a_card);
            flush_array[face_index] ++;
        }
        deckOfCards.setCurrentCardTo_0();
    }

    private static String printFlushFn (int[] flush_array) {

        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String display = "There is no flush";
        boolean is_flush = false;

        for (int i = 0; i < flush_array.length; i++) {
            if (flush_array[i] == 5) {
                is_flush = true;
                display = "Flush exists for suit: " + suits[i];
                break;
            }
        }

        currPlayerHandAnalysis_Flush(is_flush);
        return display;
    }


    private static void currPlayerHandAnalysis_Flush (boolean is_flush) {

        switch (curr_player) {
            case 0 -> {
                hand1_FlushAnalysis(is_flush);
            }
            case 1 -> {
                hand2_FlushAnalysis(is_flush);
            }
        }
    }

    private static void hand1_FlushAnalysis (boolean is_flush) {
        if (is_flush) {
            flush_hand1 = true;
        }
    }

    private static void hand2_FlushAnalysis (boolean is_flush) {
        if (is_flush) {
            flush_hand2 = true;
        }
    }

    //    To be straight it must be consecutive #no pairs (just unique faces)
    private static String straightCardsAnalysis () {

        if (!can_be_straight) {
            return "There is no straight";
        }

        int[] straight_array;
        int face_length = 13;

        straight_array = new int [face_length];

        collectStraightArrayData (straight_array);
        String display = printStraightDataFn (straight_array);

        return display;
    }

    private static void collectStraightArrayData (int[] straight_array) {

        for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            manipulateFaceBasedArray (straight_array);
        }
        deckOfCards.setCurrentCardTo_0();
    }

    private static String printStraightDataFn (int[] straight_array) {

        boolean is_high_straight = highStraightCheck (straight_array);
        if (is_high_straight) {

            currPlayerHandAnalysis_HighStraight();
            return "High straight exists on: Ace, Ten, Jack, Queen, King";
        }

        return checkOtherStraight (straight_array);
    }

    private static boolean highStraightCheck (int[] straight_array) {

        int index_0, index_9, index_10, index_11,index_12;

        index_0 = 0;        // Ace
        index_9 = 9;        // Ten
        index_10 = 10;      // Jack
        index_11 = 11;      // Queen
        index_12 = 12;      // King

        if (straight_array[index_0] == 1 && straight_array[index_9] == 1 && straight_array[index_10] == 1 &&
                straight_array[index_11] == 1 && straight_array[index_12] == 1)
        {
            return true;
        }
        return false;
    }

    private static String checkOtherStraight (int[] straight_array) {

        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
        String straight;

        int straight_detector = 0;
        straight = "";

        for (int i = 0; i < straight_array.length; i++) {

            if (straight_array[i] == 1) {
                straight_detector ++;
                straight += faces[i] + ", ";
            }
            else {
                break;
            }
        }

        if (straight_detector == 5) {
            currPlayerHandAnalysis_NormalStraight();
            return "Straight exist on: "+straight;
        }
        return "There is no straight";
    }

    private static void currPlayerHandAnalysis_HighStraight () {
        switch (curr_player) {
            case 0 -> {
                high_straight_hand1 = true;
            }
            case 1 -> {
                high_straight_hand2 = true;
            }
        }
    }

    private static void currPlayerHandAnalysis_NormalStraight () {
        switch (curr_player) {
            case 0 -> {
                normal_straight_hand1 = true;
            }
            case 1 -> {
                normal_straight_hand2 = true;
            }
        }
    }


    private static String fullHouseCardAnalysis () {

        if (!can_be_full_house) {
            return "There is no full house";
        }

        int[] full_house_array;
        int total_faces = 13;

        full_house_array = new int[total_faces];
        collectFullHouse(full_house_array);
        return printFullHouseDataFn(full_house_array);
    }

    private static void collectFullHouse (int[] full_house_array) {

        for (int i = 0; i < deckOfCards.getCards_to_be_dealt(); i++) {
            manipulateFaceBasedArray(full_house_array);
        }
        deckOfCards.setCurrentCardTo_0();
    }

    private static String printFullHouseDataFn (int[] full_house_array) {

        String[] faces = {"Ace", "Deuce", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

        String twos_str,threes_str;
        boolean twos, threes;

        twos = false;
        threes = false;
        twos_str = "";
        threes_str = "";

        for (int i = 0; i < full_house_array.length; i++) {

            if (full_house_array[i] == 2) {
                twos = true;
                twos_str = faces[i];
            }
            if (full_house_array[i] == 3) {
                threes = true;
                threes_str = faces[i];
            }

            if (twos && threes) {
                currPlayerHandAnalysis_FullHouse();
                System.out.println();

                return "There is a full house for: "+twos_str+" and "+threes_str;
            }
        }
        return "There is  no full house";
    }

    private static void currPlayerHandAnalysis_FullHouse () {
        switch (curr_player) {
            case 0 -> {
                full_house_hand1 = true;
            }
            case 1 -> {
                full_house_hand2 = true;
            }
        }
    }

    private static void manipulateFaceBasedArray (int[] face_array) {
        Card a_card = deckOfCards.dealCard();
        int face_index = a_card.getFaceIndex(a_card);
        face_array[face_index] ++;
    }

    public static void getPlayersHandCards_ForAnalysis(boolean[] player1_hand,boolean[] player2_hand) {

        hand1_CardAnalysis(player1_hand);

        if (player2_hand != null)
            hand2_CardAnalysis(player2_hand);
    }

    private static void hand1_CardAnalysis (boolean[] player1_hand) {

        if (high_straight_hand1 && flush_hand1) {
            player1_hand[0] = true;
        }

        if (normal_straight_hand1 && flush_hand1) {
            player1_hand[1] = true;
        }

        if (four_kind_hand1) {
            player1_hand[2] = true;
        }

        if (full_house_hand1) {
            player1_hand[3] = true;
        }

        if (flush_hand1) {
            player1_hand[4] = true;
        }

        if (high_straight_hand1) {
            player1_hand[5] = true;
        }

        if (normal_straight_hand1) {
            player1_hand[6] = true;
        }

        if (three_kind_hand1) {
            player1_hand[7] = true;
        }

        if (two_pair_hand1) {
            player1_hand[8] = true;
        }

        if (pair_hand1) {
            player1_hand[9] = true;
        }
    }

    private static void hand2_CardAnalysis (boolean[] player2_hand) {

        if (high_straight_hand2 && flush_hand2) {
            player2_hand[0] = true;
        }

        if (normal_straight_hand2 && flush_hand2) {
            player2_hand[1] = true;
        }

        if (four_kind_hand2) {
            player2_hand[2] = true;
        }

        if (full_house_hand2) {
            player2_hand[3] = true;
        }

        if (flush_hand2) {
            player2_hand[4] = true;
        }

        if (high_straight_hand2) {
            player2_hand[5] = true;
        }

        if (normal_straight_hand2) {
            player2_hand[6] = true;
        }

        if (three_kind_hand2) {
            player2_hand[7] = true;
        }

        if (two_pair_hand2) {
            player2_hand[8] = true;
        }

        if (pair_hand2) {
            player2_hand[9] = true;
        }
    }
}
