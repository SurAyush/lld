package ChessPackage;

public class User{
    private String name;
    private final int id;
    private int wins;
    private int losses;
    private int draws;
    private int ELO;
    private int tolerance;

    public User(String name, int id){
        this.name = name;
        this.id = id;
        this.wins = 0;
        this.losses = 0;
        this.draws = 0;
        this.ELO = 1000; // default ELO
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return id;
    }

    public int getWins(){
        return wins;
    }

    public int getLosses(){
        return losses;
    }

    public int getDraws(){
        return draws;
    }

    public int getELO(){
        return ELO;
    }
    
    public int getTolerance(){
        return tolerance;
    }
    
    public void setTolerance(int tolerance){
        this.tolerance = tolerance;
    }

    void incrementWins(){
        wins++;
    }

    void incrementLosses(){
        losses++;
    }

    void incrementDraws(){
        draws++;
    }

    void addPoints(int points){
        ELO += points;
    }
}

