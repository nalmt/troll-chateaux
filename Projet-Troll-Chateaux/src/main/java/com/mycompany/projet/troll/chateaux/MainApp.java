package com.mycompany.projet.troll.chateaux;

import Controller.GameController;
import Controller.PlayerController;
import Helper.Solver;
import Model.Castle;
import Model.Configuration;
import Model.Game;
import Model.Map;
import Model.Player;
import Model.Troll;
import View.MainView;
import java.util.ArrayList;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;

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
        
        //System.out.println("Step 0 :\n" + g + "\n");
        
        Solver s = new Solver();
        ArrayList<Configuration> allConfig = s.generateAllConfig(new Configuration(7,7,0));

        for (int i = 0; i < allConfig.size(); i++) {
            System.out.println(allConfig.get(i).toString());
        }
        
        // gc.playOneGame();

        PlayerController pj = new PlayerController(J1);
    }


}