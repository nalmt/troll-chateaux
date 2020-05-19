package com.mycompany.projet.troll.chateaux;

import Controller.GameController;
import Controller.PlayerController;
import Model.Castle;
import Model.Game;
import Model.Map;
import Model.Player;
import Model.Troll;
import View.MainView;

public class MainApp {

    public static void main(String[] args) {


        Map map = new Map(3); //Size = 3*2 + 3 = 9
        
        Castle c1 = new Castle(1, map.getCases().get(0)); //One castle on the first case.

       Castle c2 = new Castle(1, map.getCases().get(map.getCases().size()-1)); //One castle on the last case.
        
        Player J1 = new Player(1,15,c1);
        Player J2 = new Player(2,15,c2);
        
        Troll troll = new Troll(0);

        Game g = new Game(map, J1,J2, troll);
        GameController gc = new GameController(g);
        
        System.out.println("Step 0 :\n" + g + "\n");

        // gc.playOneGame();

        PlayerController pj = new PlayerController(J1);
        pj.gain(3, 3, 3);
    }


}