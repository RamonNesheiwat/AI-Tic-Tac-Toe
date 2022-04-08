import java.util.ArrayList;
import java.util.List;

public class Board
{
	final static int[][] board = new int[3][3]; 
	static List<TicTacToeCoordinates> coordinatesLeft;
	final static List<TicTacToeScoring> children = new ArrayList<>();
	final static int size = 2;

	/*
	 * Function that checks if Player 1 or Player 2 is winning
	 * If Player 1 is leading, +1000 will be given.
	 * If Player 2 is leading, -1000 will be given
	 * If neither Player 1 or Player 2 has won, 0 will be returned
	 */
	public static int heuristicFunction() 
	{
		// . Diagonals.
		if (board[0][0]==board[1][1] && board[1][1]==board[2][2]) 
			if (board[0][0]==1) 
				return +1000; 
			else if (board[0][0]==2) 
				return -1000; 
		if (board[0][2]==board[1][1] && board[1][1]==board[2][0]) 
			if (board[0][2]==1) 
				return +1000; 
			else if (board[0][2]==2) 
				return -1000; 
		// Rows.
		for (int i = 0; i<=size; i++) 
			if (board[i][0]==board[i][1] && board[i][1]==board[i][2])  
				if (board[i][0]==1) 
					return +1000; 
				else if (board[i][0]==2) 
					return -1000; 

		// Columns. 
		for (int k = 0; k<=size; k++) 
			if (board[0][k]==board[1][k] && board[1][k]==board[2][k]) 
				if (board[0][k]==1) 
					return +1000; 
				else if (board[0][k]==2) 
					return -1000;  

		return 0; 
	}
	
	// The players coordinates they are allowed to choose.
		public static void coordinates(TicTacToeCoordinates point, int player) 
		{
			board[point.x][point.y] = player;  
		}

	// The Alpha Beta Algorithm. Used to find the most optimal move for the AI.
	public static int alphaBeta(int alpha, int beta, int level, int player)
	{
		int currentScore = 0;
		int max = -1000;
		int min = 1000;
		List<TicTacToeCoordinates> coordinatesAvailable = coordinatesLeft();
		if(level==0) 
			children.clear();
		for(int i=0;i < coordinatesAvailable.size(); ++i)
		{
			TicTacToeCoordinates coordinate = coordinatesAvailable.get(i);
			if(player == 1)
			{
				coordinates(coordinate, 1); 
				currentScore = alphaBeta(alpha, beta, level+1, 2);
				alpha = Math.max(currentScore, alpha);
				max =  Math.max(max, currentScore); 
				if(level == 0)
					children.add(new TicTacToeScoring(currentScore, coordinate));
			}
			else 
			{
				coordinates(coordinate, 2);
				currentScore = alphaBeta(alpha, beta, level+1, 1); 
				beta = Math.min(currentScore, beta);
				min =  Math.min(min, currentScore);
				if(level == 0)
					children.add(new TicTacToeScoring(currentScore, coordinate));

			}
			board[coordinate.x][coordinate.y] = 0; 
		}
		if(alpha>=beta)
			if(player == 1) 
				return min; 
			else 
				return max; 
		if(lost() || won() || coordinatesLeft().isEmpty()) 
			return heuristicFunction();
		if(player == 1)
			return  max;
		else
			return min; 
	}  

	// Checks if the player has lost.
	public static boolean lost() 
	{
		// Diagonal
		if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1)) 
			return true;
		if((board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1))
			return true;
		// Row.
		for (int i = 0; i <= size; i++) 
			if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)) 
				return true;
		// Column
		for (int k = 0; k <= size; k++) 
			if((board[0][k] == board[1][k] && board[0][k] == board[2][k] && board[0][k] == 1))
				return true;
		return false;
	}

	// Checks if the player has won.
	public static boolean won() 
	{
		// Diagonal
		if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == 1)) 
			return true;
		// // Diagonal
		if((board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == 1))
			return true;
		// Row
		for (int i = 0; i <= size; i++) 
			if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == 1)) 
				return true;
		// Column
		for (int k = 0; k <= size; k++) 

			if((board[0][k] == board[1][k] && board[0][k] == board[2][k] && board[0][k] == 1))
				return true;
		return false;
	}

	// Checks the available positions left on the board
	public static List<TicTacToeCoordinates> coordinatesLeft() 
	{
		coordinatesLeft = new ArrayList<>();
		for (int i = 0; i <= size; ++i) 
			for (int k = 0; k <= size; ++k) 
				if (board[i][k] == 0) 
					coordinatesLeft.add(new TicTacToeCoordinates(i, k));
		return coordinatesLeft;
	}

	// Helps determine the AI's best move
	public static TicTacToeCoordinates AIsMove() 
	{
		int max = -1000;
		int best = 1000;
		for (int i = 0; i < children.size(); i++) 
			if (max < children.get(i).score) 
			{
				max = children.get(i).score;
				best = i;
			}
		return children.get(best).coordinate;
	}
}