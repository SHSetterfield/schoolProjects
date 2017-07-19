package sudoku;

import java.util.*;


public class Solver 
{
	private Grid						problem;
	private ArrayList<Grid>				solutions;
	
	
	public Solver(Grid problem)
	{
		this.problem = problem;
	}
	
	
	public void solve()
	{
		solutions = new ArrayList<>();
		solveRecurse(problem);
	}
	
		
	// 
	//
	// Standard backtracking recursive solver.
	//
	private void solveRecurse(Grid grid)
	{		
		Evaluation eval = evaluate(grid);
		
		if (eval == Evaluation.ABANDON)
		{
			// Abandon evaluation of this illegal board.
			System.out.println("abandoned a grid");
			return;
		}
		else if (eval == Evaluation.ACCEPT)
		{
			// A complete and legal solution. Add it to solutions.
			System.out.println("accepted a grid");
			solutions.add(grid);
		}
		else if (eval== Evaluation.CONTINUE)
		{
			// Here if eval == Evaluation.CONTINUE. Generate all 9 possible next grids. Recursively 
			// call solveRecurse() on those grids.
			System.out.println("continuing a grid.....standby");
			ArrayList<Grid> nextGrids =  grid.next9Grids();
			for(Grid nextGrid : nextGrids){  //iterate through nextGrids
				System.out.println("$$$$$ CURRENTLY RECURSING $$$$$$$$$");
				solveRecurse(nextGrid);  //pass next grid to recursive solver
			}
		}
	}
	
	//
	//
	//
	// Returns Evaluation.ABANDON if the grid is illegal. 
	// Returns ACCEPT if the grid is legal and complete.
	// Returns CONTINUE if the grid is legal and incomplete.
	//
	public Evaluation evaluate(Grid grid)
	{
		if(grid.isLegal()==false){
			System.out.println("Grid is illegal");
			return (Evaluation.ABANDON);
		}
		if((grid.isLegal()==true) && (grid.isFull()==true)){
			System.out.println("$$$$$$$$$$$$-----Grid is legal and full------$$$$$$$$$$$$$$$$$$");
			return (Evaluation.ACCEPT);
		}
		else return Evaluation.CONTINUE;
	}
		
		
	

	
	public ArrayList<Grid> getSolutions()
	{
		return solutions;
	}
	
	
	public static void main(String[] args)
	{
		Grid g = TestGridSupplier.getPuzzle2();		// or any other puzzle
		Solver solver = new Solver(g);   //set problem to grid from supplier
		solver.solve();      //initialize solution arrayList and solveRecurse(problem)
		
		// Print out your solution, or test if it equals() the solution in TestGridSupplier.
		System.out.println("Here are the solutions:");
		System.out.println(solver.getSolutions());
		//do equality check of solution and print result here
		
	}
}
