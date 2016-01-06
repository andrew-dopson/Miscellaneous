package CommuterTrain;




public class Train {
	final int STOP = 0;
	final int EMERGENCY = 1;
	final int CRUISE = 2;
	final int ACCEL = 3;
	final int DECEL = 4;
	final int ON = 1;
	final int OFF = 0;
	final int FAILED = 5;
	final int NONE = 10;
	final static long difference = 500;
	
	
	private int STATUS;
	private int engine = ON;
	private int currentSpeed = 45;
	private boolean emergencyStopRequest = false;
	private boolean stopRequest = false;
	private int phaseIBrakes = OFF;
	private int phaseIIBrakes = OFF;
	private int maxSpeed = 60;
	private int minSpeed = 30;
	private boolean locationNear = false;
	
	public Train(){
		STATUS = NONE;
	}
	
	public void controlSpeed(){
		if(engine == FAILED){
			waitAHalfSec();
			System.out.println("ENGINE FAILURE!\n");
			emergencyStop();
		}
		
		if(emergencyStopRequest){
			waitAHalfSec();
			System.out.println("EMERGENCY STOP NEEDED.\n");
			STATUS = EMERGENCY;
		}
		else if(stopRequest){
			if(locationNear){
				waitAHalfSec();
				System.out.println("STOP REQUESTED.\n");
				STATUS = STOP;
			}else{
				waitAHalfSec();
				System.out.println("NO LOCATION TO STOP AT. TRAIN WILL STOP AT NEXT LOCATION.\n");
			}
				
		}
		if(STATUS == NONE){
			if(currentSpeed < minSpeed)
				STATUS = ACCEL;
			else if(currentSpeed > maxSpeed)
				STATUS = DECEL;
			else
				STATUS = CRUISE;
		}
		
		switch(STATUS){
			case STOP:
				System.out.println("ENGINE DISENGAGED.\n");
				engine = OFF;
				engageBrakes();
				while(currentSpeed != 0){
					decelerate();
				}
				waitAHalfSec();
				System.out.println("ARRIVED AT DESTINATION!\n");
				break;
				
			case EMERGENCY:
				System.out.println("ENGINE DISENGAGED.\n");
				engine = OFF;
				engageBrakes();
				while(currentSpeed > 0){
					decelerate();
				}
				waitAHalfSec();
				System.out.println("EMERGENCY STOP SUCCESSFUL!\n");
				break;
				
			case CRUISE:
				waitAHalfSec();
				System.out.println("TRAIN IS IN CRUISE MODE AT " + currentSpeed + " MPH.\n");
				break;
			case ACCEL:
				waitAHalfSec();
				System.out.println("TRAIN AT UNSAFE SPEED.  ACCELERATING TO THRESHOLD SPEED.\n");
				disengageBrakes();
				System.out.println("ENGINE ENGAGED.\n");
				engine = ON;
				while(currentSpeed != 45){
					accelerate();
				}
				waitAHalfSec();
				System.out.println("TRAIN NOW AT SAFE THRESHOLD SPEED OF: " + currentSpeed + " MPH.\n");
				break;
				
			case DECEL:
				waitAHalfSec();
				System.out.println("TRAIN AT UNSAFE SPEED.  DECELERATING TO THRESHOLD SPEED.\n");
				engine = OFF;
				System.out.println("ENGINE DISENGAGED.\n");
				engageBrakes();
				while(currentSpeed != 45){
					decelerate();
				}
				waitAHalfSec();
				System.out.println("TRAIN NOW AT SAFE THRESHOLD SPEED OF: " + currentSpeed + " MPH.\n");
				break;
		}
	}
	
	public void setSpeed(int value){
		currentSpeed = value;
	}
	
	public int getSpeed(){
		return currentSpeed;
	}
	
	public void accelerate(){
		currentSpeed += 1;
		System.out.println("Current Speed: " + currentSpeed);
		waitAHalfSec();
	}
	
	public void decelerate(){
		if(STATUS == EMERGENCY){
			if(currentSpeed < 5)
				currentSpeed = 0;
			else 
				currentSpeed -= 5;
		}
		else
			currentSpeed -= 1;
		
		System.out.println("Current Speed: " + currentSpeed);
		waitAHalfSec();
		
	}
	
	public void stopRequest(){
		stopRequest = true;
	}
	
	public void emergencyStop(){
		emergencyStopRequest = true;
	}
	
	public void disengageBrakes(){
		phaseIBrakes = OFF;
		phaseIIBrakes = OFF;
		waitAHalfSec();
		System.out.println("BRAKES DISENGAGED.\n");
	}
	
	public void engageBrakes(){
		phaseIBrakes = ON;
		waitAHalfSec();
		System.out.println("PHASE I BRAKES ENGAGED.\n");
		if(STATUS == EMERGENCY){
			phaseIIBrakes = ON;
			System.out.println("PHASE II BRAKES ENGAGED.\n");
		}
	}
	
	public void locationNear(){
		locationNear = true;
	}
	
	public void engineFailure(){
		engine = FAILED;
	}
	
	public static void waitAHalfSec(){
		try {
			Thread.sleep(difference);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
