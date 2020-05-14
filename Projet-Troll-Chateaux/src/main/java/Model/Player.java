package Model;

/**
 * The model class of a player.
 * @author louis
 */
public class Player {
    int id; //Player 1 or 2
    int nbStone; //Number of stones remaining for the player.
    Castle castle; //The player's castle.
    boolean win; //Now if the player win : 1 = win, 0 = lose.
    
    //Constructor
    public Player(int id, int nbStone, Castle castle) {
        this.id = id;
        this.nbStone = nbStone;
        this.castle = castle;
        this.win = false;
    }  

    public Player() {
        this.id = -1;
    }
 
    //Mutator
    public void setNbStone(int nbStone) {
        this.nbStone = nbStone;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setCastle(Castle castle) {
        this.castle = castle;
    }
    
    //Accessor
    public boolean isWin() {
        return win;
    }

    public int getNbStone() {
        return nbStone;
    }

    public Castle getCastle() {
        return castle;
    }

    public int getId() {
        return id;
    }
}
