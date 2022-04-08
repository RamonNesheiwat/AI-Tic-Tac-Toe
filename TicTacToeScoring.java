public class TicTacToeScoring 
{
	TicTacToeCoordinates ticTacToe = new TicTacToeCoordinates(0,0);

	final int score;
	final TicTacToeCoordinates coordinate;

	TicTacToeScoring(int score0, TicTacToeCoordinates coordinate0) 
	{
		score = score0;
		coordinate = coordinate0;
	}
}