/**Oskar Otoya
  * CS111 Outside Lab 
  * Transaction.java
  * */

public class Transaction
{
  private String accountNumber;
  private String transactionType;
  private double amount;
  private double date;


  public Transaction(String acctNum, String transType, double amt, double day)
  {    
   accountNumber = acctNum;
   transactionType = transType;
   amount = amt;
   date = day;
  }

  public String processTransaction()
  { 
    return "Account number: "+"\t"+accountNumber+"\n" +"Date: "+"\t"+date
      +"\n"+"Type: "+"\t"+ transactionType+"\n"+"Amount: "+"\t"+amount;
  }
}