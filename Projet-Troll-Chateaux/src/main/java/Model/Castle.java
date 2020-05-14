/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * Model class of a castle.
 * @author louis
 */
public class Castle {
    int id;
    int pos; //The position on the map.
    
    //Constructor
    public Castle() {
    }
    
    public Castle(int id, int pos) {
        this.id = id;
        this.pos = pos;
    }
    
    //Mutator
    public void setPos(int pos) {
        this.pos = pos;
    }
      
    //Accessor
    public int getPos() {
        return pos;
    }
}
