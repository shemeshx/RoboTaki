package Game;

import java.awt.Color;
import java.awt.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

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
		
		if(card instanceof specialCardColor) //if the card is special card
		{
			specialCardColor sCard=(specialCardColor)card;
			if(sCard.getType().equals(cardType.STOP))
			{
				//TODU Skip the turn
				avaiableCards=null;
			}
		}
		else if(card instanceof specialCardNoColor)
		{
			
			avaiableCards=this.getAvailableByColorCard(card);
		}
		else if(card instanceof numberCard) //if the card is number card
		{
			avaiableCards =this.getAllAvailableCardsByNumberCard(card);
		}
		
		if(avaiableCards==null) myCard=null; //if can;t return card (skip the turn)
		else //find the best card to return
		{
			myCard=this.findCardToReturn(avaiableCards);
		}
		return myCard;
	}
	
	private LinkedList<Card> findCardToReturn(LinkedList<Card> availableCardsList)
	{
		LinkedList<Card> temp=null;
		
		return temp;
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
	
	private LinkedList<Card >getAvailableByColorCard(Card card) //return list of card witch can put on speicialNoColor card
	{
		specialCardNoColor sCard=(specialCardNoColor)card;
		if (sCard.getType().equals(cardType.COLOR))
		{
			//after this card is put by the user, the RoboTaki ask to choose color with button
			//TODO get color by click on button
			return this.getAvailableCardsByColor(this.getColorsInput());///get the color from input by buttons 
		}
		return null;
	}
	private LinkedList<Card >getAllAvailableCardsByNumberCard(Card card) //return list of card witch can put on numberCArd card
	{
		numberCard nCard=(numberCard)card;
		if(nCard.getNumber()==2) //if the number is 2 PLUS
		{
			//TODO Get 2 cards from the board
			return null;
		}
		else //regular number card
		{
			return getAvailableCards(card); //get all the cards wich can put on this card
		}
	}
	
	public LinkedList<Card> getAvailableCardsByColor(Colors color) //Get all the cards from this color and SuperTaki and Change Color
	{
		LinkedList<Card> availableCards=new LinkedList<Card>(); //new list to storw all the available cards
		LinkedList<Card> temp = copyList(myDeck.getHand()); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty()) 
		{ //check for each card in the deck what it's type
			
			Card current = temp.getFirst(); //current checked card
			temp.removeFirst();
			
			if(current instanceof numberCard) //if this card is number card
			{
				numberCard nCard=(numberCard)current;
				if(nCard.getColor().equals(color)) availableCards.add(current); //add to the list if it is color is the same
			}
			else if(current instanceof specialCardColor) //if this card it is spical card with color
			{
				specialCardColor sCard=(specialCardColor)current;
				if(sCard.getColor().equals(color)) availableCards.add(current); //chheck if the colors is same
			}
			else if(current instanceof specialCardNoColor) //if the card is Super Taki or Change Color
			{
				availableCards.add(current); //if it is add it to the list
			}
		}
		System.out.println(printList(myDeck.getHand()));
		System.out.println(printList(availableCards));
		return availableCards;
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
