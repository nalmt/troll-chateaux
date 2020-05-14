/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author louis
 */
public class Game {
    Map map;
    Player player1, player2;
    Troll troll;

    public Game(Map map, Player player1, Player player2, Troll troll) {
        this.map = map;
        this.player1 = player1;
        this.player2 = player2;
        this.troll = troll;
    }

    //Accessor
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Troll getTroll() {
        return troll;
    }

    public Map getMap() {
        return map;
    }
  
    //Methods
    @Override
    public String toString() {
        String toReturn = "C1";
        
        for (int i = -map.getM()+1; i < troll.getPos(); i++) {
            toReturn += " _ ";
        }
        
        toReturn += " T ";
        
        for (int i =  troll.getPos(); i < map.getM()-1; i++) {
            toReturn += " _ ";
        }
        
        toReturn += "C2";
        return toReturn;
    }
}
