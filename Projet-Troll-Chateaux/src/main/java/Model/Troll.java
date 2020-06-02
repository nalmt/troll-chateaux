package Model;

/**
 * The model class of Troll.
 * @author louis
 */
public class Troll {
    int position; //The position of the Troll.
    
    //Constructor
    public Troll(int position) {
        this.position = position;
    }
    
    //Accessor
    public int getPos() {
        return position;
    }

    //Mutator
    public void setPos(int position) {
        this.position = position;
    }

}
