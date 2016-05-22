package Cards;

public class specialCardNoColor extends Card {

	private Colors CurrentColor;
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

	@Override
	public String toString() {
		return "specialCardNoColor [type=" + type + "]";
	}
	
	public void SetCurrentColor(Colors c)
	{
		this.CurrentColor=c;
	}
	
	public Colors GetCurrentColor()
	{
		return this.CurrentColor;
	}
	
}
