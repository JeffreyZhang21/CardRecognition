package blackjack;

import java.util.ArrayList;

public class Hand 
{
	private ArrayList<Card> cards; 
	
	public Hand(Card c1, Card c2)
	{
		cards = new ArrayList<Card>();
		cards.add(c1);
		cards.add(c2);
	}
	
	public void add(Card c)
	{
		cards.add(c);
	}
	
	public int getValue()
	{
		int sum = 0;
		for(Card c : cards)
		{
			int val = c.getNumber();
			if(val > 10)
				val = 10;
			else if(val == 1 && sum + 11 <= 21)
				val = 11;
			sum += val;
		}
		
		return sum;
	}
	
	public String toString()
	{
		String result = "";
		for(int i = 0; i < cards.size(); i++)
			result += "card: " + i + ": " + cardString(cards.get(i));
		return result;
	}
	
	private String cardString(Card c)
	{
		int val = c.getNumber();
		if(val == 1)
			return "ace of " + c.getSuit();
		else if(val == 11)
			return "jack of " + c.getSuit();
		else if(val == 12)
			return "queen of " + c.getSuit();
		else if(val == 13)
			return "king of " + c.getSuit();
		else return c.toString();
	}
}
