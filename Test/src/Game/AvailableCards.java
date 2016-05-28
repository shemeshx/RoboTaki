package Game;

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import Cards.Card;
import Cards.Colors;
import Cards.cardType;
import Cards.numberCard;
import Cards.specialCardColor;
import Cards.specialCardNoColor;

public class AvailableCards {
	
	public static LinkedList<Card >getAvailableBySpecialColorCard(specialCardColor sCard,LinkedList<Card> myDeck) //return list of card witch can put on speicialNoColor card
	{
		//if(sCard.getType().equals(cardType.STOP))
		//{
			//TODU Skip the turn
			//return null;
		//}
		//else 
		//{
			return getAvailableCards(sCard,myDeck);
		//}
	}
	
	public static LinkedList<Card >getAvailableByNoColorCard(specialCardNoColor sCard,LinkedList<Card> myDeck) //return list of card witch can put on speicialNoColor card
	{
		if (sCard.getType().equals(cardType.COLOR)||sCard.getType().equals(cardType.SUPER))
		{
			//after this card is put by the user, the RoboTaki ask to choose color with button
			//TODO get color by click on button
			return getAvailableCardsByColor(getColorsInput(),myDeck);///get the color from input by buttons 
		}
		return null;
	}
	public static LinkedList<Card >getAllAvailableCardsByNumberCard(numberCard nCard,LinkedList<Card> myDeck) //return list of card witch can put on numberCArd card
	{
		if(nCard.getNumber()==2) //if the number is 2 PLUS
		{
			//TODO Get 2 cards from the board
		    LinkedList<Card> temp=new LinkedList<Card>();
		    temp.addLast(Search2Plus(myDeck));
			return null;
		}
		else //regular number card
		{
			return getAvailableCards(nCard,myDeck); //get all the cards wich can put on this card
		}
	}
	
	public static LinkedList<Card> getAvailableCardsByColor(Colors color,LinkedList<Card> myDeck) //Get all the cards from this color and SuperTaki and Change Color
	{
		LinkedList<Card> availableCards=new LinkedList<Card>(); //new list to storw all the available cards
		LinkedList<Card> temp = copyList(myDeck); //copy the cards in the deck hand to new temp list
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
	
	public static LinkedList<Card> getAvailableCards(Card card,LinkedList<Card> myDeck) //return linked list with all the cards tan can place on the corrent ard
	{
		LinkedList<Card> availableCards=new LinkedList<Card>(); //new list to storw all the available cards
		LinkedList<Card> temp = copyList(myDeck); //copy the cards in the deck hand to new temp list
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
	
	public static Colors getColorsInput() //return color value from buttons input.
	{
		/*
		 * 
		 * change scanner line to raspbery input method of buttons!!!!!!!!!!!
		 * 
		 * */
		Colors color=Colors.valueOf((new Scanner(System.in).nextLine()).toUpperCase());
		
		return color;
		
	}
	public static LinkedList<Card> copyList(LinkedList<Card> list){
		LinkedList<Card> newList=new LinkedList<Card>();
		for(ListIterator<Card> iter=list.listIterator();iter.hasNext();){
			Card card=iter.next();
			newList.addLast(card);
			}
		return newList;
		
	}
	
	public static numberCard Search2Plus(LinkedList<Card> availableCard)
	{
		LinkedList<Card> temp = copyList(availableCard); //copy the cards in the deck hand to new temp list
		int counter=0;
		while (!temp.isEmpty())
		{ 	//check for each card in the deck what it's type
			Card current = temp.getFirst();
			temp.removeFirst();
			
				if(current instanceof numberCard&&((numberCard)current).getNumber()==2)) return (numberCard)current;
			else continue;
		}
		return null;
	}
	
	
}
