package ChessPackage;

class Move{
    Position from;
    Position to;
    boolean canCapture;
    boolean canMove;

    Move(Position from, Position to, boolean canCapture, boolean canMove){ 
        this.from = from;
        this.to = to;
        this.canCapture = canCapture;
        this.canMove = canMove;
    }
}
