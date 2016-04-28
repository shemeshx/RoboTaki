package Game;

import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import Cards.Card;
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
	public Card playTurn(Card card)
	//this method gets a card on the heap that the player put. the method turns a card.
	{
		
		Card myCard = null; //card to pop from the dackhand
		LinkedList<Card>avaiableCards=getAvailableCards(card); //get all the cards wich can put on this card
		return myCard;
	}
	public LinkedList<Card> getAvailableCards(Card card) //return linked list with all the cards tan can place on the corrent ard
	{
		LinkedList<Card> availableCards=new LinkedList<Card>(); //new list to storw all the available cards
		LinkedList<Card> temp = copyList(myDeck.getHand()); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty()){ //check for each card in the deck what it's type
		Card current = temp.getFirst();
		temp.removeFirst();
			if(current instanceof numberCard) //if the corrrent cad is number card
			{
				if(card.getType().equals(current.getType())) 
				{ //if also the checked card is number card
					numberCard nCard=(numberCard) current;
					if(nCard.getColor().equals(((numberCard)card).getColor()))
					{
						availableCards.add(nCard);
					}
					else 
						if (nCard.getNumber()==((numberCard)card).getNumber())
							availableCards.add(nCard);
						else if (card instanceof specialCardColor && ((specialCardColor)card).getColor().equals(nCard.getColor()))
								availableCards.add(nCard);
				}
				else continue;
			}
			
			if(current instanceof specialCardColor) //if the corrent card is special card
			{
				if(card.getType().equals(current.getType())){
					specialCardColor sCard=(specialCardColor) current;
					availableCards.add(sCard);
				}else if (card instanceof numberCard && ((numberCard)card).getColor().equals(((specialCardColor)current).getColor()))
				{
					specialCardColor sCard=(specialCardColor) current;
					availableCards.add(sCard);
				}
					else continue;
			}
			
			if(current instanceof specialCardNoColor)
				availableCards.add(((specialCardNoColor)current));
			
		}
		System.out.println(printList(myDeck.getHand()));
		System.out.println(printList(availableCards));
		
		return availableCards;
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
		String str="hand:\n";
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
	
}
