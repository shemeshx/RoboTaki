package Game;

import java.awt.Color;
import java.awt.List;
import java.awt.Window.Type;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.print.attribute.standard.NumberUp;
import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import Cards.Card;
import Cards.Colors;
import Cards.cardType;
import Cards.numberCard;
import Cards.specialCardColor;
import Cards.specialCardNoColor;

/*
 * this class is about AI - the robot algorithm . 
 * */
public class RobotTaki {
	private deckHand myDeck;
	
	public RobotTaki(String []cards) //start new game
	{
		this.myDeck=new deckHand();
		myDeck.Init(cards);
	}
	
	private void Game()
	{
		while(!this.myDeck.getHand().isEmpty())
		{
			
		}
	}
	
	
	public LinkedList<Card> playTurn(Card card)//this method gets a card on the heap that the player put. the method turns a card.
	{
		/*
		 * this method get the current card witch put on the table
		 * the method find the type of the card and find  all the cards in the dack witch can put on this card
		 * from this list the method find the best card to return
		 * 
		 * if return null seems that cant return any card like in case of the card on the table is stop or 2+
		 * 
		 * if the list returns eampty so there is no available card and need to take card from the bank
		 *
		 */
		LinkedList<Card> myCard = null; //card to pop from the dackhand
		LinkedList<Card>avaiableCards=null;
		//System.out.println(myDeck);
		//System.out.println(card);
		if(card instanceof specialCardColor) //if the card is special card
		{
			avaiableCards=AvailableCards.getAvailableBySpecialColorCard((specialCardColor)card);
			System.out.println(printList(avaiableCards));
		}
		else if(card instanceof specialCardNoColor)
		{
			avaiableCards=AvailableCards.getAvailableByNoColorCard((specialCardNoColor)card);
		}
		else if(card instanceof numberCard) //if the card is number card
		{
			avaiableCards =AvailableCards.getAllAvailableCardsByNumberCard((numberCard)card,this.myDeck.getHand());
			System.out.println(printList(avaiableCards));
		}
		if(avaiableCards==null)
		{ 
			myCard=null; //if can;t return card (skip the turn)
			System.out.println("NO CARDS AVAILABLE");
		}
		else //find the best card to return
		{
			myCard=BestCard.findCardToReturn(avaiableCards,card,this.myDeck.getHand());
			System.out.println(printList(myCard));
		}
		return null;//myCard;
	}
	
	
	private Colors getColorsInput() //return color value from buttons input.
	{
		/*
		 * 
		 * change scanner line to raspbery input method of buttons!!!!!!!!!!!
		 * 
		 * */
		Colors color=Colors.valueOf((new Scanner(System.in).nextLine()).toUpperCase());
		
		return color;
		
	}
	
	
	
	
	public LinkedList<Card> copyList(LinkedList<Card> list){
		LinkedList<Card> newList=new LinkedList<Card>();
		for(ListIterator<Card> iter=list.listIterator();iter.hasNext();){
			Card card=iter.next();
			newList.addLast(card);
			}
		return newList;
		
	}
	public String printList(LinkedList<Card> list) //method witch get a list and print it
	{
		String str="list:\n";
		LinkedList<Card> str2=copyList(list);
		while(!str2.isEmpty())
		{
			str=str+str2.getFirst().toString()+"   ,";
			str2.removeFirst();
		}
		return str;
	}
	public String toString()
	{
		return this.myDeck.toString();
	}
	
	
	private int NumberOfCardsOfColor(LinkedList<Card> cardList,Colors color)
	{
		int count=0;
		for (Card current : cardList) 
		{
			if ((current instanceof numberCard&&((numberCard)current).getColor().equals(color))||((current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(color)))) count++;
		}
		return count;
	}
	
	
	
}
