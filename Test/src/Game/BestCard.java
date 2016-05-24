package Game;

import java.util.LinkedList;
import java.util.ListIterator;

import Cards.Card;
import Cards.Colors;
import Cards.cardType;
import Cards.numberCard;
import Cards.specialCardColor;
import Cards.specialCardNoColor;

public class BestCard {

	public static LinkedList<Card> findCardToReturn(LinkedList<Card> availableCardsList, Card card,LinkedList<Card> myDeck)
	{
		if(availableCardsList.size()==0) return new LinkedList<Card>();
		else if(HaveSpecialOneColorCardWithCards(availableCardsList,cardType.TAKI,myDeck)!=null)return ReturnCardsByColorTaki(availableCardsList,myDeck);
		else if(HaveSpecialOneColorCardWithCards(availableCardsList,cardType.PLUS,myDeck)!=null) return ReturnCardsByPlus(availableCardsList,myDeck);
		else if (ReturnFirst(new numberCard(3, Colors.RED),availableCardsList,myDeck) != null) return ReturnFirst(new numberCard(3, Colors.RED),availableCardsList,myDeck) ;
       return ReturnFirst(new specialCardColor(cardType.PLUS, Colors.BLUE),availableCardsList,myDeck);

	}
	
	public static LinkedList<Card> ReturnCardsByColorTaki(LinkedList<Card> AvailableCards,LinkedList<Card> myDeck)
	{
		LinkedList<Card> cardList=new LinkedList<Card>();
		specialCardColor Taki=HaveSpecialOneColorCardWithCards(AvailableCards,cardType.TAKI,myDeck);
		cardList.addFirst(Taki);
		LinkedList<Card> temp =copyList(AvailableCards); //copy the cards in the deck hand to new temp list
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
		return cardList;
	}
	
	public static LinkedList<Card>  ReturnFirst  (Card type,LinkedList<Card> availableCards,LinkedList<Card> myDeck)
    {
		LinkedList<Card> CardsToReturn = new LinkedList<Card>();
        for (Card card : availableCards) 
        {
        	if (card instanceof specialCardNoColor)
            {
        		 specialCardNoColor NoCard = (specialCardNoColor)card;
                 NoCard.SetCurrentColor(GetMaxColor(myDeck));
                 CardsToReturn.addLast(card);
                 if (NoCard.getType().equals(cardType.COLOR)) return CardsToReturn;
                 else
                 {
                	 for (Card card2 : myDeck)
                     {
                      if(card2 instanceof numberCard&&((numberCard)card2).getColor().equals(NoCard.GetCurrentColor())) CardsToReturn.addLast(card2);
                      else continue;
                     }
                	 for (Card card2 : myDeck)
                     {
                      if(card2 instanceof specialCardColor&&((specialCardColor)card2).getColor().equals(NoCard.GetCurrentColor())) CardsToReturn.addLast(card2);
                      else continue;
                     }
                	 return CardsToReturn;
                 }
            }
        	
        	else if(type.getClass().isInstance(card))
        	{
        		CardsToReturn.addLast(card);
        		return CardsToReturn;
        	}
        }
        return null;
    }
	
	public static Colors GetMaxColor(LinkedList<Card> myDeck)
	    {
	        int Red = 0, Green = 0, Yellow = 0, Blue = 0;
	        for (Card current :myDeck) 
	        {
	            if (current instanceof numberCard||current instanceof specialCardColor) //check the current card coloe
	            {
	                if ((current instanceof numberCard&&((numberCard)current).getColor().equals(Colors.BLUE))||((current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(Colors.BLUE)))) Blue++;
	                else if ((current instanceof numberCard&&((numberCard)current).getColor().equals(Colors.GREEN))||((current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(Colors.GREEN)))) Green++;
	                else if ((current instanceof numberCard&&((numberCard)current).getColor().equals(Colors.RED))||((current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(Colors.RED)))) Red++;
	                else if ((current instanceof numberCard&&((numberCard)current).getColor().equals(Colors.YELLOW))||((current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(Colors.YELLOW)))) Yellow++;
	            }
	        }
	        //return the color with the max number of cards
	        if (Blue >= Red && Blue >= Yellow && Blue >= Green) return Colors.BLUE; 
	        else if (Yellow >= Red && Yellow >= Blue && Yellow >= Green) return Colors.YELLOW;
	        else if (Green >= Red && Green >= Blue && Green >= Yellow) return Colors.GREEN;
	        else return Colors.RED;
	    }
	
	public static specialCardColor HaveSpecialOneColorCardWithCards(LinkedList<Card> availableCards,cardType type,LinkedList<Card> myDeck)
	{
		for (Card card : availableCards) 
		{
			if(card instanceof specialCardColor&&((specialCardColor)card).getType().equals(type)&&NumberOfCardsOfColor(myDeck, ((specialCardColor)card).getColor())>1) return (specialCardColor)card;
		}
		return null;
	}
	
	public static LinkedList<Card> ReturnCardsByPlus(LinkedList<Card> AvailableCards,LinkedList<Card> myDeck)
	{
		LinkedList<Card> cardList=new LinkedList<Card>();
		specialCardColor Plus=HaveSpecialOneColorCardWithCards(AvailableCards,cardType.PLUS,myDeck);
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
		return cardList;
	}
	
	public static int NumberOfCardsOfColor(LinkedList<Card> cardList,Colors color)
	{
		int count=0;
		for (Card current : cardList) 
		{
			if ((current instanceof numberCard&&((numberCard)current).getColor().equals(color))||((current instanceof specialCardColor&&((specialCardColor)current).getColor().equals(color)))) count++;
		}
		return count;
	}
	
	public static LinkedList<Card> copyList(LinkedList<Card> list){
		LinkedList<Card> newList=new LinkedList<Card>();
		for(ListIterator<Card> iter=list.listIterator();iter.hasNext();){
			Card card=iter.next();
			newList.addLast(card);
			}
		return newList;
		
	}
	
}
