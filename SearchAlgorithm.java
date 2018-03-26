import TicTacToeGame.logic.ScoresAndBoardCells;
import TicTacToeGame.gui.Board;
import TicTacToeGame.gui.Board.Players;
import TicTacToeGame.logic.BoardCell;
import java.util.ArrayList;
import java.util.List;

public class SearchAlgorithm {
    
    private static Board board;
    
    private int alphaBeta(int depth, Board.Players player, int alpha, int beta) {
        if (!board.running())
            return heuristicValue(board);
        if (player == Players.MAX)
            return Maximize(depth, player, alpha, beta);
        else
            return Minimize(depth, player, alpha, beta);
        
    }
    
    private int Maximize(int depth, Board.Players player, int alpha, int beta) {
        List<Integer> scores = new ArrayList();
        
        for (BoardCell availableBoardCell : board.getEmptyCells()) {
            board.move(availableBoardCell, Players.MAX);
            int currentScore = alphaBeta(depth + 1, player.MIN, alpha, beta);
            if (currentScore > alpha)
                alpha = currentScore;
            scores.add(alpha);
            if (depth == 0)
                board.getrootValuePositions().add(new ScoresAndBoardCells(alpha, availableBoardCell));
            if (alpha >= beta)
                break;
        }
        return evaluateMax(scores);
    }
    
    private int Minimize(int depth, Board.Players player, int alpha, int beta) {
        List<Integer> scores = new ArrayList();
        
        for (BoardCell availableBoardCell : board.getEmptyCells()) {
            board.move(availableBoardCell, Players.MIN);
            int currentScore = alphaBeta(depth + 1, player.MAX, alpha, beta);
            if (currentScore < beta)
                beta = currentScore;
            scores.add(beta);
            if (alpha >= beta)
                break;
        }
        return evaluateMin(scores);
    }
    
    private static int heuristicValue(Board board) {
        if (board.winning(Board.Players.MAX))
            return +1;
        if (board.winning(Board.Players.MIN))
            return -1;
        return 0;
    }
    
}
