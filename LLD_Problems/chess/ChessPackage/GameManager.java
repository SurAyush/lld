package ChessPackage;
import java.util.*;

public class GameManager {
    
    private static volatile GameManager instance = null;
    
    private List<Game> activeGames;
    private List<User> waitingUsers;
    private Matchmaking matchmaking;
    private GameFactory gameFactory;


    private GameManager(){
        // Private constructor to prevent instantiation
        activeGames = new ArrayList<>();
        matchmaking = new EloBasedMaking();
        gameFactory = new GameFactory();
    }

    public static GameManager getInstance(){
        if(instance == null){
            synchronized(GameManager.class){
                // critical section
                if(instance == null){      // re-check if a thread has created the instance
                    instance = new GameManager();
                }
            }
        }
        return instance;
    }

    public Game play(User user){
        return matchmaking.findMatch(user, waitingUsers);
    }

    public Game playWithFriend(User user1, User user2){
        return gameFactory.createGame(user1, user2);
    }
}
