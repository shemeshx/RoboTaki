package Cards;

public class specialCardNoColor extends Card {

	public specialCardNoColor(cardType type) {
		this.type=type;
	}

	@Override
	public cardType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public void setType(cardType type) {
		// TODO Auto-generated method stub
		this.type=type;
	}

}
