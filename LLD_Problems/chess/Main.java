import ChessPackage.GameManager;
import ChessPackage.User;
import ChessPackage.Game;

public class Main{
    public static void main(String[] args) {
        GameManager gameManager = GameManager.getInstance();   
        User user1 = new User("Tommy", 1);
        User user2 = new User("Arthur", 2);

        Game game = gameManager.playWithFriend(user1, user2);
        game.play();
    }
}









