package ChessPackage;

import java.util.List;

interface Matchmaking {
    Game findMatch(User player, List<User> availablePlayers);
}
