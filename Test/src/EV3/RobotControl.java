package EV3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Locale.LanguageRange;
import java.util.concurrent.BrokenBarrierException;

import lejos.ev3.tools.EV3Control;
import lejos.ev3.tools.LCDDisplay;
import lejos.hardware.BrickFinder;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.lcd.LCDOutputStream;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;
import lejos.remote.ev3.RemoteRequestEV3;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class RobotControl {
	

	private static int count=0 ;
	private static RemoteRequestEV3 ev3;
	private static RegulatedMotor m = null;
	private static RegulatedMotor m2 = null;
	private static RegulatedMotor m3 = null;
	private static RegulatedMotor m4= null;
	public RobotControl() throws RemoteException, MalformedURLException, NotBoundException {
		// TODO Auto-generated constructor stub
		
	}
	public void lastCardPop() throws InterruptedException
	{
			
			
			try {
				
				if(count==0){
				ev3 = new RemoteRequestEV3("10.0.1.1");
				ev3.setDefault();
				m =ev3.createRegulatedMotor("C",'L');//buttom motor
				m2= ev3.createRegulatedMotor("B",'L');//top motor
				m3 =ev3.createRegulatedMotor("A", 'M');
				m4= ev3.createRegulatedMotor("D", 'L');
				count++;
				}
				Sound.beep();
				m.setSpeed(100);
				m3.setSpeed(200);
				m4.setSpeed(20);
				int i=0;
			//	while (i++<1){
				m.rotate(230);	
				//if (i!=20){
					m.forward();
					m2.backward();
					m3.backward();
					Thread.sleep(900);
					m.flt();
					m2.flt();
					m3.flt();
				//}
				//}
				
				//m4.rotate(-11);
				//m4.flt();
				/*
				//m3.rotate(-35);
				m3.rotate(100);
				m2.rotate(-100);
				Delay.msDelay(100);
				m3.flt();
				}
				*/
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							
				
			
			
				
	}

	public void CardPop(int num) throws InterruptedException
	{
			
			
			try {
				
				if(count==0){
				ev3 = new RemoteRequestEV3("10.0.1.1");
				ev3.setDefault();
				m =ev3.createRegulatedMotor("C",'L');//buttom motor
				m2= ev3.createRegulatedMotor("B",'L');//top motor
				m3 =ev3.createRegulatedMotor("A", 'M');//pop
				count++;
				}
				Sound.beep();
				m.setSpeed(100);
				m3.setSpeed(200);
				int i=0;
				
				while (i++<num){
					m.startSynchronization();
					m3.startSynchronization();
					m2.startSynchronization();
					
					m.rotate(200);
					m.rotate(-230);
					m.flt();
					//m3.rotate(-200);
					Thread.sleep(200);
					m.endSynchronization();
					m3.setSpeed(20);
					m3.setSpeed(300);
					m3.rotate(-25);
									
					cardON(); 
					m.backward();
					m2.backward();
					m3.forward();
					Thread.sleep(300);
					m.stop();
					m3.stop();
					m2.stop();
					cardOFF();
					m3.endSynchronization();
					m2.endSynchronization();
					m4.endSynchronization();
					m3.flt();
					m4.flt();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							
			
				
	}
	public void cardON()
	{
		
		
		ev3.setDefault();
		
		m4= ev3.createRegulatedMotor("D", 'L');//raise card
		m4.startSynchronization();
		m4.setSpeed(20);
		System.out.println("Boom");
		m4.rotate(-25);
		m4.endSynchronization();
	}
	public void cardOFF()
	{
		
		ev3.setDefault();
		m4= ev3.createRegulatedMotor("D", 'L');//raise card
		
		m4.startSynchronization();
		m4.setSpeed(20);
		
		
		m4.rotate(25);
		m4.endSynchronization();
	}
	
	
}
