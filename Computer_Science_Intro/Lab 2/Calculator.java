/*Andrew Dopson
  Lab2 - CS 111
  Section 002
  08/27/2012 */



import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args) 
    {
	int input1 = 0;
	int input2 = 0;
	String exit == "";
	Scanner input = new Scanner(System.in);
       	String selection = "";


	while (exit =! "exit")
	{
	    System.out.println("Please select an operation:\nA) Add\nB) Subtract\n"
			      "C) Multiply\nD) Divide\nOr type 'exit' to exit.")
	    selection = input.next();
	    
	    System.out.println("Input the first integer:");
	    input1 = input.nextInt();
	    System.out.println("Input the second integer:");
	    input2 = input.nextInt();

	    if (selection == 'A')
	    {
		System.out.println(add(input1, input2));
	    }
	    else if (selection == 'B')
	    {
		System.out.println(sub(input1, input2));
	    else if (selection == 'C')
	    {
		System.out.println(mult(input1, input2));
	    }
	    else if (selection == 'D')
	    {
		System.out.prinltn(div(input1, input2));
	    } 
	    else if (selection == 'exit')
	    {
		exit == "exit";
	    }
        }

    }

    public static int add(int input1, int input2)
    {
	return (input1 + input2);
    }

    public static int sub(int input1, int input2)
    {
	return (input1 - input2);
    }

    public static int mult(int input1, int input2)
    {
	return (input1 * input2);
    }

    public static double div(int input1, int input2)
    {
	return (input1 / input2);
    }
}
