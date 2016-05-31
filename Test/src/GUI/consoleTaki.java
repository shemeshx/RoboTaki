package GUI;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import Cards.Card;
import Cards.Colors;
import Cards.cardType;
import Cards.numberCard;
import Cards.specialCardColor;
import Cards.specialCardNoColor;
import Game.RobotTaki;

public class consoleTaki {

	private static RobotTaki robot;
	private static int numofcards;
	private static boolean robotTurn;
	public consoleTaki() {
		begin();
		playturn();
	}

	public static void playturn()
	{
	
		robotTurn=false;
		while(numofcards!=0){
			if(!robotTurn){
	
				System.out.println("please put card on the heap\n");
				String cardtemp=new Scanner(System.in).nextLine();
				
				switch (cardtemp) {
				case "COLOR":
						System.out.println("please enter color\n");
						Colors color = Colors.valueOf(new Scanner(System.in).nextLine()); 
						System.out.println(robot.playTurn(new specialCardNoColor(cardType.COLOR),color));
					break;

				default:
					if(Character.isDigit(cardtemp.charAt(0)))
					{
						int num = Integer.parseInt(cardtemp.substring(0,cardtemp.indexOf(" ")));
						Colors colortemp =Colors.valueOf(cardtemp.substring(cardtemp.indexOf(" ")+1,cardtemp.length()));
						
						System.out.println(robot.playTurn(new numberCard(num, colortemp)));
					}
					else if (cardtemp.indexOf(" ")!=-1)
					{
						cardType type =cardType.valueOf(cardtemp.substring(0,cardtemp.indexOf(" ")));
						Colors colortemp =Colors.valueOf(cardtemp.substring(cardtemp.indexOf(" ")+1,cardtemp.length()));
						System.out.println(robot.playTurn(new specialCardColor(type,colortemp)));
					}else{
						cardType type =cardType.valueOf(cardtemp.substring(0,cardtemp.length()));
						System.out.println(robot.playTurn(new specialCardNoColor(type)));
						
					}
					break;
				}
				
			}
		}
	}
	public static void begin()
	{
		numofcards=0;
		System.out.println("Hello and welcome to TAKI game with our robot!\n\n\n");
		System.out.println("please enter 8 cards to start the game.");
		numofcards=8;
		String[] initCards = {"8 RED"
				,"2 BLUE"
				,"SWITCH RED"
				,"COLOR"
				,"TAKI BLUE"
				,"5 GREEN"
				,"8 YELLOW"
				,"SUPER"};
		robot=new RobotTaki(initCards);
		/*String [] initCards= new String[8];
		while (numofcards<=8){
			for (int i =1;i<=8;i++)
			{
				numofcards++;
				initCards[numofcards-1]=new Scanner(System.in).nextLine();
			}
			robot=new RobotTaki(initCards);
			
		}*/
	}
	public static void main(String[] args) {
		consoleTaki c=new consoleTaki(); 
	}
	

}
