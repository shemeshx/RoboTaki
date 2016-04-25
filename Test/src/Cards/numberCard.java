package Cards;

public class numberCard extends Card{

	private int number;
	
	
	public numberCard(int number) {
		this.setType(cardType.NUMBER);
		this.number = number;
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

}
