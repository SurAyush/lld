package ChessPackage;
import java.util.List;

class EloBasedMaking implements Matchmaking {
    @Override
    public Game findMatch(User player, List<User> availablePlayers) {
        for (User opponent : availablePlayers) {
            int eloDiff = Math.abs(player.getELO() - opponent.getELO());
            if (eloDiff <= player.getTolerance() && eloDiff <= opponent.getTolerance()) {
                System.out.println("Match found between " + player.getName() + " and " + opponent.getName());
                GameFactory gameFactory = new GameFactory();
                int winElo = eloDiff / 10 + 5;
                int lossElo = eloDiff / 10 - 5;
                return gameFactory.createGame(player, opponent, winElo, lossElo);
            }
        }
        System.out.println("No suitable match found for " + player.getName());
        System.out.println("Appending in waiting list");
        availablePlayers.add(player);

        return null;
    }
}
