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
		String []str={"8 RED","SWITCH RED","PLUS RED","COLOR","2 YELLOW","TAKI YELLOW","PLUS RED","5 BLUE"};
		RobotTaki r=new RobotTaki(str);
		
		//r.playTurn(new specialCardNoColor(cardType.SUPER));
		//r.playTurn(new numberCard(4,Colors.RED));
		r.playTurn(new specialCardColor(cardType.STOP,Colors.RED));
		
	}

}
