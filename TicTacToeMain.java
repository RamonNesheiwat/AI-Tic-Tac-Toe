import java.util.Scanner;

public class TicTacToeMain 
{

	public static void main(String[] args) 
	{ 
		System.out.println("Welcome to Ramon's TicTacToe Game: ");
		System.out.println("Enter the Coordinates to move.");
		System.out.println("You are Player 2. The AI is Player 1.");
		System.out.println("0 0 is top left. 0 1 is top middle. 02 is top right.");
		System.out.println("1 0 is middle left. 1 1 is middle. 12 is middle right.");
		System.out.println("2 0 is bottom left. 2 1 is bottom middle. 2 2 is bottom right.");

		@SuppressWarnings("resource")
		Scanner kb = new Scanner(System.in);
		final int size = 2;
		while (!Board.lost() || !Board.won() || Board.coordinatesLeft().isEmpty()) 
		{
			System.out.println("Your Turn: ");
			TicTacToeCoordinates player 
			= new TicTacToeCoordinates(kb.nextInt(), kb.nextInt());
			Board.coordinates(player, 2); 
			for (int i = 0; i <= size; i++) 
			{
				for (int k = 0; k <= size; k++) 
					System.out.print(Board.board[i][k] + " ");
				System.out.println();
			}
			System.out.println();
			if (Board.lost() || Board.won()) 
				return;
			if(Board.coordinatesLeft().isEmpty())
			{
				System.out.println("It's a tie.");
				break;
			}
			
			Board.alphaBeta(-1000, 1000, 0, 1);
			System.out.println("AI's Turn: ");
			Board.coordinates(Board.AIsMove(), 1);
			for (int i = 0; i <= size; i++) 
			{
				for (int k = 0; k <= size; k++) 
					System.out.print(Board.board[i][k] + " ");
				System.out.println();
			}
		}
		kb.close();
		if (Board.lost()) 
		{
			System.out.println("You lost.");
		} 
		else if (Board.won()) 
		{
			System.out.println("You won.");
		} 
		System.out.println("Thank You for Playing.");
	}
}
