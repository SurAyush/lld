package ChessPackage;

import java.util.List;

abstract class Pieces{
    
    protected final Color color;
    protected final PieceType type;
    protected boolean hasMoved;

    Pieces(Color color, PieceType type) {
        this.color = color;
        this.type = type;
        hasMoved = false;
    }

    Color getColor(){
        return color;
    }

    PieceType getType(){
        return type;
    }

    void moved() {
        hasMoved = true;
    }

    boolean canJump() {
        return false;
    }

    // not checking for board limits or other pieces
    abstract List<Move> getMoves(Position currentPosition);
}
