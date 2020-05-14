/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Castle;
import Model.Game;
import Model.Player;
import Model.Troll;

/**
 *
 * @author louis
 */
public class GameController {
    Game g;

    //Constructor
    public GameController(Game g) {
        this.g = g;
    }
    
    //Methods
    public void playOneStep(){
        PlayerController pc1 = new PlayerController(this.g.getPlayer1());
        PlayerController pc2 = new PlayerController(this.g.getPlayer2());
        
        TrollController tc = new TrollController(this.g.getTroll());
        
        int stoneSentByJ1 = pc1.sendRandomNbStone();
        int stoneSentByJ2 = pc2.sendRandomNbStone();
        
        if (stoneSentByJ1 < stoneSentByJ2) {
            tc.goBack();
        } else if (stoneSentByJ1 > stoneSentByJ2) {
            tc.goFront();
        }
        
        System.out.println("J1 has sent " + stoneSentByJ1 + " stones.");
        System.out.println("J2 has sent " + stoneSentByJ2 + " stones.");
        System.out.println("J1 keep " + this.g.getPlayer1().getNbStone());
        System.out.println("J2 keep " + this.g.getPlayer2().getNbStone());
        System.out.println(this.g);
    }
    
    public Player winner(){
        Player toReturn = new Player();
        
        Player draw = new Player(0,0,new Castle());
        Player p1 = this.g.getPlayer1();
        Player p2 = this.g.getPlayer2();
        
        Troll t = this.g.getTroll();
        
        if (p1.getNbStone() == 0) {
            if (t.getPos()-p2.getNbStone() < 0) {
                return p2;
            } else if (t.getPos()-p2.getNbStone() == 0) {
                return draw;
            } else {
                return p1;
            }
        } else if (p2.getNbStone() == 0) {
            if (t.getPos()+p1.getNbStone() > 0) {
                return p1;
            } else if (t.getPos()+p1.getNbStone() == 0) {
                return draw;
            } else {
                return p2;
            }
        } else {
            if (t.getPos() == p1.getCastle().getPos()) {
                return p2;
            } else if (t.getPos() == p2.getCastle().getPos()) {
                return p1;
            }
        }
        
        return toReturn;
    }
    
    public void playOneGame(){
        int cptStep = 1;
        
        while (this.winner().getId() == -1) {
            System.out.println("Step " + cptStep + " :\n");
            this.playOneStep();
        }
        
        if (this.winner().getId() == 0){
            System.out.println("DRAW !!!!");
        } else {
            System.out.println("\nAND THE WINNER IS J" + this.winner().getId() + " !!!!");
        }
    }
}
