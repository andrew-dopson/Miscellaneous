/* Andrew Dopson
   Lab6 - CS 111
   Section 002
   10/01/2012 */

public class Student2 extends Person
{
    private double gpa;
    private int grade;

    public Student2(String firstName, String lastName, int age1, double gpa, int grade)
    {
	super(firstName, lastName, age1);
	this.gpa = gpa;
	this.grade = grade;
    }

    public Student2()
    {
	super();
	gpa = 0;
	grade = 0;
    }

    public void setGPA(double newGPA)
    {
	gpa = newGPA;
    }

    public void setGrade(int newGrade)
    {
	grade = newGrade;
    }

    public double getGPA()
    {
	return gpa;
    }

    public int getGrade()
    {
	return grade;
    }

    public void printPerson()
    {
	System.out.println("First Name: " + super.getFirst() + "\nLast Name: " + super.getLast() + "\nAge: " + super.getAge() + "\nGPA: "
			   + gpa + "\nGrade: " + grade);
    }
}

