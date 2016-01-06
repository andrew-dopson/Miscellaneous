//UsePerson.java
//Sample to use person class

public class UsePerson
{

    public static void main(String[] args)
    {

	Person p1 = new Person();
	Person p2 = new Person("Bob", "Sagot", 35);
	Person p3 = new Person("Andrew", "Dopson", 20);

	p1.printPerson();
	p2.printPerson();
	p3.printPerson();

	System.out.println(p1.compareTo(p2));

	System.out.println(p1.equals(p2));
	System.out.println(p1.equals(p3));

	Person p4 = (Person)p2.clone();
	System.out.println("Person 4 is: ");
	p4.printPerson();

    }
}