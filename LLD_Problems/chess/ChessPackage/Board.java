package ChessPackage;

class Board{
    Pieces[][] board;

    Board(){
        board = new Pieces[8][8];
        init();
    }

    private void init(){
        System.out.println("Initializing board...");
        // initialize white pieces
        board[0][0] = new Rook(Color.WHITE);
        board[1][0] = new Knight(Color.WHITE);
        board[2][0] = new Bishop(Color.WHITE);
        board[3][0] = new Queen(Color.WHITE);
        board[4][0] = new King(Color.WHITE);
        board[5][0] = new Bishop(Color.WHITE);
        board[6][0] = new Knight(Color.WHITE);
        board[7][0] = new Rook(Color.WHITE);
        
        for(int i=0;i<8;i++){
            board[i][1] = new Pawn(Color.WHITE);
        }

        // initialize black pieces
        board[0][7] = new Rook(Color.BLACK);
        board[1][7] = new Knight(Color.BLACK);
        board[2][7] = new Bishop(Color.BLACK);
        board[3][7] = new Queen(Color.BLACK);
        board[4][7] = new King(Color.BLACK);
        board[5][7] = new Bishop(Color.BLACK);
        board[6][7] = new Knight(Color.BLACK);
        board[7][7] = new Rook(Color.BLACK);
        
        for(int i=0;i<8;i++){
            board[i][6] = new Pawn(Color.BLACK);
        }
    }

    Pieces getPieceAt(Position position){
        return board[position.x][position.y];
    }

    void RemovePieceAt(Position position){
        board[position.x][position.y] = null;
    }

    void setPieceAt(Position position, Pieces piece){
        board[position.x][position.y] = piece;
    }

    void movePiece(Position from, Position to){
        Pieces piece = getPieceAt(from);
        setPieceAt(to, piece);
        RemovePieceAt(from);
        piece.moved();
    }

    void movePieceTemp(Position from, Position to){
        // for simulations
        Pieces piece = getPieceAt(from);
        setPieceAt(to, piece);
        RemovePieceAt(from);
    }

    // FROM CHATGPT
    void display() {
        System.out.println("    a  b  c  d  e  f  g  h");
        System.out.println("  +------------------------+");

        for (int j = 7; j >= 0; j--) {
            System.out.print((j + 1) + " | ");

            for (int i = 0; i < 8; i++) {
                Pieces piece = board[i][j];
                if (piece == null) {
                    System.out.print(".  ");
                } else {
                    char c = piece.getColor() == Color.WHITE ? 'W' : 'B';
                    char t = switch (piece.getType()) {
                        case PAWN   -> 'P';
                        case ROOK   -> 'R';
                        case KNIGHT -> 'N';
                        case BISHOP -> 'B';
                        case QUEEN  -> 'Q';
                        case KING   -> 'K';
                    };
                    System.out.print("" + c + t + " ");
                }
            }

            System.out.println("| " + (j + 1));
        }

        System.out.println("  +------------------------+");
        System.out.println("    a  b  c  d  e  f  g  h");
    }

}
