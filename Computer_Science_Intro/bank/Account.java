/**Oskar Otoya
  * CS 111 Lab assignment
  * Account.java
  * */

public class Account
{
  private Customer c;
  private String accountNumber;
  private double balance;
  private Transaction[] history= new Transaction[100];
  int i = 0;
  
  /**Null constructor creates an acocunt with no parameters*/
  public Account(){}

  public Account(Object o, String acctNum, double bal)
  {
    c = (Customer)o;
    accountNumber = acctNum;
    balance = bal;
  }
  
  public void deposit(double amt, Object o)
  {
    balance+=amt;
    Transaction t = (Transaction) o;
    history[i]=t;
    i++;
  }
 
  public void withdraw(double amt, Object o)
  {
    
    if (balance < amt)
      System.out.println("Insufficient Funds!");
    else
    {
      balance = balance-amt;
      Transaction t = (Transaction) o;
      history[i]=t;
      i++;
    }
  }
  
  public String getAccountNumber(){return accountNumber;}

  public double getBalance(){return balance;}

  public Customer getCustomer(){return c;}

  public String getHistory()
  {
    for (Transaction t: history)
      return(t.processTransaction());
    return null;
  }


  public String toString()
  {
    return ("Account number:\t"+accountNumber
              +"\nBalance:\t"+balance);
  }

  public void addInterest()
  {
    
  }
  

  public void setCustomer(Object o)
  {
    c = (Customer)o;
  }
}