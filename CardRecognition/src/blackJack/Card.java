package blackJack;

public class Card 
{
	private String suit;
	private int number;
	
	
	public Card(String suit, int number)
	{
		if(!validCard(suit,number))
			throw new IllegalArgumentException("invalid card values");
		this.suit = suit;
		this.number = number;
	}
	
	public Card(String card) // must be in the format of suit " " number
	{
		String s = card.substring(0,card.indexOf(" "));
		int n = Integer.parseInt(card.substring(card.indexOf(" ")+1));
		if(!validCard(s,n))
			throw new IllegalArgumentException("invalid card values");
		suit = s;
		number = n;
	}
	
	public boolean validCard(String suit, int value)
	{ 
		suit = suit.toLowerCase();
		return value >= 1 && value <=13 && (
				   suit.contains("heart")   || suit.equals("s") 
				|| suit.contains("diamond") || suit.equals("d") 
				|| suit.contains("spade")   || suit.equals("s")
				|| suit.contains("clover")  || suit.equals("c"));
	}
	
	public String getSuit()
	{ return suit; }
	
	public int getNumber()
	{ return number; }
	
	public String toString()
	{ return number + " of " + suit;}
}
