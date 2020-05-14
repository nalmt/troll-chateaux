package Model;

import java.util.ArrayList;

/**
 * The map of the game, a map is an array of Case.
 * @author louis
 */
public class Map {
    int m; //Size = (m*2)+1
    ArrayList<Integer> cases;
    
    //Constructor

    public Map() {
        this.m = 0;
        this.cases = new ArrayList<>();
    }
    
    public Map(int m) {
        this.m = m;

        //Init cases
        this.cases = new ArrayList<>();
        for (int i = -m; i < m + 1; i++) {
            this.cases.add(i);
        }
    }
    
    //Mutator
    
    //Accessor
    public int getM() {
        return m;
    }

    public ArrayList<Integer> getCases() {
        return cases;
    }
}
