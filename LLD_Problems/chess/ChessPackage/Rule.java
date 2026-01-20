package ChessPackage;
import java.util.List;

interface Rule{
    List<Move> getValidMoves(Board board, Position from);
    boolean isValidMove(List<Move> moves, Position from, Position to);
    boolean isCheck(Board board, Color color);
    boolean isCheckmate(Board board, Color color);
    boolean isStalemate(Board board, Color color);
}

