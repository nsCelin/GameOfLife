package com.GameOfLife;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameOfLife {

	public static void main(String[] args)
    {
		Scanner sc = new Scanner(System.in);
		
		List<String> inputs = new ArrayList<String>();
		System.out.println("Enter Matrix Column and Row");
		int a = sc.nextInt();
		int b = sc.nextInt();
		
		System.out.println("Enter the number of generation needed ");
		int genCount = sc.nextInt();
		
		System.out.println("Enter the input array");		
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			if (line.equals("QUIT")) {
	            break;
	        }
			inputs.add(line);			
		}
		

		int M = a, N = b;
        int next[][] = null;
 
        // Initializing the grid.
        int[][] grid = new int[a][b];
      
        // Enter Matrix Data
        enterMatrixData(sc, grid, a, b,inputs);
		sc.close();
 
        // Displaying the grid
        System.out.println("Original Generation");
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (grid[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
        
        /*Generating the first generation*/
        int[][] future = nextGeneration(grid, M, N);
        
        /*Generating the next generations based on the input generation count*/
        for(int i=0; i<genCount; i++)
        {
        	next = future;
        	future = nextGeneration(next, M, N);
        }
    }
	
	/*Storing the input values given in n array format*/
	public static void enterMatrixData(Scanner scan, int[][] matrix, int matrixRow, int matrixCol,List<String> inputs){
	     	          
	     inputs.remove(0);
	          for (int i = 0; i < matrixRow; i++)
	          {
	              for (int j = 0; j < matrixCol; j++)
	              {
	            	  for (String in : inputs)
	            	  {
	            		  String[] parts= in.split(",");
	            		  
	            		  int one = Integer.parseInt(parts[0]);
	            		  int two = Integer.parseInt(parts[1]);
	            		  if(one == i && two == j)
	            		  {
	            			  matrix[i][j] = 1;
	            			  break;
	            		  }	            			  
	            		  else
	            			  matrix[i][j] = 0;
	            	  }
	              }
	          }
	  }
	
	// Function to print next generation
	static int[][] nextGeneration(int grid[][], int M, int N)
    {
        int[][] future = new int[M][N];
 
        // Loop through every cell
        for (int l = 1; l < M - 1; l++)
        {
            for (int m = 1; m < N - 1; m++)
            {
                // finding no Of Neighbours that are alive
                int aliveNeighbours = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        aliveNeighbours += grid[l + i][m + j];
 
                // The cell needs to be subtracted from
                // its neighbours as it was counted before
                aliveNeighbours -= grid[l][m];
 
                // Implementing the Rules of Life
 
                // Cell is lonely and dies
                if ((grid[l][m] == 1) && (aliveNeighbours < 2))
                    future[l][m] = 0;
 
                // Cell dies due to over population
                else if ((grid[l][m] == 1) && (aliveNeighbours > 3))
                    future[l][m] = 0;
 
                // A new cell is born
                else if ((grid[l][m] == 0) && (aliveNeighbours == 3))
                    future[l][m] = 1;
 
                // Remains the same
                else
                    future[l][m] = grid[l][m];
            }
        }
 
        System.out.println("Next Generation");
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (future[i][j] == 0)
                    System.out.print(".");
                else
                    System.out.print("*");
            }
            System.out.println();
        }
        
        return future;
    }
}

