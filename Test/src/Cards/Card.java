package Cards;
/*
 * card is abstract class. This class contain type of the card of TAKI game.
 * The type of the card is represented by the enum class - cardType.
 * 
 */
public abstract class Card {
	protected cardType type;
	
	public abstract cardType getType();

	public abstract void setType(cardType type);
	
	
	
}
