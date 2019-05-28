package card;

/**
 * Class representation of a single card. A card stores the value it stores and it suit. the value goes
 * from 1 to 13 1 being ace and 13 being King. The suit of the card is also stored
 * @author Jeffr
 *
 */
public class Card 
{
	private Suit suit;
	private int number;
	
	/** 
	 * Constructor to create a card with the given suit and number value
	 * @param suit The Suit of the card. This can be heart, diamond, clover,spade
	 * @param number The number of the card must be in range 1 <= # <= 13
	 * @throws IllegalArgumentException if range if card number is not 1<= # <=13
	 */
	public Card(Suit suit, int number)
	{
		if(!validNumber(number))
			throw new IllegalArgumentException("invalid card values");
		this.suit = suit;
		this.number = number;
	}
	
	/** 
	 * Constructor to create a card with the given suit and number value
	 * @param suit The Suit of the card. This can be heart, diamond, clover,spade. This method is case sensitive so it must be
	 * spelled like the above examples
	 * @param number The number of the card must be in range 1 <= # <= 13
	 * @throws IllegalArgumentException if range if card number is not 1<= # <=13 or suit does not match above examples
	 * @throw NullPointerException if suit is null
	 */
	public Card(String suit, int number)
	{
		if(!validNumber(number))
			throw new IllegalArgumentException("invalid card values");
		this.suit = Suit.valueOf(suit);
		this.number = number;
	}
	
	/**
	 * Checks if a number is inside the allowed range
	 * @param value the value a card will store
	 * @return true if yes false other wise
	 */
	public boolean validNumber(int value)
	{ return value >= 1 && value <=13; }
	
	/**
	 * This method is a getter method for the suit of the card
	 * @return the suit of the card
	 */
	public Suit getSuit()
	{ return suit; }
	
	/**
	 * This method is a getter method for the number of the card
	 * @return a number between 1 <= # <= 13. This is the number the card holds;
	 */
	public int getNumber()
	{ return number; }
	
	/**
	 * Method return this class as a string in the format number +  of  + suit
	 */
	public String toString()
	{ return number + " of " + suit;}
}
