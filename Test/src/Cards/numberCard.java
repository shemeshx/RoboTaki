package Cards;
/*
 * this class is about number cards such as 1 - blue, 3 - red etc.....
 */
public class numberCard extends Card{

	private int number;
	private Colors color;
	
	public numberCard(int number ,Colors color ) {
		this.setType(cardType.NUMBER);
		this.number = number;
		this.color=color;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public cardType getType() {
		
		return this.type;
	}

	public void setType(cardType type) {
		this.type=type;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}
	
}
