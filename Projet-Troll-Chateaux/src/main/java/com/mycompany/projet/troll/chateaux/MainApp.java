package com.mycompany.projet.troll.chateaux;

import Model.Castle;
import Model.Map;
import Model.Player;
import Model.Troll;
import View.MainView;

import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        Map map = new Map(3); //Size = 3*2 + 3 = 9
        
        Castle c1 = new Castle(1, map.getCases().get(0)); //One castle on the first case.
        Castle c2 = new Castle(1, map.getCases().get(map.getCases().size()-1)); //One castle on the last case.
        
        Player J1 = new Player(1,15,c1);
        Player J2 = new Player(2,15,c2);
        
        Troll troll = new Troll(0);
        
        System.out.println("C1 in " + c1.getPos());
        System.out.println("C2 in " + c2.getPos());
    }

    @Override
    public void start(Stage stage) throws Exception {  
        
    }

}