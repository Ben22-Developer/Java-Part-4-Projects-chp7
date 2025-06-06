import java.security.SecureRandom;
import java.util.ArrayList;

public class DeckOfCards {

    private static int computer_points, user_points;

    private static int rounds = 0;
    private static final int total_rounds = 3;

    private Card[] hand_1_cards, hand_2_cards;
    private static ArrayList<Card> cards_to_play = new ArrayList<>();

    private int currentCard;

    private static final SecureRandom randomize = new SecureRandom();

    private static final int cards_in_deck = 52;
    private static final int cards_to_be_dealt = 5;
    private static int hand_to_deal = 0;
    private static boolean cards_are_ready_to_be_dealt = false;

    public static void setPointsToDefault () {
        computer_points = 0;
        user_points = 0;
        rounds = 0;
    }

    public static void setHands() {

        if (!cards_to_play.isEmpty()) {
            cards_to_play.clear();
        }
        putCardsOnStage();
        shuffleTheCards();
    }

    private static void putCardsOnStage () {

        Face.FACE[] faces = Face.FACE.values();
        Suit.SUIT[] suits = Suit.SUIT.values();

        for (int i = 0; i < cards_in_deck; i++) {
            Card a_card = new Card(faces[i % faces.length], suits[i % suits.length]);
            cards_to_play.add(a_card);
        }
    }

    public static void shuffleTheCards () {

        cards_are_ready_to_be_dealt = true;

        for (int i = cards_to_play.size() - 1; i >= 1; i--) {

            int random_index = randomize.nextInt(i+1);

            if (i != random_index) {
                Card curr_card = cards_to_play.set(i,cards_to_play.get(random_index));
                cards_to_play.set(random_index, curr_card);
            }
        }
    }

    public void setPlayerHand () {

        if (DeckOfCards.hand_to_deal < 5) {
            hand_1_cards = new Card[getCards_to_be_dealt()];
            setPlayer_1_or_2_Hand(hand_1_cards);
        }
        else {
            hand_2_cards = new Card[getCards_to_be_dealt()];
            setPlayer_1_or_2_Hand(hand_2_cards);
        }
        setCurrentCardTo_0();
    }

    private void setPlayer_1_or_2_Hand (Card[] player_cards) {

        for (int i = 0; i < player_cards.length; i++) {

            player_cards[i] = cards_to_play.getLast();
            cards_to_play.removeLast();
            DeckOfCards.hand_to_deal ++;
        }
    }

    public void playerDraw (Card[] player_cards ,int cards_to_draw) {

        for (int i = player_cards.length - cards_to_draw; i < player_cards.length; i++) {
            player_cards[i] = cards_to_play.getLast();
            cards_to_play.removeLast();
        }
    }

    public static String getPlayersPoints () {
        return "Computer: "+computer_points+" User: "+user_points;
    }

    public Card[] getHand_1_cards() {
        return hand_1_cards;
    }

    public Card[] getHand_2_cards() {
        return hand_2_cards;
    }

    public int getCards_to_be_dealt () {
        return cards_to_be_dealt;
    }

    public boolean get_if_cards_can_be_dealt () {
        return cards_are_ready_to_be_dealt;
    }

    public static int getTotal_rounds () {
        return total_rounds;
    }

    public static int getRounds () {
        return rounds;
    }

    public void setCurrentCardTo_0 () {
        currentCard = 0;
        if (hand_to_deal > 5) {
            hand_to_deal = 0;
        }
    }

    public Card dealCard () {
        return DeckOfCards.hand_to_deal < 5 ? dealSpecificHand (hand_1_cards) : dealSpecificHand (hand_2_cards);
    }

    private Card dealSpecificHand (Card[] hand) {

        DeckOfCards.hand_to_deal ++;
        if (currentCard < cards_to_be_dealt) {
            return hand[currentCard ++];
        }
        return null;
    }

    public static void incrementWinnerPoints (int winner) {
        if (winner == 0) {
            computer_points ++;
        }
        else if (winner == 1) {
            user_points ++;
        }
        rounds ++;
    }

    public static void getWinner() {

        String winning_points = "\nComputer: "+computer_points+" VS User: "+user_points;

        if (computer_points > user_points) {
            UI.showMessageDialog("The champion is Computer"+winning_points);
        }
        else if (computer_points < user_points) {
            UI.showMessageDialog("The champion is User"+winning_points);
        }
        else {
            UI.showMessageDialog("It's a tie"+winning_points);
        }
    }
}
