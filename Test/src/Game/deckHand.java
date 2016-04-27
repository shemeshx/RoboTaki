package Game;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import Cards.*;

/*
 *this class is for the deck of the robot.  
 */
public class deckHand {
	private LinkedList<Card> hand;

	public deckHand() {
		this.hand=new LinkedList<Card>();
	}
	public void Init(String [] cards)
	/*
	 * this method init the robot with 8 cards.
	 * takes 8 strings and change them to cards.
	 */
	{
		for(int i=0;i<8;i++)
		{
			hand.addFirst(getCardByString(cards[i]) );
		}
	}
	public Card getCardByString(String str)
	/*
	 * this function takes a string and change it to Card.
	 */
	{
		Card c = null;
		if (Character.isDigit(str.charAt(0)))// if it is numeric card.
		{
			int num=Character.getNumericValue(str.charAt(0));
			c = new numberCard(num,Colors.valueOf( str.substring(2, str.length())));
		}
		else if (str.indexOf(" ")!=-1)// if it is special color card such as PLUS RED ...
		{
			c=new specialCardColor(cardType.valueOf(str.substring(0,str.indexOf(" "))),Colors.valueOf(str.substring(str.indexOf(" ")+1,str.length())));
			
		}
		else // if it is special card without color such as SUPER card... 
			c=new specialCardNoColor(cardType.valueOf(str.substring(0,str.length())));

		return c;
	}
	public LinkedList<Card> getHand()
	{
		return this.hand;
	}
	public String toString()
	{
		String str="hand:\n";
		LinkedList<Card> str2=copyList(this.hand);
		while(!str2.isEmpty())
		{
			str=str+str2.getFirst().toString()+"   ,";
			str2.removeFirst();
		}
		return str;
	}
	public LinkedList<Card> copyList(LinkedList<Card> list){
		LinkedList<Card> newList=new LinkedList<Card>();
		for(ListIterator<Card> iter=list.listIterator();iter.hasNext();){
			Card card=iter.next();
			newList.addLast(card);
			}
		return newList;
		
	}
}
