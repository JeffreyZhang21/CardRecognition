package blackjack;

/**
 * contain the suits of a card
 * @author Jeffr
 * @version 1
 * @since 5/26/19
 */
public enum Suit 
{
	HEARTS {
		public String toString()
		{
			return "hearts";
		}
	},
	DIAMONDS {
		public String toString()
		{
			return "diamonds";
		}
	},
	SPADES {
		public String toString()
		{
			return "spades";
		}
	},
	CLUBS {
		public String toString()
		{
			return "clubs";
		}
	},
}
