import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Scanner;


class Dice{
    private final int faces;
    public Dice(int faces) {
        this.faces = faces;
    }
    public int roll(){
        return (int)(Math.random() * faces) + 1;
    }
}

interface IObserver{
    void update(String message);
}

class ConsoleObserver implements IObserver{
    @Override
    public void update(String message) {
        System.out.println(message);
    }
}

class Player{
    String name;
    public Player(String name) {
        this.name = name;
    }
}

interface BoardElement{
    int slides(int position);
    int getStart();
    int getEnd();
}

class Snake implements BoardElement{
    private final int start;
    private final int end;
    public Snake(int start, int end) {
        if (start <= end) {
            throw new IllegalArgumentException("Snake's start must be greater than end.");
        }
        this.start = start;
        this.end = end;
    }
    @Override
    public int getStart() {
        return start;
    }
    @Override
    public int getEnd() {
        return end;
    }
    @Override
    public int slides(int position) {
        if (position == start) {
            return end;
        }
        return position;
    }
}

class Ladder implements BoardElement{
    private final int start;
    private final int end;
    public Ladder(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("Ladder's start must be less than end.");
        }
        this.start = start;
        this.end = end;
    }
    @Override
    public int getStart() {
        return start;
    }
    @Override
    public int getEnd() {
        return end;
    }
    @Override
    public int slides(int position) {
        if (position == start) {
            return end;
        }
        return position;
    }
}

class Board{
    private final int size;
    private final HashMap<Integer, BoardElement> occupiedPositions;
    
    public Board(int size, ArrangementStrategy arrangementStrategy) {
        this.size = size;
        this.occupiedPositions = new HashMap<>();
        arrangementStrategy.populateBoard(this);
    }

    public int getSize() {
        return size;
    }

    public void addElement(BoardElement element) {
        if(occupiedPositions.containsKey(element.getStart())) {
            throw new IllegalArgumentException("Board element already exists at position: " + element.getStart());
        }
        occupiedPositions.put(element.getStart(), element);
    }

    public boolean hasElement(int position) {
        return occupiedPositions.containsKey(position);
    }

    public BoardElement getElementAt(int position) {
        return occupiedPositions.get(position);
    }

    public void show(){
        for(BoardElement element : occupiedPositions.values()){
            if(element instanceof Snake){
                System.out.println("Snake from " + element.getStart() + " to " + element.getEnd());
            } else if(element instanceof Ladder){
                System.out.println("Ladder from " + element.getStart() + " to " + element.getEnd());
            }
        }
    }
}

interface ArrangementStrategy{
    void populateBoard(Board board);
}


class StandardArrangement implements ArrangementStrategy{
    @Override
    public void populateBoard(Board board) {
        if(board.getSize() != 100){
            throw new IllegalArgumentException("Standard arrangement is only defined for board size 100.");
        }
        // Standard board setup : 5 snakes and 5 ladders
        BoardElement[] elements = {
            new Snake(16, 6),
            new Snake(48, 26),
            new Snake(49, 11),
            new Snake(56, 53),
            new Snake(62, 19),
            new Ladder(2, 38),
            new Ladder(7, 14),
            new Ladder(8, 31),
            new Ladder(15, 26),
            new Ladder(21, 42)
        };
        for (BoardElement element : elements) {
            board.addElement(element);
        }
    }
}

class RandomArrangement implements ArrangementStrategy{
    private final int snakeCount;
    private final int ladderCount;

    public RandomArrangement(int snakeCount, int ladderCount) {
        this.snakeCount = snakeCount;
        this.ladderCount = ladderCount;
    }

    @Override
    public void populateBoard(Board board) {
        int size = board.getSize();
        HashSet<Integer> occupiedPositions = new HashSet<>();

        // Add Ladders
        for (int i = 0; i < ladderCount; i++) {
            int start, end;
            do {
                start = (int)(Math.random() * (size - 1)) + 1;
                end = (int)(Math.random() * (size - start)) + start + 1;
            } while (occupiedPositions.contains(start) || occupiedPositions.contains(end) || start >= end);
            occupiedPositions.add(start);
            occupiedPositions.add(end);
            board.addElement(new Ladder(start, end));
        }

        // Add Snakes
        for (int i = 0; i < snakeCount; i++) {
            int start, end;
            do {
                start = (int)(Math.random() * (size - 1)) + 2;
                end = (int)(Math.random() * (start - 1)) + 1;
            } while (occupiedPositions.contains(start) || occupiedPositions.contains(end) || start <= end);
            occupiedPositions.add(start);
            occupiedPositions.add(end);
            board.addElement(new Snake(start, end));
        }
    }
}

class CustomArrangement implements ArrangementStrategy{
    private final List<BoardElement> elements;
    private final HashSet<Integer> occupiedPositions;

    public CustomArrangement(List<BoardElement> elements) {
        this.elements = elements;
        occupiedPositions = new HashSet<>();
        for (BoardElement element : elements){
            if (occupiedPositions.contains(element.getStart()) || occupiedPositions.contains(element.getEnd())) {
                    throw new IllegalArgumentException("Overlapping board elements at position: " + element.getStart() + " or " + element.getEnd());
                }
            occupiedPositions.add(element.getStart());
            occupiedPositions.add(element.getEnd());
        }
    }

    @Override
    public void populateBoard(Board board) {
        for (BoardElement element : elements) {
            if(element.getStart() < 1 || element.getStart()> board.getSize() || element.getEnd() > board.getSize() || element.getEnd()<1){
                throw new IllegalArgumentException("Board element positions must be within board size :)");
            }
            board.addElement(element);
        }
    }
}

interface Rules{
    boolean canMove(int currentPosition, int diceRoll, Board board);
    int getPositionAfterMove(int currentPosition, int diceRoll, Board board, Notifier notifier);
    boolean hasWon(int position, Board board);
}

class StandardRules implements Rules{
    @Override
    public boolean canMove(int currentPosition, int diceRoll, Board board) {
        return currentPosition + diceRoll <= board.getSize();
    }
    @Override
    public int getPositionAfterMove(int currentPosition, int diceRoll, Board board, Notifier notifier) {
        int pos = currentPosition + diceRoll;
        if (board.hasElement(pos)){
            BoardElement element = board.getElementAt(pos);
            if(element instanceof Ladder)
                notifier.notifyObservers("Yay! Landed on a ladder from " + element.getStart() + " to " + element.getEnd());
            else if(element instanceof Snake)
                notifier.notifyObservers("Oops! Landed on a snake from " + element.getStart() + " to " + element.getEnd());
            else
                notifier.notifyObservers("Landed on a board element from " + element.getStart() + " to " + element.getEnd());
            return element.slides(pos);
        }
        return pos;
    }
    @Override
    public boolean hasWon(int position, Board board) {
        return position == board.getSize();
    }
}

class Game{
    private final Board board;
    private final Dice dice;
    private final Rules rules;
    private final Deque<Player> players;
    private final Notifier notifier;
    private final HashMap<Player, Integer> playerPositions;
    private boolean isGameOver;
    private ArrangementStrategy arrangementStrategy;

    private Game(GameBuilder builder){
        this.arrangementStrategy = builder.arrangementStrategy;
        this.board = new Board(builder.boardSize, this.arrangementStrategy);
        this.dice = builder.dice;
        this.rules = builder.rules;
        this.players = new ArrayDeque<>(builder.players);
        this.notifier = builder.notifier;
        this.playerPositions = new HashMap<>();
        for(Player player : players){
            playerPositions.put(player, 0);
        }
        this.isGameOver = false;
    }

    void showPositions(){
        System.out.println("-----------------------------------------------");
        for(Player player : players){
            int position = playerPositions.get(player);
            System.out.println(player.name + " is at position " + position);
        }
        System.out.println("-----------------------------------------------");
    }

    void play(){
        Scanner scanner = new Scanner(System.in);
        board.show();
        while(!isGameOver){
            Player currentPlayer = players.poll();
            int currentPosition = playerPositions.get(currentPlayer);
            notifier.notifyObservers(currentPlayer.name + ", press Enter to roll the dice...");
            scanner.nextLine();
            int diceRoll = dice.roll();
            players.offerLast(currentPlayer);

            notifier.notifyObservers(currentPlayer.name + " rolled a " + diceRoll);
            if(rules.canMove(currentPosition, diceRoll, board)){
                int newPostion = rules.getPositionAfterMove(currentPosition, diceRoll, board, notifier);
                playerPositions.put(currentPlayer, newPostion);
                notifier.notifyObservers(currentPlayer.name + " has moved from "+ currentPosition + " to " + newPostion);
                if(rules.hasWon(newPostion, board)){
                    notifier.notifyObservers(currentPlayer.name + " has won the game!");
                    isGameOver = true;
                }
            }
            else{
                notifier.notifyObservers(currentPlayer.name + " cannot move from " + currentPosition + " with roll " + diceRoll);
            }

            showPositions();
        }   
        scanner.close();
    }

    
    static class GameBuilder{
        private int boardSize;
        private ArrangementStrategy arrangementStrategy;
        private Rules rules;
        private Dice dice;
        int playerCount;
        private final List<Player> players;
        private final Notifier notifier;

        public GameBuilder(){
            boardSize = 100;
            arrangementStrategy = new StandardArrangement();
            rules = new StandardRules();
            dice = new Dice(6);
            players = new ArrayList<>();
            notifier = new Notifier();
            playerCount = 0;
        }

        public GameBuilder setPlayerCount(int count){
            if(count<1){
                throw new IllegalArgumentException("Player count must be at least 1.");
            }
            this.playerCount = count;
            return this;
        }

        public GameBuilder addPlayer(String name){
            if(playerCount==0){
                throw new IllegalStateException("Player count must be set before adding players.");
            }
            if(players.size() >= playerCount){
                throw new IllegalArgumentException("Cannot add more players than the specified player count.");
            }
            players.add(new Player(name));
            return this;
        }

        public GameBuilder addObserver(IObserver observer){
            notifier.addObserver(observer);
            return this;
        }

        public GameBuilder setBoardSize(int size){
            if(size<10){
                throw new IllegalArgumentException("Board size must be at least 10.");
            }
            System.out.println("STANDARD ARRANGEMENT IS ONLY FOR BOARD SIZE 100");
            this.boardSize = size;
            return this;
        }

        public GameBuilder setArrangementStrategy(ArrangementStrategy strategy){
            this.arrangementStrategy = strategy;
            return this;
        }

        public GameBuilder setRules(Rules rules){
            this.rules = rules;
            return this;
        }

        public GameBuilder setDice(Dice dice){
            this.dice = dice;
            return this;
        }

        public Game build(){
            if(playerCount==0){
                throw new IllegalStateException("Player count must be set before building the game.");
            }
            if(players.size() != playerCount){
                throw new IllegalStateException("Number of added players does not match the specified player count.");
            }
            if(boardSize!=100 && arrangementStrategy instanceof StandardArrangement){
                throw new IllegalArgumentException("Standard arrangement strategy can only be used with board size 100.");
            }

            return new Game(this);
        }
    }

}

class Notifier{
    List<IObserver> observers;

    public Notifier() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(IObserver observer){
        observers.add(observer);
    }

    void notifyObservers(String message){
        for(IObserver obs : observers){
            obs.update(message);
        }

    }
}

public class Main{
    public static void main(String[] args) {
        List<BoardElement> customElements = new ArrayList<>();
        customElements.add(new Ladder(1, 49));
        customElements.add(new Ladder(2, 48));
        customElements.add(new Ladder(3, 47));
        customElements.add(new Snake(46, 6));
        customElements.add(new Snake(45, 5));

        Game game = new Game.GameBuilder()
                .setBoardSize(50)
                .setArrangementStrategy(new CustomArrangement(customElements))
                .setPlayerCount(2)
                .addPlayer("Superman")
                .addPlayer("Batman")
                .setDice(new Dice(10))
                .addObserver(new ConsoleObserver())
                .build();
        game.play();
    }
}