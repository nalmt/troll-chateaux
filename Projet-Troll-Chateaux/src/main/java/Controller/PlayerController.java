/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Player;

/**
 *
 * @author louis
 */
public class PlayerController {
    Player p;

    //Constructor
    public PlayerController(Player p) {
        this.p = p;
    }
    
    //Methods
    public int sendRandomNbStone(){
        int nbStoneSent = (int) (Math.random() * (this.p.getNbStone()-1))+1;
        this.p.setNbStone(this.p.getNbStone() - nbStoneSent);
        return nbStoneSent;
    }
}
