import java.awt.Window.Type;

import Cards.Card;
import Cards.Colors;
import Cards.cardType;
import Cards.numberCard;
import Cards.specialCardColor;
import Cards.specialCardNoColor;
import Game.RobotTaki;
import Game.deckHand;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String []str={"8 BLUE","SWITCH RED","2 GREEN","COLOR","SUPER","TAKI RED","PLUS RED","9 RED"};
		RobotTaki r=new RobotTaki(str);
		
		r.playTurn(new specialCardColor(cardType.PLUS,Colors.BLUE));
		
	}

}
