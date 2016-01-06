/**Oskar Otoya
  * CS111 Outside Lab 
  * Customer.java
  * */
public class Customer
{
  private String name;
  private String address;
  private int age;
  private String phoneNumber;
  private String customerNumber;

  public Customer(){}
 

  public Customer(String n, String a, String p, String custNum, int custAge)
  {
    name = n;
    address= a;
    phoneNumber = p;
    age = custAge;
  }
  

  public String getName(){return name;}
   

  public String getAddress(){return address;}


  public String getPhoneNumber(){return phoneNumber;}
   

  public String getCustomerNumber(){return customerNumber;}

  public int getAge(){return age;}
   

  public void setName(String n)
  {
    name = n;
  }

  public void setAddress(String a)
  {
    address = a;    
  }

  public void setPhoneNumber(String p)
  {
    phoneNumber = p;
  }

  public void setCustomerNumber(String c)
  {
    customerNumber = c;
  }

  public void setAge(int a)
  {
    age = a;       
  }
}
