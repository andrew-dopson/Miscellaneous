/*Oskar Otoya
 * CS 111 Outside Lab 
 * student.java
 * */

public class Student extends Customer
{
  private double SAVINGS_INTEREST = 1.0/365.0;
  private double CHECK_INTEREST = 1.5/365.0;
  private double CHECK_CHARGE = 1.0;
  private double OVERDRAFT_PENALTY = 10.0;

  public Student(){}
  

  public Student(String n, String a, String p, String custNum, int custAge)
  {
    super(n,a,p,custNum,custAge);
  }
  

  public double getSavingsInterest()
  {
    return SAVINGS_INTEREST;
  }
  
 
  public double getCheckingInterest()
  {
    return CHECK_INTEREST;
  }
  
 
  public double getCheckCharge()
  {
    return CHECK_CHARGE;
  }
  
   
  public double getOverDraftPenalty()
  {
    return OVERDRAFT_PENALTY;
  } 
}