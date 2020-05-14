/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * The model class of Troll.
 * @author louis
 */
public class Troll {
    int pos; //The position of the Troll.
    
    //Constructor
    public Troll(int pos) {
        this.pos = pos;
    }
    
    //Accessor
    public int getPos() {
        return pos;
    }

    //Mutator
    public void setPos(int pos) {
        this.pos = pos;
    }

}
