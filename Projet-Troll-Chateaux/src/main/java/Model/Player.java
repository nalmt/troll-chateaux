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
    
    //Mutator
    
    //Accessor

}
