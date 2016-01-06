/*Andrew Dopson
  Lab2 - CS 111
  Section 002
  08/27/2012 */



import java.util.Scanner;

public class dopson2
{
    public static void main(String[] args) 
    {
	int input1 = 0;
	int input2 = 0;
	String exit = "";
	Scanner input = new Scanner(System.in);
       	String selection = "";


	while (exit != "exit")
	{
	    System.out.println("Please select an operation:\nA) Add\nB) Subtract\n" + 
			       "C) Multiply\nD) Divide\nOr type 'exit' to exit.");
	    selection = input.nextLine();
	    
	    
	    if (selection.equals("A"))
	    {
		System.out.println("Input the first integer:");
		input1 = input.nextInt();
		System.out.println("Input the second integer:");
		input2 = input.nextInt();

		System.out.println("The final result is: " + add(input1, input2));
	    }
	    else if (selection.equals("B"))
	    {
		System.out.println("Input the first integer:");
		input1 = input.nextInt();
		System.out.println("Input the second integer:");
		input2 = input.nextInt();

		System.out.println("The final result is: " + sub(input1, input2));
	    }
	    else if (selection.equals("C"))
	    {
		System.out.println("Input the first integer:");
		input1 = input.nextInt();
		System.out.println("Input the second integer:");
		input2 = input.nextInt();

		System.out.println("The final result is: " + mult(input1, input2));
	    }
	    else if (selection.equals("D"))
	    {
		System.out.println("Input the first integer:");
		input1 = input.nextInt();
		System.out.println("Input the second integer:");
		input2 = input.nextInt();

		System.out.println("The final result is: " + div(input1, input2));
	    } 
            else if (selection.equals("exit"))
	    {
		exit = "exit";
	    }
	}

    }

    public static int add(int input1, int input2)
    {
	return input1 + input2;
    }

    public static int sub(int input1, int input2)
    {
	return input1 - input2;
    }

    public static int mult(int input1, int input2)
    {
	return input1 * input2;
    }

    public static double div(int input1, int input2)
    {
	return input1 / input2;
    }
}
