package blackJack;

import java.util.ArrayList;

public class CardStack 
{
	private ArrayList<Card> cards;
	
	public CardStack()
	{ cards = new ArrayList<>(); }
	
	public void addCard(String card)
	{ cards.add(new Card(card)); }
	
	public void addCard(Card card)
	{ cards.add(card); }
	
	public int size()
	{ return cards.size(); }
	
	public boolean contains(Card card)
	{ return cards.contains(card); }
	
	public boolean contains(String suit)
	{
		for(Card c: cards)
			if(c.getSuit().equals(suit))
				return true;
		return false;
	}
	
	public boolean contains(int number)
	{
		for(Card c: cards)
			if(c.getNumber() == number)
				return true;
		return false;
	}
	
	public ArrayList<Card> getCards()
	{ return cards; }
	 
	public void clearCards()
	{ cards.clear(); }
	
	public int getCardSum()
	{ return cards.stream().mapToInt(i -> i.getNumber()).sum(); }
	
	public boolean isFlush()
	{
		if(cards.isEmpty())
			return false;
		String s = cards.get(0).getSuit();
		for(int i=1; i<cards.size(); i++)
			if(!cards.get(i).getSuit().equals(s))
				return false;
		return true;
	}
	
	public boolean sameNumbers()
	{
		if(cards.isEmpty())
			return false;
		int n = cards.get(0).getNumber();
		for(int i=1; i<cards.size(); i++)
			if(!(cards.get(i).getNumber() == n))
				return false;
		return true;
	}
}
