/** Oskar Otoya
  * CS111 Outside Lab
  * Bank.java
  * */
import java.util.Scanner;

public class Bank
{
  public static Scanner scan = new Scanner(System.in);
  public static Account[] myBank = new Account[100];
  public static int i = 0;
  public static boolean done = false; 
 
  public static void main(String[] args)
  {
    System.out.println("Welcome to the Bank");

    while(!done)
    {
      showMenu();
      makeChoice();
      System.out.println();
    }
  }
  

  public static void showMenu()
  {
    System.out.println("|*******************************************|");
    System.out.println("|******************Menu****************|");
    System.out.println("| 1) Add an account                         |");
    System.out.println("| 2) Make a deposit                         |");
    System.out.println("| 3) Make a withdrawal                      |");
    System.out.println("| 4) Show balance                           |");
    System.out.println("| 5) Show transactions                      |");
    System.out.println("| 6) Show accounts                          |");
    System.out.println("| 7) Add interest                           |");
    System.out.println("| 8) Exit                                   |");
    System.out.println("|*******************************************|");   
    System.out.println("|*******************************************|");    
  }
  

  public static void addAccount()
  {
    scan = new Scanner(System.in);
    Customer myCustomer = new Customer();
    Account myAccount = new Account();
    System.out.println("Enter your name: ");
    String name = scan.nextLine();
    myCustomer.setName(name);
    System.out.println("Enter your address: ");
    myCustomer.setAddress(scan.nextLine());
    System.out.println("Enter your phone number: ");
    myCustomer.setPhoneNumber(scan.nextLine());
    System.out.println("Enter your desired customer number: ");
    myCustomer.setCustomerNumber(scan.nextLine());
    System.out.println("Enter your age: ");
    int age = scan.nextInt();
    if (age<=25)
    {
      myCustomer = new Student(myCustomer.getName(), 
                             myCustomer.getAddress(),
                             myCustomer.getPhoneNumber(), 
                             myCustomer.getCustomerNumber(), age);
    }
    else if (age<=62)
    {
      myCustomer = new Adult(myCustomer.getName(), 
                           myCustomer.getAddress(),
                           myCustomer.getPhoneNumber(), 
                           myCustomer.getCustomerNumber(),age);
    }
    
    else
    {
      myCustomer = new Senior(myCustomer.getName(), 
                            myCustomer.getAddress(),
                            myCustomer.getPhoneNumber(), 
                            myCustomer.getCustomerNumber(),age);
    }
    scan = new Scanner(System.in);
    System.out.println("Enter your desired account number: ");
    String an = scan.nextLine();
    System.out.println("Enter your starting balance: ");
    double b = scan.nextDouble();
    System.out.println("What type of account would you like to create?"
                         +"\n Enter 1 for Savings"
                         +"\n Enter 2 for Checking");
    int c = scan.nextInt();
    if (c==1)
     myAccount = new SavingsAccount(myCustomer, an, b);
    else
     myAccount = new CheckingAccount(myCustomer, an, b);
    myBank[i]=myAccount;
    i++;
    System.out.println("Your account had been added");
    System.out.println(myAccount);
    //System.out.println(myCustomer.getAddress());
    
  }
 

  public static void makeChoice()
  {
    int n = scan.nextInt();
    switch (n)
    {
      case 1:
        addAccount();
        break;
      case 2:
        makeDeposit();
        break;
      case 3:
        makeWithdrawal();
        break;
      case 4:
        determineBalance();
        break;
      case 5:
        displayTransactions();
        break;
      case 6:
        displayAccounts();
        break;
      case 7:
        //addInterest();
        break;
      case 8:
      default:
        System.out.println("Thanks for banking with us!");
        done = true;
        break;
    }
  }


  public static void makeWithdrawal()
  {
    System.out.println("Enter the day of the month as an integer: ");
    int date = scan.nextInt();
    System.out.println("Enter the account number: ");
    String acctNum = scan.next();
    Account a = (Account)getAccount(acctNum);
    System.out.println("Enter the amount you'd like to withdraw: ");
    double amt = scan.nextDouble();
    Transaction t = new Transaction( acctNum, "Withdrawal", amt, date);
    a.withdraw(amt,t);
    System.out.println("Your transaction has been processed"); 
    System.out.println(a.getBalance());
  }
  

  public static void makeDeposit()
  {
    System.out.println("Enter the day of the month as an integer: ");
    int date = scan.nextInt();
    System.out.println("Enter the account number: ");
    String acctNum = scan.next();
    Account a = (Account)getAccount(acctNum);
    System.out.println("Enter the amount you'd like to deposit: ");
    double amt = scan.nextDouble();
    Transaction t = new Transaction( acctNum, "Deposit", amt, date);
    a.deposit(amt,t);
    System.out.println("Your transaction has been processed"); 
    System.out.println(a.getBalance());
  }
  

  public static void determineBalance()
  {
  System.out.println("Enter the account number: ");
  Account a = (Account)getAccount(scan.next());
  System.out.println("Balance for account "+a.getAccountNumber()+":"+"\t"+"$"+a.getBalance());
  }
  
 public static void displayTransactions()
  {
    System.out.println("Enter the account number: ");
    Account a = (Account)getAccount(scan.next());
    System.out.println(a.getHistory());
  }

  public static void displayAccounts()
  {
    System.out.println("Please enter your customer number: ");
    String custNum = scan.next();
    for (Account a: myBank)
    {
      if (custNum.equals(a.getCustomer().getCustomerNumber()))
      System.out.println(a.toString());
    }
  }

  public static Account getAccount(String acctNum)
  {
    for (Account a: myBank)
    {
      if (a.getAccountNumber().equals(acctNum))
      return a;
      else return a;
    }
    return null;
  }
}