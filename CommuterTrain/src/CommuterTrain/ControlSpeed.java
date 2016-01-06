package CommuterTrain;
import java.util.Scanner;

public class ControlSpeed{
	final static long difference = 1000;
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("                                    (@@@)     (@@@@@)");
		System.out.println("                              (@@)     (@@@@@@@)        (@@@@@@@)");
		System.out.println("                         (@@@@@@@)   (@@@@@)       (@@@@@@@@@@@)");
		System.out.println("                    (@@@)     (@@@@@@@)   (@@@@@@)             (@@@)");
	    System.out.println("               (@@@@@@)    (@@@@@@)                (@)");
		System.out.println("           (@@@)  (@@@@)           (@@)");
		System.out.println("        (@@)              (@@@)");
		System.out.println("       .-.               ");
		System.out.println("       ] [    .-.      _    .-----.");
		System.out.println("     .\"   \"\"\"\"   \"\"\"\"\"\" \"\"\"\"| .--`");
		System.out.println("    (:--:--:--:--:--:--:--:-| [___    .------------------------.");
		System.out.println("     |C&O  :  :  :  :  :  : [_9_] |'='|.----------------------.|");
		System.out.println("    /|.___________________________|___|'--.___.--.___.--.___.-'| ");
		System.out.println("   / ||_.--.______.--.______.--._ |---\\'--\\-.-/==\\-.-/==\\-.-/-'/--");
		System.out.println("  /__;^=(==)======(==)======(==)=^~^^^ ^^^^(-)^^^^(-)^^^^(-)^^^ jgs");
		System.out.println("~~~^~~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~~^~~\n");
		
		System.out.println("WELCOME TO THE AUTOMATED COMMUTER TRAIN SYSTEM!");
		System.out.println("\nPress enter to begin the first test run.");
		input.nextLine();
		
		Train train1 = new Train();
		runTrain1(train1);
		waitASec();
		System.out.println("\nPress enter to begin the second test run.");
		input.nextLine();
		Train train2 = new Train();
		runTrain2(train2);
		waitASec();
		System.out.println("\nPress enter to begin the third test run.");
		input.nextLine();
		Train train3 = new Train();
		runTrain3(train3);
		waitASec();
		System.out.println("\nPress enter to begin the fourth test run.");
		input.nextLine();
		Train train4 = new Train();
		runTrain4(train4);
		waitASec();
		System.out.println("\nPress enter to begin the fifth test run.");
		input.nextLine();
		Train train5 = new Train();
		runTrain5(train5);
		waitASec();
		System.out.println("\nPress enter to begin the sixth test run.");
		input.nextLine();
		Train train6 = new Train();
		runTrain6(train6);
		
		waitASec();
		System.out.println("\nEND OF TEST RUNS!!");
	}
	
	//Test for speed below minimum
	public static void runTrain1(Train train){
		System.out.println("Starting Test Run 1...Test for speed below minimum.\n");
		train.setSpeed(20);
		train.controlSpeed();
		System.out.println("End of Test Run 1.");
	}
	
	//Test for speed above maximum
	public static void runTrain2(Train train){
		System.out.println("Starting Test Run 2...Test for speed above maximum.\n");
		train.setSpeed(70);
		train.controlSpeed();
		System.out.println("End of Test Run 2.");
	}
	
	//Test for stop request, but no location available
	public static void runTrain3(Train train){
		System.out.println("Starting Test Run 3...Test for stop request, but no location available.\n");
		train.stopRequest();
		train.controlSpeed();
		System.out.println("End of Test Run 3.");
	}
	
	//Test for stop request, with location available
	public static void runTrain4(Train train){
		System.out.println("Starting Test Run 4...Test for stop request, with location available.\n");
		train.stopRequest();
		train.locationNear();
		train.controlSpeed();
		System.out.println("End of Test Run 4.");
	}
	
	//Test for emergency stop
	public static void runTrain5(Train train){
			System.out.println("Starting Test Run 5...Test for emergency stop.\n");
			train.emergencyStop();
			train.controlSpeed();
			System.out.println("End of Test Run 5.");
		}
	
	//Test for engine failure
		public static void runTrain6(Train train){
				System.out.println("Starting Test Run 6...Test for engine failure.\n");
				train.engineFailure();
				train.controlSpeed();
				System.out.println("End of Test Run 6.");
			}

	public static void waitASec(){
		try {
			Thread.sleep(difference);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

}
