/* Andrew Dopson
 * CS111 - Sec002
 * Out of Lab Assignment 3
 * Ool3.java */


import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class Ool3
{
    private static Stack<Character> theStack = new Stack<Character>();
    private static LinkedList<Character> postfix = new LinkedList<Character>();
    private static String postfixExp = "";
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the infix expression you would like to convert: ");
        String expression = input.nextLine();
        convert(expression);
    }
    
    public static void convert(String expression)
    {
         char ch = ' ';
         
         for (int i = 0 ; i < expression.length() - 1; i++)
         {
             ch = expression.charAt(i);
             if (isOperand(ch))
             {
                 postfixExp = postfixExp + ch;
                 
             }
             switch (ch)
             {
                 case '$':
                     break;
                 case '(':  
                     theStack.push(ch);
                     break;
                 case ')':
                     while (theStack.peek() != '(')
                     {
                         postfixExp = postfixExp + theStack.pop();
                         if (theStack.empty())
                             //throw new MismatchedParenException();
                             //System.err.println("Mismatched Parentheses.");
                     }
                     theStack.pop();
                     break;
                 case '+':
                     while (!theStack.empty() && theStack.peek() != '(' && precedence(ch) <= precedence(theStack.peek()))
                     {
                         postfixExp = postfixExp + theStack.pop();
                     }
                     theStack.push(ch);
                     break;
                 case '-':
                     while (!theStack.empty() && theStack.peek() != '(' && precedence(ch) <= precedence(theStack.peek()))
                     {
                         postfixExp = postfixExp + theStack.pop();
                     }
                     theStack.push(ch);
                     break;
                 case '*':
                     while (!theStack.empty() && theStack.peek() != '(' && precedence(ch) <= precedence(theStack.peek()))
                     {
                         postfixExp = postfixExp + theStack.pop();
                     }
                     theStack.push(ch);
                     break;
                 case '/':
                     while (!theStack.empty() && theStack.peek() != '(' && precedence(ch) <= precedence(theStack.peek()))
                     {
                         postfixExp = postfixExp + theStack.pop();
                     }
                     theStack.push(ch);
                     break;
                 default:
                     break;
             }
             
         }
         
         while (!theStack.empty())
         {
             postfixExp = postfixExp + theStack.pop();
         }
         
         for (int i = 0; i < postfixExp.length(); i++)
         {
             postfix.offer(postfixExp.charAt(i));
         }
         
         while(!postfix.isEmpty())
         {
             System.out.print(postfix.remove());
         }
         System.out.println();
    }
    
    public static boolean isOperand(char ch)
    {
        boolean isOp;
        if (ch >= 48 && ch <= 57)
            isOp = true;
        else if (ch >= 65 && ch <= 90)
            isOp = true;
        else if (ch >= 97 && ch <= 122)
            isOp = true;
        else 
            isOp = false;
        
        return isOp;
    }
    
    public static int precedence(char ch)
    {
        if (ch == '/' || ch == '+')
            return 2;
        else
            return 1;
    }
      
      
      
      
      
      
}