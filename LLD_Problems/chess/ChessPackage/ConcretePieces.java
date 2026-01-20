package ChessPackage;
import java.util.List;
import java.util.ArrayList;

class Pawn extends Pieces{
    
    Pawn(Color color) {
        super(color, PieceType.PAWN);
    }

    List<Move> getMoves(Position currentPosition) {
        List<Move> moves = new ArrayList<>();
        int direction = (color == Color.WHITE) ? 1 : -1;
        moves.add(new Move(currentPosition,new Position(currentPosition.x , currentPosition.y + direction), false, true));
        if(!hasMoved){
            moves.add(new Move(currentPosition,new Position(currentPosition.x , currentPosition.y+2*direction), false, true));
        }
        moves.add(new Move(currentPosition, new Position(currentPosition.x + 1, currentPosition.y + direction), true, false));
        moves.add(new Move(currentPosition, new Position(currentPosition.x - 1, currentPosition.y + direction), true, false));
        return moves;
    }

}

class Rook extends Pieces{

    Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    List<Move> getMoves(Position currentPosition) {
        List<Move> moves = new ArrayList<>();
        for(int i=0;i<8;i++){
            if(i!=currentPosition.x){
                moves.add(new Move(currentPosition,new Position(i,currentPosition.y), true, true));
            }
            if(i!=currentPosition.y){
                moves.add(new Move(currentPosition,new Position(currentPosition.x,i), true, true));
            }
        }
        return moves;
    }
}

class Knight extends Pieces{

    Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    List<Move> getMoves(Position currentPosition) {
        List<Move> moves = new ArrayList<>();
        int[][] directions = {{2,1},{2,-1},{-2,1},{-2,-1},{1,2},{1,-2},{-1,2},{-1,-2}};
        for(int[] dir : directions){
            moves.add(new Move(currentPosition,new Position(currentPosition.x + dir[0], currentPosition.y + dir[1]), true, true));
        }
        return moves;
    }

    @Override
    boolean canJump() {
        return true;       // special ability to jump over pieces
    }
}

class Bishop extends Pieces{

    Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    List<Move> getMoves(Position currentPosition) {
        List<Move> moves = new ArrayList<>();
        for(int i=-7;i<=7;i++){
            if(i!=0){
                moves.add(new Move(currentPosition,new Position(currentPosition.x + i, currentPosition.y + i), true, true));
                moves.add(new Move(currentPosition,new Position(currentPosition.x + i, currentPosition.y - i), true, true));
            }
        }
        return moves;
    }
}


class Queen extends Pieces{

    Queen(Color color) {
        super(color, PieceType.QUEEN);
    }

    List<Move> getMoves(Position currentPosition) {
        List<Move> moves = new ArrayList<>();
        for(int i=-7;i<=7;i++){
            if(i!=0){
                moves.add(new Move(currentPosition,new Position(currentPosition.x + i, currentPosition.y + i), true, true));
                moves.add(new Move(currentPosition,new Position(currentPosition.x + i, currentPosition.y - i), true, true));
                moves.add(new Move(currentPosition,new Position(currentPosition.x + i, currentPosition.y), true, true));
                moves.add(new Move(currentPosition,new Position(currentPosition.x, currentPosition.y + i), true, true));
            }
        }
        return moves;
    }
}

class King extends Pieces{
    King(Color color) {
        super(color, PieceType.KING);
    }

    List<Move> getMoves(Position currentPosition) {
        List<Move> moves = new ArrayList<>();
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
        for(int[] dir : directions){
            moves.add(new Move(currentPosition,new Position(currentPosition.x + dir[0], currentPosition.y + dir[1]), true, true));
        }
        return moves;
    }
}
