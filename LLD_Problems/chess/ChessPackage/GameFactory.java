package ChessPackage;

class GameFactory {
    Game createGame(User u1, User u2){ 
        return new Game(u1, u2, new StandardRule(), 0,0 );
            
    }
    Game createGame(User u1, User u2, String ruleType){
        Rule rule;
        if(ruleType.equals("Standard")) 
            rule = new StandardRule();
        else 
        {   
            System.out.println("Invalid rule type. Using StandardRule by default.");
            rule = new StandardRule();
        }
        return new Game(u1, u2, rule, 0, 0);
            
    }
    Game createGame(User u1, User u2, String ruleType, int pointsWin, int pointsLoss){ 
        Rule rule;
        if(ruleType.equals("Standard")) 
            rule = new StandardRule();
        else 
        {   
            System.out.println("Invalid rule type. Using StandardRule by default.");
            rule = new StandardRule();
        }
        return new Game(u1, u2, rule, pointsWin, pointsLoss);
    }

    Game createGame(User u1, User u2, int pointsWin, int pointsLoss){ 
        return new Game(u1, u2, new StandardRule(), pointsWin, pointsLoss);
    }

}
