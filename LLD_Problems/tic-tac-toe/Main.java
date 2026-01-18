import java.util.Deque;
import java.util.Scanner;
import java.util.ArrayList;

class Player{
    String name;
    char symbol;

    public Player(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }
}

class Board{

    final int m, n;
    private final char[][] grid;
    private final char epmtySymbol = '-';
    
    public Board(int m, int n){
        this.m = m;
        this.n = n;
        grid = new char[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                grid[i][j] = epmtySymbol;
            }
        }
    }

    public char getSymbol(int x, int y){
        return grid[x][y];
    }

    public boolean isValidMove(int x, int y){
        if(x<0 || x>=m || y<0 || y>=n){
            return false;
        }
        return grid[x][y] == epmtySymbol;
    }

    public boolean setSymbol(int x, int y, char symbol){
        if(isValidMove(x, y)){
            grid[x][y] = symbol;
            return true;
        }
        return false;
    }

    public void showBoard(){
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}

interface Rules {
    boolean checkWin(Board b, char symbol);
    boolean checkDraw(Board b);
}

class StandardRule implements Rules {
    // full row or column or diagonal
    public boolean checkWin(Board b, char symbol){
        // Check rows and columns
        for(int i=0; i<b.m; i++){
            boolean rowWin = true;
            boolean colWin = true;
            for(int j=0; j<b.n; j++){
                if(b.getSymbol(i,j) != symbol){
                    rowWin = false;
                }
                if(b.getSymbol(j,i) != symbol){
                    colWin = false;
                }
            }
            if(rowWin || colWin){
                return true;
            }
        }
        // Check diagonals
        boolean diag1Win = true;
        boolean diag2Win = true;

        for(int i=0; i<b.m; i++){
            if(b.getSymbol(i,i) != symbol){
                diag1Win = false;
            }
            if(b.getSymbol(i,b.n - i - 1) != symbol){
                diag2Win = false;
            }
        }
        
        if(diag1Win || diag2Win){
            return true;
        }

        return false;
    }
    public boolean checkDraw(Board b){
        for(int i=0; i<b.m; i++){
            for(int j=0; j<b.n; j++){
                if(b.getSymbol(i,j) == '-'){
                    return false;
                }
            }
        }
        return true;
    }
}

interface Observer {
    void update(String message);
}

class ConsoleObserver implements Observer {
    public void update(String message){
        System.out.println("Notification: " + message);
    }
}

class Game{
    private final Board board;
    private final Deque<Player> players;
    private final Rules rules;
    private boolean isOver;
    private final ArrayList<Observer> observers;
    
    private Game(GameBuilder builder){
        this.board = builder.board;
        this.players = builder.players;
        this.rules = builder.rules;
        this.observers = builder.observers;
        this.isOver = false;
    }

    private void notify(String message){
        for(Observer ob : observers){
            ob.update(message);
        }
    }

    public void play(){
        Scanner sc = new Scanner(System.in);

        while(!isOver){
            board.showBoard();
            Player current = players.pollFirst();
            System.out.println(current.name + "'s turn. Enter row and column (0 based indexing):");
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(!board.setSymbol(x, y, current.symbol)){
                System.out.println("Invalid move! Try again.");
                players.offerFirst(current);
                continue;
            }
            // check win
            if(rules.checkWin(board, current.symbol)){
                board.showBoard();
                isOver = true;
                notify(current.name + " wins!");
            }
            // check draw
            else if(rules.checkDraw(board)){
                board.showBoard();
                isOver = true;
                notify("Game Draw!");
            }
            players.offerLast(current);
        }

        sc.close();
    }

    static class GameBuilder{
        private Board board;
        private Deque<Player> players;
        private Rules rules;
        private ArrayList<Observer> observers;
        private int count;

        public GameBuilder setBoard(int m, int n){
            if(m<1 || n<1){
                throw new IllegalArgumentException("Board dimensions must be at least 1x1");
            }
            this.board = new Board(m, n);
            return this;
        }

        public GameBuilder setPlayerCount(int count){
            this.count = count;
            return this;
        }

        public GameBuilder addPlayer(String name, char symbol){
            if(this.count == 0){
                throw new IllegalStateException("Player count must be set before adding players");
            }
            if(this.players == null){
                this.players = new java.util.ArrayDeque<>();
            }
            if(this.players.size() >= count){
                System.out.println("Cannot add more players than the specified count");
            }
            this.players.offerLast(new Player(name, symbol));
            return this;
        }

        public GameBuilder setRules(String ruleType){
            if (ruleType.equals("STANDARD")){
                this.rules = new StandardRule();
            } else {
                System.out.println("Unknown rule type. Defaulting to STANDARD.");
                this.rules = new StandardRule();
            }
            return this;
        }

        public GameBuilder addObservers(Observer observers){
            if(this.observers == null){
                this.observers = new ArrayList<>();
                this.observers.add(observers);
                return this;
            }
            else{
                this.observers.add(observers);
                return this;
            }
        }

        public Game build(){
            if(board == null || players == null || rules == null || players.size() != count){
                throw new IllegalStateException("Game cannot be built. Missing components or player count mismatch.");
            }
            return new Game(this);
        }
    }
        
}

public class Main{
    public static void main(String[] args) {

        Observer consoleObserver = new ConsoleObserver();
        Game.GameBuilder builder = new Game.GameBuilder();
        Game game = builder.setBoard(4, 4)
            .setPlayerCount(3)
            .addPlayer("Ayush", 'X')
            .addPlayer("Ayan", 'O')
            .addPlayer("Bot", '@')
            .addObservers(consoleObserver)
            .setRules("STANDARD")
            .build();

        game.play();
    }
}