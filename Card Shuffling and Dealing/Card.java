public class Card {

    private Face.FACE card_face;
    private Suit.SUIT card_suit;

    public Card (Face.FACE card_face, Suit.SUIT card_suit) {
        this.card_face = card_face;
        this.card_suit = card_suit;
    }

    public Face.FACE getCard_face () {
        return card_face;
    }

    public Suit.SUIT getCard_suit () {
        return card_suit;
    }

    public String print () {
        return card_face + "  OF  " +card_suit;
    }

    public int getFaceIndex(Card a_card) {
        switch (a_card.card_face) {
            case ACE -> {
                return 0;
            }

            case DEUCE -> {
                return 1;
            }

            case THREE -> {
                return 2;
            }

            case FOUR -> {
                return 3;
            }

            case FIVE -> {
                return 4;
            }

            case SIX -> {
                return 5;
            }

            case SEVEN -> {
                return 6;
            }

            case EIGHT -> {
                return 7;
            }

            case NINE -> {
                return 8;
            }

            case TEN -> {
                return 9;
            }

            case JACK -> {
                return 10;
            }

            case QUEEN -> {
                return 11;
            }

            case KING -> {
                return 12;
            }
        }
        return 0;
    }

    public int getSuitIndex(Card a_card) {

        switch (a_card.card_suit) {
            case HEARTS -> {
                return 0;
            }

            case DIAMONDS -> {
                return 1;
            }

            case CLUBS -> {
                return 2;
            }

            case SPADES -> {
                return 3;
            }
        }
        return 0;
    }
}
