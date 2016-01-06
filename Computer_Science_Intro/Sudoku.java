/* Andrew Dopson
 * CS 111                                                                                                                                                                                                        
 * Project 1                                                                                                                                                                                                     
 * 09/17/2012 */

import java.util.Scanner;
import java.io.FileReader;

public class Sudoku
{
    private static int [][] grid;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        grid = new int[6][6];
        System.out.println("Please enter the name of the file "
                           + "containing your Sudoku puzzle: ");
        String fileName = input.nextLine();
        makeBoard(fileName);
        //Prints puzzle for checking
		for(int x = 0; x < 6; x++){
			for (int y = 0; y < 6; y++){
				System.out.print(grid[x][y]);
			}
			System.out.println();
		}
		System.out.println();
        
        if(solve(0, 0, grid))
	    {
		System.out.println("Here is the solution! \n");
		for(int x = 0; x < 6; x++)
		    {
			for (int y = 0; y < 6; y++)
			    {
				System.out.print(grid[x][y]);
                    
			    }
			System.out.println();
		    }
		System.out.println();
	    }
        else
            {
                System.out.println("Solution not found!");
            }
    }

    /**makeBoard method takes a filename 
     * as a string, gets the file,
     * and puts the values into
     * a 2-D array containing ints
     * representing the sudoku board
     * pre: must be a valid filename
     *      there must be spaces between each int
     * post: the array will contain numbers and zeros
     * responses: throws an IOException for file problems
     *           terminates if puzzle data is bad
     * @param the filename
     * @throws IOException
     */
    public static void makeBoard(String fileName)
    {
        try {
	    int i;
            int j;
            Scanner fileScan = new Scanner(new FileReader (fileName));
	    while (fileScan.hasNext())
		{
            
		    for (i=0; i< 6 ;i++)
			{
			    for (j=0; j< 6 ;j++)
				{
				    int num = fileScan.nextInt();
				    grid[i][j] = num;
				}
			}
		}
        }
        catch (Exception e)
	    {
		System.out.println(e);
	    }
    }  
    
    /** solve method uses recursion to try different answers 
     * for a 6X6 Sudoku problem. The correct answers are recorded.
     * pre: int[][] grid must contain a valid puzzle
     * post: the solution is recorded in an int[][] solution
     *       and true is returned if it was solved
     *       false it it was not. 
     *       The int[][] grid contains the solution 
     *       if the puzzle was solved   
     * responses: error terminate for invalid input
     * @param: int[][]grid the puzzle to be solved
     *         int row the row index number to start from 
     *         int column the column number to start from
     * @return: boolean true if the puzzle was solved
     *          false if otherwise
     * */
    public static boolean solve(int row, int column, int[][] grid)
    {
        if (row > 5)
            return true;
        
        if (grid[row][column] != 0)
	    {
		if (column < 5)
		    {
			solve(row, column + 1, grid);
		    }
		else
		    {
			solve(row + 1, 0, grid);
		    }
	    }
        else
	    {
		for( int number = 1; number < 7; number++)
		    {
			if( notInRow(row, number) && notInColumn(column, number) && notInBox(row, column, number))
			    {
				grid[row][column] = number;
				if (column < 5)
				    solve(row, column + 1, grid);
				else
				    solve(row + 1, 0, grid);
			    }
		    }            
	    }
        grid[row][column] = 0;
        return false;
    }
 
    /* Method notInBox checks to see if a given number is 
     * in the respective box region 
     * pre: must have a specified position (row and column)
     *      and must have a number that needs to be checked 
     *      for
     * post: boolean value is assigned false if box already 
     *       contains that number and true if it does not
     * @param: int row the row index
     *         int column the column index
     *         int number the number to check for
     * @return: boolean false if box contains number already
     *          true if it does not
     * @throws IndexOutOfBoundsException
     */
    public static boolean notInBox(int row, int column, int number)
    {
        //Top Left Box
        if(row >= 0 && row < 2 && column >= 0 && column < 3)
	    {   
		for(int x = 0; x < 2; x++)
		    {
			for(int y = 0; y < 3; y++)
			    {
				if(grid[x][y] == number)
				    return false;
			    }
		    }
	    }
        
        //Middle Left Box
        if(row >= 2 && row < 4 && column >= 0 && column < 3)
	    {   
		for(int x = 2; x < 4; x++)
		    {
			for(int y = 0; y < 3; y++)
			    {
				if(grid[x][y] == number)
				    return false;     
			    }
		    }
	    }
        
        //Bottom Left Box
        if(row >= 4 && row < 6 && column >= 0 && column < 3)
	    {   
		for(int x = 4; x < 6; x++)
		    {
			for(int y = 0; y < 3; y++)
			    {
				if(grid[x][y] == number)
				    return false;
			    }
		    }
	    }
        
        //Top Right Box
        if(row >= 0 && row < 2 && column >= 3 && column < 6)
	    {   
		for(int x = 0; x < 2; x++)
		    {
			for(int y = 3; y < 6; y++)
			    {
				if(grid[x][y] == number)
				    return false;
			    }
		    }
	    }
        
        //Middle Right Box
        if(row >= 2 && row < 4 && column >= 3 && column < 6)
	    {   
		for(int x = 2; x < 4; x++)
		    {
			for(int y = 3; y < 6; y++)
			    {
				if(grid[x][y] == number)
				    return false; 
			    }
		    }
	    }
        
        //Bottom Right Box
        if(row >= 4 && row < 6 && column >= 3 && column < 6)
	    {   
		for(int x = 4; x < 6; x++)
		    {
			for(int y = 3; y < 6; y++)
			    {
				if(grid[x][y] == number)
				    return false; 
			    }
		    }
	    }
        return true;
    }
    
    /* Method notInRow checks to see if there is already
     * a specified number in that row
     * pre: must be given a valid row index
     * post: returns true if number already exists in row
     *       false if it does not
     * @param: int row the row index to be searched
     *         int number the number being looked for
     * @return: false if the number exists in the row
     *          true if it does not
     * @throws: IndexOutOfBoundsException
     */
    public static boolean notInRow(int row, int number)
    {
        for(int column = 0; column < 6; column++)
	    {
		if(grid[row][column] == number)
		    return false;
	    }
        return true;
    }
    
    /* Method notInColumn checks to see if there is already
     * a specified number in that column
     * pre: must be given a valid row index
     * post: returns false if number already exists in column
     *       true if it does not
     * @param: int column the column index to be searched
     *         int number the number being looked for
     * @return: false if the number exists in the column
     *          true if it does not
     * @throws: IndexOutOfBoundsException
     */
    public static boolean notInColumn(int column, int number)
    {
        for(int row = 0; row < 6; row++)
	    {
		if(grid[row][column] == number)
		    return false;
	    }
        return true;
    }
}

