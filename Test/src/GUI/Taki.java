package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints.Key;

import javax.sound.sampled.Port;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.xml.crypto.dsig.keyinfo.KeyValue;

import Cards.Card;
import Cards.Colors;
import Cards.cardType;
import Cards.numberCard;
import Cards.specialCardColor;
import Cards.specialCardNoColor;
import EV3.RobotControl;
import Game.RobotTaki;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.robotics.RegulatedMotor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JButton;

public class Taki {

	private JFrame frame;
	private JTextField GameField;
	private JTextField inputText;
	private String []cards={"8 RED","SWITCH RED","PLUS RED","COLOR","2 YELLOW","TAKI RED","PLUS RED","5 BLUE"};
	private JButton btnYellow;
	private JButton btnBlue;
	private JButton btnRed;
	private JButton btnGreen;
	private RobotControl rc;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Taki window = new Taki();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Taki() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 715, 452);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		GameField = new JTextField();
		GameField.setHorizontalAlignment(SwingConstants.LEFT);
		GameField.setEditable(false);
		GameField.setBounds(67, 24, 281, 127);
		frame.getContentPane().add(GameField);
		GameField.setColumns(10);
		GameField.setText("Hello and wellcome to Taki game!\n");
		RobotTaki robot = new RobotTaki(cards);
		try {
			rc=new RobotControl();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inputText = new JTextField();
		inputText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode()==arg0.VK_ENTER){
//					GameField.setText(robot.playTurn(new specialCardNoColor(cardType.COLOR)).toString());
					changeColorAppear(true);
					try {
						new RobotControl().CardPop(3);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				}
			}
		});
		inputText.setBounds(67, 182, 281, 22);
		frame.getContentPane().add(inputText);
		inputText.setColumns(10);
		
		btnGreen = new JButton("");
		btnGreen.setBackground(new Color(0, 128, 0));
		btnGreen.setBounds(422, 124, 54, 57);
		frame.getContentPane().add(btnGreen);
		
		btnYellow = new JButton("");
		btnYellow.setBackground(new Color(255, 255, 0));
		btnYellow.setBounds(422, 237, 54, 57);
		frame.getContentPane().add(btnYellow);
		
		btnBlue = new JButton("");
		btnBlue.setBackground(new Color(30, 144, 255));
		btnBlue.setBounds(446, 181, 54, 57);
		frame.getContentPane().add(btnBlue);
		
		btnRed = new JButton("");
		btnRed.setBackground(Color.RED);
		btnRed.setBounds(392, 181, 54, 57);
		frame.getContentPane().add(btnRed);
		
		changeColorAppear(false);
	}
	public void changeColorAppear(boolean enable)
	{
		if(enable)
		{
			btnGreen.setVisible(true);
			btnRed.setVisible(true);
			btnYellow.setVisible(true);
			btnBlue.setVisible(true);
		
		}
		else
		{
			btnGreen.setVisible(false);
			btnRed.setVisible(false);
			btnYellow.setVisible(false);
			btnBlue.setVisible(false);
		
		}
	
	}
}
