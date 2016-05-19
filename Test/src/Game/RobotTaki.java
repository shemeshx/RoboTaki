package Game;

import java.awt.Color;
import java.awt.List;
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
			avaiableCards=this.getAvailableBySpecialColorCard((specialCardColor)card);
			System.out.println(printList(avaiableCards));
		}
		else if(card instanceof specialCardNoColor)
		{
			avaiableCards=this.getAvailableByNoColorCard((specialCardNoColor)card);
		}
		else if(card instanceof numberCard) //if the card is number card
		{
			avaiableCards =this.getAllAvailableCardsByNumberCard((numberCard)card);
			System.out.println(printList(avaiableCards));
		}
		if(avaiableCards==null)
		{ 
			myCard=null; //if can;t return card (skip the turn)
			System.out.println("NO CARDS AVAILABLE");
		}
		else //find the best card to return
		{

			myCard=this.findCardToReturn(avaiableCards,card);
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
	
	private LinkedList<Card >getAvailableBySpecialColorCard(specialCardColor sCard) //return list of card witch can put on speicialNoColor card
	{
		if(sCard.getType().equals(cardType.STOP))
		{
			//TODU Skip the turn
			return null;
		}
		else 
		{
			return getAvailableCards(sCard);
		}
	}
	
	private LinkedList<Card >getAvailableByNoColorCard(specialCardNoColor sCard) //return list of card witch can put on speicialNoColor card
	{
		if (sCard.getType().equals(cardType.COLOR)||sCard.getType().equals(cardType.SUPER))
		{
			//after this card is put by the user, the RoboTaki ask to choose color with button
			//TODO get color by click on button
			return this.getAvailableCardsByColor(this.getColorsInput());///get the color from input by buttons 
		}
		return null;
	}
	private LinkedList<Card >getAllAvailableCardsByNumberCard(numberCard nCard) //return list of card witch can put on numberCArd card
	{
		if(nCard.getNumber()==2) //if the number is 2 PLUS
		{
			//TODO Get 2 cards from the board
			return null;
		}
		else //regular number card
		{
			return getAvailableCards(nCard); //get all the cards wich can put on this card
		}
	}
	
	private LinkedList<Card> getAvailableCardsByColor(Colors color) //Get all the cards from this color and SuperTaki and Change Color
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
		
		return availableCards;
	}
	
	private LinkedList<Card> getAvailableCards(Card card) //return linked list with all the cards tan can place on the corrent ard
	{
		LinkedList<Card> availableCards=new LinkedList<Card>(); //new list to storw all the available cards
		LinkedList<Card> temp = copyList(myDeck.getHand()); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty()){ //check for each card in the deck what it's type
		Card current = temp.getFirst();
		temp.removeFirst();
			if(current instanceof numberCard) //if the current cad is number card
			{
				numberCard nCard=(numberCard) current;
				if(((card instanceof numberCard)&&(nCard.getColor().equals(((numberCard)card).getColor())||nCard.getNumber()==((numberCard)card).getNumber()))
						||((card instanceof specialCardColor)&&(((specialCardColor)card).getColor().equals(nCard.getColor()))))
				{
					availableCards.add(nCard);
				}
				else continue;
			}
			
			else if(current instanceof specialCardColor) //if the correct card is special card
			{
				specialCardColor sCard=(specialCardColor) current;
				if(((card instanceof specialCardColor)&&((sCard.getColor().equals(((specialCardColor)card).getColor()))||(sCard.getType()==((specialCardColor)card).getType())))
						||((card instanceof numberCard)&&(((numberCard)card).getColor().equals(sCard.getColor()))))
				{
					availableCards.add(sCard);
				}
				else continue;
			}
			
			else if(current instanceof specialCardNoColor)
				availableCards.add(((specialCardNoColor)current));
			
		}
	
		
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
	
	
	private LinkedList<Card> findCardToReturn(LinkedList<Card> availableCardsList, Card card)
	{
		LinkedList<Card> temp=new LinkedList<Card>();
		if(FindFirstTaki(availableCardsList)!=null)temp= this.ReturnCardsByColorTaki(availableCardsList);
		else if(FindFirstPlus(availableCardsList)!=null)temp= this.ReturnCardsByPlus(availableCardsList);
		return temp;
	}
	
	private LinkedList<Card> ReturnCardsByColorTaki(LinkedList<Card> AvailableCards)
	{
		LinkedList<Card> cardList=new LinkedList<Card>();
		specialCardColor Taki=this.FindFirstTaki(AvailableCards);
		cardList.addFirst(Taki);
		LinkedList<Card> temp = copyList(AvailableCards); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof numberCard&&((numberCard)current).getColor().equals(Taki.getColor())) cardList.addLast(current);
			else continue;
			
		}
		temp = copyList(AvailableCards); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(Taki.getColor())) 
				if(current==Taki) continue;
				else cardList.addLast(current);
			else continue;
			
		}
		System.out.println("BOOM "+cardList);
		return cardList;
	}
	
	
	private specialCardColor FindFirstTaki(LinkedList<Card> availableCards)
	{
		LinkedList<Card> temp = copyList(availableCards); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof specialCardColor&&((specialCardColor)current).getType().equals(cardType.TAKI)) return (specialCardColor) current;
			else continue;
			
		}
		return null;
	}
	
	private specialCardColor FindFirstPlus(LinkedList<Card> AvailableCards)
	{
		LinkedList<Card> temp = copyList(AvailableCards); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof specialCardColor&&((specialCardColor)current).getType().equals(cardType.PLUS)&&this.NumberOfCardsOfColor(AvailableCards,((specialCardColor)current).getColor())>1) return (specialCardColor) current;
			else continue;
			
		}
		return null;
	}
	
	private LinkedList<Card> ReturnCardsByPlus(LinkedList<Card> AvailableCards)
	{
		LinkedList<Card> cardList=new LinkedList<Card>();
		specialCardColor Plus=this.FindFirstPlus(AvailableCards);
		cardList.addFirst(Plus);
		//System.out.println(Plus);
		LinkedList<Card> temp = copyList(AvailableCards); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof numberCard&&((numberCard)current).getColor().equals(Plus.getColor())) 
			{
			 cardList.addLast(current);
			 return cardList;
			}
			else continue;
			
		}
		temp = copyList(AvailableCards); //copy the cards in the deck hand to new temp list
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(Plus.getColor())) 
				if(current==Plus) continue;
				else 
				{
					cardList.addLast(current);
					return cardList;
				}
			else continue;
			
		}
		System.out.println("BOOM "+cardList);
		return cardList;
	}
	
	private int NumberOfCardsOfColor(LinkedList<Card> cardList,Colors color)
	{
		LinkedList<Card> temp = copyList(cardList); //copy the cards in the deck hand to new temp list
		int counter=0;
		while (!temp.isEmpty())
		{ //check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
			if(current instanceof numberCard&&((numberCard)current).getColor().equals(color)) counter++;
			else if (current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(color)) counter++;
		}
		return counter;
	}
	
	
	
	
}
