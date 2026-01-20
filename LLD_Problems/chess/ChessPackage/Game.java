package ChessPackage;
import java.util.List;
import java.util.Scanner;

public class Game{
    private final Board board;
    private final User whitePlayer;
    private final User blackPlayer;
    private final int pointsWin;
    private final int pointsLoss;
    private final Rule rule;
    private boolean whiteTurn;
    private boolean gameActive;
    private Color winner;
    private boolean isDraw;


    Game(User whitePlayer, User blackPlayer, Rule rule, int pointsWin, int pointsLoss){
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.rule = rule;
        board = new Board();
        whiteTurn = true;
        gameActive = true;
        this.pointsWin = pointsWin;
        this.pointsLoss = pointsLoss;
        this.isDraw = false;
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Game started between " + whitePlayer.getName() + " (White) and " + blackPlayer.getName() + " (Black).");
        while(gameActive){
            board.display();
            System.out.println((whiteTurn ? "White" : "Black") + "'s turn. Enter your move (e.g., e2 e4): ");
            String moveInput = scanner.nextLine();
            // resign command
            if(moveInput.equalsIgnoreCase("resign")){
                winner = whiteTurn ? Color.BLACK : Color.WHITE;
                gameActive = false;
                System.out.println((whiteTurn ? "White" : "Black") + " resigns.");
                break;
            }
            String[] parts = moveInput.split(" ");
            Position from = parsePosition(parts[0]);
            Position to = parsePosition(parts[1]);
            
            if(!makeMove(from, to)){
                System.out.println("Try again.");
                continue;
            }

            whiteTurn = !whiteTurn;
            if(!gameActive)
                board.display();   // final board state
        }

        endgame();
        scanner.close();
    }

    private boolean isValidPosition(Position pos){
        return pos.x >=0 && pos.x <8 && pos.y >=0 && pos.y <8;
    }

    private void endgame(){
        if(isDraw){
            gameDraw();
        } else {
            gameWon();
        }
    }

    private Position parsePosition(String pos){
        int x = pos.charAt(0) - 'a';
        int y = pos.charAt(1) - '1';
        return new Position(x, y);
    } 

    private boolean makeMove(Position from, Position to){

        // standard board validation
        if(!isValidPosition(from) || !isValidPosition(to)){
            System.out.println("Invalid position.");
            return false;
        }

        Pieces piece = board.getPieceAt(from);
        
        // null validation and turn validation
        if(piece == null){
            System.out.println("No piece at the source position.");
            System.out.println(from.x + " " + from.y);
            return false;
        }
        if((whiteTurn && piece.getColor() != Color.WHITE) || (!whiteTurn && piece.getColor() != Color.BLACK)){
            System.out.println("You cannot move other color pieces.");
            return false;
        }

        // rule validation -- dependent on rule implementation
        List<Move> validMoves = rule.getValidMoves(board, from);
        if(!rule.isValidMove(validMoves, from, to)){
            System.out.println("Invalid move.");
            return false;
        }
        
        board.movePiece(from, to);
        
        // notify check, checkmate, stalemate
        Color currentColor = whiteTurn ? Color.WHITE : Color.BLACK;
        Color opponentColor = whiteTurn ? Color.BLACK : Color.WHITE;

        if(rule.isCheck(board, opponentColor)){
            // checkmate
            if(rule.isCheckmate(board, opponentColor)){
                System.out.println((opponentColor == Color.WHITE ? "White" : "Black") + " is in checkmate!");
                winner = currentColor;
                gameActive = false;
            }
            // Stalemate
            else if(rule.isStalemate(board, opponentColor)){
                System.out.println("Stalemate!");
                isDraw = true;
                gameActive = false;
            }
            else{
                System.out.println((opponentColor == Color.WHITE ? "White" : "Black") + " is in check!");
            }
        }

        return true;

    }

    private void gameWon(){
        System.out.println("Game Over! " + (winner == Color.WHITE ? "White" : "Black") + " wins!");
        User winnerUser = (winner == Color.WHITE) ? whitePlayer : blackPlayer;
        winnerUser.incrementWins();
        winnerUser.addPoints(pointsWin);
        User loserUser = (winner == Color.WHITE) ? blackPlayer : whitePlayer;
        loserUser.incrementLosses();
        loserUser.addPoints(-pointsLoss);
    }

    private void gameDraw(){
        System.out.println("Game Over! It's a draw!");
        whitePlayer.incrementDraws();
        blackPlayer.incrementDraws();
    }

}
