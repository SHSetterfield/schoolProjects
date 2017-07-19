package sudoku;

import java.util.*;


public class Grid 
{
	private int[][]						values;
	
	private Grid(Grid g){  //extra ctor to copy grid over before changing empty entries, generating next 9 grids
		System.out.println("special ctor for next9 called");
		int[][] newVal = new int[9][9];  //initialize 9x9 int array
		for(int row=0;row<9;row++){
			for(int col=0;col<9;col++){
				newVal[row][col]=g.values[row][col];  //copy values from old 9x9 array over
			}
		}
		values = newVal;  //why overwrite?
	}
	

	//
	// DON'T CHANGE THIS.
	//
	// See TestGridSupplier for examples of input.
	// Dots in input strings represent 0s in values[][].
	//
	
	public Grid(String[] rows)
	{
		values = new int[9][9];
		for (int j=0; j<9; j++)
		{
			String row = rows[j];
			char[] charray = row.toCharArray();
			for (int i=0; i<9; i++)
			{
				char ch = charray[i];
				if (ch != '.')
					values[j][i] = ch - '0';
			}
		}
		System.out.println("Normal grid ctor has completed");
	}
	
	
	//
	// DON'T CHANGE THIS.
	//
	public String toString()
	{
		String s = "";
		for (int j=0; j<9; j++)
		{
			for (int i=0; i<9; i++)
			{
				int n = values[j][i];
				if (n == 0)
					s += '.';
				else
					s += (char)('0' + n);
			}
			s += "\n";
		}
		System.out.println("toString method has completed");
		return s;
	}
	
	
	//
	//
	//
	// Finds an empty member of values[][]. Returns an array list of 9 grids that look like the current grid,
	// except the empty member contains 1, 2, 3 .... 9. Returns null if the current grid is full.
	//
	// Example: if this grid = 1........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//                         .........
	//
	// Then the returned array list would contain:
	//
	// 11.......          12.......          13.......          14.......    and so on     19.......
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	// .........          .........          .........          .........                  .........
	//
	public ArrayList<Grid> next9Grids()
	{	
		System.out.println("started next9Grids method");
		ArrayList<Grid> nextGrids = new ArrayList<Grid>();
		int x = -1;
		int y = -1;
		for(int row=0; row<9; row++){
			for(int col=0; col<9; col++){
				if(values[row][col]== 0){  //search through 2 dimensional array of ints for a zero			
					System.out.println("found a zero in next9Grids!");
					x = row;
					y = col;
					for(int k=1; k<10; k++){
						 Grid addedGrid = new Grid(this);  //call ctor to create next 9 arrays with 1-9 in the currently empty field
						 addedGrid.values[x][y] = k;
						 nextGrids.add(addedGrid);
					}
					System.out.println("return 9 and only 9 possible grids for ONE zero ONLY!");
					return nextGrids;
				}
			}
		}
		System.out.println("next9Grids returns null, no zeroes!!!!!!!!!!!");
		return null;
	}
	
	
	//
	//
	// Returns true if this grid is legal. A grid is legal if no row, column, diagonal, or 
	// 3x3 block contains a repeated 1, 2, 3, 4, 5, 6, 7, 8, or 9.
	//
	public boolean isLegal()
	{
		boolean legality = true;     //set legal condition inditially to true
		System.out.println("started isLegal method");
		
		for(int row=0; row<9; row++){   //check 9 full length rows for illegal behavior
			legality = isRowLegal(row);    
			if(legality==false){
				System.out.println("found illegal row");
				return false;
			}
		}
		
		for(int col=0; col<9; col++){		//check 9 full length columns for illegal behavior
			legality = isColumnLegal(col);  
			if(legality==false){
				System.out.println("found illegal column");
				return false;
			}
		}
		
		for(int box=1; box<10; box++){    //check 9 short 3x3 boxes for illegal behavior
			legality = isBoxLegal(box);
			if(legality==false){
				System.out.println("found illegal box");
				return false;
			}
		}
		System.out.println("$$$ Grid passed isLegal method succesfully!!!  Here's your Grid so far:");
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				System.out.print(values[i][j]);
			}
			System.out.println();
		}
		
		

		
		return true;  //if all tests pass, return true for your new legal board
	
	}
	
	private boolean isRowLegal(int aRow)
	{
		int numberCounter=0;
		for(int number=1;number<10;number++){
			for(int col=0;col<9;col++){
				if(values[aRow][col]==number){
					numberCounter++;
				}
				if(numberCounter>1){
					return false;
				}
				
			}
			numberCounter=0;  //reset number counter before scanning for next number
		}
		System.out.println("found legal row");
		return true;
	}
	
	private boolean isColumnLegal(int aCol)
	{
		int numberCounter=0;
		for(int number=1;number<10;number++){
			for(int row=0;row<9;row++){
				if(values[row][aCol]==number){
					numberCounter++;
				}
				if(numberCounter>1){
					return false;
				}
			}
			numberCounter=0;  //reset number counter before scanning for next number
		}
		System.out.println("found legal col");
		return true;
	}
	
	private boolean isBoxLegal(int box)
	{
		int minRow=0;
		int maxRow=0;
		int minCol=0;
		int maxCol=0;
		 ///$$$$ case statements for min/max bounds of each box
		if(box==1){
			minRow=1;
			maxRow=3;
			minCol=1;
			maxCol=3;
		}
		
		if(box==2){
			minRow=1;
			maxRow=3;
			minCol=4;
			maxCol=6;
		}
		
		if(box==3){
			minRow=1;
			maxRow=3;
			minCol=7;
			maxCol=9;
		}
		
		if(box==4){
			minRow=4;
			maxRow=6;
			minCol=1;
			maxCol=3;
		}
		
		if(box==5){
			minRow=4;
			maxRow=6;
			minCol=4;
			maxCol=6;
		}
		
		if(box==6){
			minRow=4;
			maxRow=6;
			minCol=7;
			maxCol=9;
		}
		
		if(box==7){
			minRow=7;
			maxRow=9;
			minCol=1;
			maxCol=3;
		}
		
		if(box==8){
			minRow=7;
			maxRow=9;
			minCol=4;
			maxCol=6;
		}
		
		if(box==9){
			minRow=7;
			maxRow=9;
			minCol=7;
			maxCol=9;
		}
		//start at values[1][1] for box 1
		int numberCounter=0;
		for(int number=1;number<10;number++){ //test box for each possible number 1-9
			for(int row=minRow; row<=maxRow; row++){
				for(int col=minCol; col<=maxCol; col++){
					if(values[row-1][col-1]==number){
						numberCounter++;
					}
				}
			}
			if(numberCounter>1){
				return false;  //after checking entire box for 1 number, check number counter for duplicates
			}
			numberCounter=0;  //reset number counter before scanning for next number
		}
		System.out.println("found legal box");
		return true;   //whew
		
	}
	
	
	
	
	
	//
	//
	// Returns true if every cell member of values[][] is a digit from 1-9.
	//
	public boolean isFull()
	{
		for(int row=0; row<9; row++){   //search through 2 dimensional array of ints for a zero
			for(int col=0; col<9; col++){
				if(values[row][col]==0){  
					System.out.println("isFull method returns false");
					return false;  //if zero is found, return false
				}
			}
		}
		System.out.println("isFull method returns true!!! $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		return true;  //if no zero is found, return true
				
	}
	
	//
	//
	// Returns true if x is a Grid and, for every (i,j), 
	// x.values[i][j] == this.values[i][j].
	//
	public boolean equals(Object x)
	{
		if(x instanceof Grid){  //if object x is a grid
			Grid check = (Grid)x;  //cast it as such 
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					if(check.values[i][j]!=this.values[i][j]){
						return false;  //return false if a value is a mismatch
					}
				}
			}
			return true;  //return true if object x is a grid and values match this.values
			
		}
		return false;  //return false if object x is not a grid

	}
	
	
}
