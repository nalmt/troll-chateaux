package Controller;

import Model.*;

import java.io.IOException;

/**
 *
 * @author louis
 */
public class GameController {
    Game g;

    public GameController(Game g) {
        this.g = g;
    }

    public void play() throws Exception {
        int cptStep = 1;

        while (this.winner().getId() == -1) {
            System.out.println("Step " + cptStep + " :\n");
            this.playOneStep();
        }

        if (this.winner().getId() == 0){
            System.out.println("It's a draw!");
        } else {
            System.out.println("\nThe winner is J" + this.winner().getId() + "!");
        }
    }

    public void playOneStep() throws Exception {
        PlayerController pc1 = new PlayerController(this.g.getPlayer1());
        PlayerController pc2 = new PlayerController(this.g.getPlayer2());
        TrollController tc = new TrollController(this.g.getTroll());

        Player p1 = this.g.getPlayer1();
        Player p2 = this.g.getPlayer2();
        Troll t = this.g.getTroll();

        int stoneSentByJ1 = pc1.sendCarefulNumberOfStones(new Configuration(p1.getNbStone(), p2.getNbStone(), t.getPos()));
        int stoneSentByJ2 = pc2.askUserNumberOfStonesToSend();
        
        if (stoneSentByJ1 < stoneSentByJ2) {
            tc.goBack();
        } else if (stoneSentByJ1 > stoneSentByJ2) {
            tc.goFront();
        }
        
        System.out.println("J1 send " + stoneSentByJ1 + " stones and keeps "+ this.g.getPlayer1().getNbStone()+".");
        System.out.println("J2 send " + stoneSentByJ2 + " stones and keeps "+ this.g.getPlayer2().getNbStone()+".");
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
}
