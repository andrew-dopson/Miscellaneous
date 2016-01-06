/**Oskar Otoya
  * CS111 Outside Lab
  * Senior.java
  * */
public class Senior extends Customer
{
  private double SAVINGS_INTEREST = 1.75/365;
  private double CHECK_INTEREST = 1.25/365;
  private double CHECK_CHARGE = 0.75;
  private double OVERDRAFT_PENALTY = 7.50;
  
  public Senior(){}
  

  public Senior(String n, String a, String p, String custNum, int custAge)
  {
    super(n,a,p,custNum,custAge);
  }

  public double getSavingsInterest(){return SAVINGS_INTEREST;}
  
 
  public double getCheckingInterest(){return CHECK_INTEREST;}
  
 
  public double getCheckCharge(){return CHECK_CHARGE;}
  

  public double getOverDraftPenalty(){return OVERDRAFT_PENALTY;} 
}
