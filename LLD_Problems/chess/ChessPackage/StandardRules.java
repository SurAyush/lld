package ChessPackage;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

class StandardRule implements Rule{

    private boolean pieceCheckingKing(Board board, Position kingPos, Position piecePos){
        List<Move> moves = getAttackSquares(board, piecePos);
        // only capture moves matter
        for(Move move : moves){
            if(move.to.equals(kingPos)){    // all attack squares are capture moves
                return true;
            }
        }
        return false;
    }

    private boolean inBounds(Position pos){
        return pos.x >=0 && pos.x <8 && pos.y >=0 && pos.y <8;
    }

    private void inBoundsFilter(List<Move> possibleMoves, boolean[] valid){
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                if(!inBounds(move.to)){
                    valid[i] = false;
                    continue;
                }
            } 
        }
    }

    private void isSameColorFilter(List<Move> possibleMoves, boolean[] valid, Board board, Color color){
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                Pieces piece = board.getPieceAt(move.to);
                if(piece != null && piece.getColor() == color){
                    valid[i] = false;
                    continue;
                }
            } 
        }
    }

    private void captureMoveFilter(List<Move> possibleMoves, boolean[] valid, Board board, Color color){
        // Capture moves only
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                Pieces destPiece = board.getPieceAt(move.to);
                if(destPiece != null && destPiece.getColor() != color){
                    // capture move
                    if(!move.canCapture)
                        valid[i] = false;
                }
            }
        }
    }

    private void normalMoveFilter(List<Move> possibleMoves, boolean[] valid, Board board, Color color){
        // Normal moves only
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                Pieces destPiece = board.getPieceAt(move.to);
                if(destPiece == null){
                    // normal move
                    if(!move.canMove)
                        valid[i] = false;
                }
            }
        }
    }

    private void postCheckFilter(List<Move> possibleMoves, boolean[] valid, Board board, Color color, Position from){
        // After move there should not be the player's check       
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                // Simulate move
                Pieces capturedPiece = board.getPieceAt(move.to);
                board.movePieceTemp(from, from);
                if(isCheck(board, color)){
                    valid[i] = false;
                }
                // Undo move
                board.movePieceTemp(move.to, from);
                board.setPieceAt(move.to, capturedPiece);
            }
        }
    }

    private void jumpFilter(List<Move> possibleMoves, boolean[] valid, Board board, Color color, Position from){
        Pieces piece = board.getPieceAt(from);
        if(piece.canJump()){
            return; 
        }
        boolean obstacles;
        // there are 8 possible directions to move in
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                int[] direction = getDirection(from, move.to);
                if(direction[0] == 0 && direction[1] == 0){
                    // same position, should not happen as filtered earlier
                    valid[i] = false;
                    continue;
                }
                // walk in the direction and check for obstacles
                obstacles = false;
                int x = from.x + direction[0];
                int y = from.y + direction[1];
                
                while(x != move.to.x || y != move.to.y){
                    Pieces intermediatePiece = board.getPieceAt(new Position(x,y));
                    if(intermediatePiece != null){
                        obstacles = true;
                        break;
                    }
                    x += direction[0];
                    y += direction[1];
                }
                if(obstacles)
                    valid[i] = false;
            }
        }
    }

    private void removeNonCapture(List<Move> possibleMoves, boolean[] valid, Pieces piece){
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                Move move = possibleMoves.get(i);
                if(!move.canCapture){
                    valid[i] = false;
                }
            }
        }
    }

    private int[] getDirection(Position from, Position to){
        // Signum : Postive to +1, Negative to -1, Zero to 0
        // cannot be 0,0 as sameMoves are not possible (removed in isSameColorFilter)
        int dx = Integer.signum(to.x - from.x);       
        int dy = Integer.signum(to.y - from.y);
        return new int[]{dx, dy};
    }

    public List<Move> getValidMoves(Board board, Position from){

        Pieces piece = board.getPieceAt(from);
        if(piece == null) return new ArrayList<>();
        
        Color color = piece.getColor();
        List<Move> possibleMoves = piece.getMoves(from);        // all possible moves without considering checks (even boundChecks)
        List<Move> validMoves;
        boolean[] valid = new boolean[possibleMoves.size()];
        Arrays.fill(valid, true);

        // all filters to check for valid moves as per standard chess rules (Castling, En passant, Promotion can be added later)
        inBoundsFilter(possibleMoves, valid);
        isSameColorFilter(possibleMoves, valid, board, color);
        jumpFilter(possibleMoves, valid, board, color, from);
        captureMoveFilter(possibleMoves, valid, board, color);     // removes capture moves made by non-capturing pieces(pawn straight moves)
        normalMoveFilter(possibleMoves, valid, board, color);       // removes normal moves made by capturing pieces(pawn diagonal moves)
        postCheckFilter(possibleMoves, valid, board, color, from);

        validMoves = new ArrayList<>();
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                validMoves.add(possibleMoves.get(i));
            }
        }

        return validMoves;
    }

    private List<Move> getAttackSquares(Board board, Position from){
        
        Pieces piece = board.getPieceAt(from);
        if(piece == null) return new ArrayList<>();
        
        Color color = piece.getColor();
        List<Move> possibleMoves = piece.getMoves(from);        // all possible moves without considering checks (even boundChecks)
        List<Move> validMoves;
        boolean[] valid = new boolean[possibleMoves.size()];
        Arrays.fill(valid, true);

        inBoundsFilter(possibleMoves, valid);
        isSameColorFilter(possibleMoves, valid, board, color);
        jumpFilter(possibleMoves, valid, board, color, from);
        captureMoveFilter(possibleMoves, valid, board, color);
        normalMoveFilter(possibleMoves, valid, board, color);
        // No postCheckFilter as we want all attack squares : even if it puts own king in check
        // For pawns only we need to remove normal moves as they cannot attack that way
        removeNonCapture(possibleMoves, valid, piece);
        
        validMoves = new ArrayList<>();
        for(int i=0;i<possibleMoves.size();i++){
            if(valid[i]){
                validMoves.add(possibleMoves.get(i));
            }
        }

        return validMoves;
    }

    public boolean isValidMove(List<Move> moves, Position from, Position to){
        // simply iterate through moves
        for (Move move : moves){
            if(move.from.equals(from) && move.to.equals(to)){
                return true;
            }
        }
        return false;
    }

    public boolean isCheck(Board board, Color color){
        boolean inCheck = false;
        Position kingPos = null;

        outer:
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Pieces piece = board.getPieceAt(new Position(i,j));
                if(piece != null && piece.getType() == PieceType.KING && piece.getColor() == color){
                    kingPos = new Position(i,j);
                    break outer;
                }
            }
        }

        if(kingPos == null){
            // King not found, should not happen in a valid game
            return false;
        }

        outer:
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Pieces piece = board.getPieceAt(new Position(i,j));
                if(piece != null && piece.getColor() != color){
                    if(pieceCheckingKing(board, kingPos, new Position(i,j))){
                        inCheck = true;
                        break outer;
                    }
                }
            }
        }

        return inCheck;
    }

    public boolean isCheckmate(Board board, Color color){
        if(!isCheck(board, color)) 
            return false;
        // Check if any move can get out of check
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Pieces piece = board.getPieceAt(new Position(i,j));
                if(piece == null || piece.getColor() != color)
                    continue;
                List<Move> moves = getValidMoves(board, new Position(i,j));
                if(!moves.isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isStalemate(Board board, Color color){
        if(isCheck(board, color)) 
            return false;
        // Check if any valid move exists
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                Pieces piece = board.getPieceAt(new Position(i,j));
                if(piece == null || piece.getColor() != color)
                    continue;
                List<Move> moves = getValidMoves(board, new Position(i,j));
                if(!moves.isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }
}
