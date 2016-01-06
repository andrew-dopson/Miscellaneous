/**Oskar Otoya
  * CS111 Assignment 2
  * Adult.java
  * */

public class Adult extends Customer
{
  private double SAVINGS_INTEREST = 1.75/365;
  private double CHECK_INTEREST = 1.25/365;
  private double CHECK_CHARGE = 1.25;
  private double OVERDRAFT_PENALTY = 12.5; 

  public Adult(){}
  

  public Adult(String n, String a, String p, String custNum, int custAge)
  {
    super(n,a,p,custNum,custAge);
  }
  

  public double getSavingsInterest(){return SAVINGS_INTEREST;}

  public double getCheckingInterest(){return CHECK_INTEREST;}
  

  public double getCheckCharge(){return CHECK_CHARGE;}
  
  public double getOverDraftPenalty(){return OVERDRAFT_PENALTY;} 
}