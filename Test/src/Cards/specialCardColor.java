package Cards;

public class specialCardColor extends Card{
	
	private Colors color;
	public specialCardColor(cardType type,Colors color) {
		this.type=type;
		this.color=color;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	@Override
	public cardType getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public void setType(cardType type) {
		// TODO Auto-generated method stub
		this.type=type;
	}

	@Override
	public String toString() {
		return "specialCardColor [color=" + color + ", type=" + type + "]";
	}
	
}
