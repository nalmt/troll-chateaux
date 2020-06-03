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

import java.io.IOException;
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

    public static void main(String[] args) throws Exception {

        Map map = new Map(3); //Size = 3*2 + 3 = 9
        
        Castle c1 = new Castle(1, map.getCases().get(0)); //One castle on the first case.
        Castle c2 = new Castle(1, map.getCases().get(map.getCases().size()-1)); //One castle on the last case.
        
        Player computer = new Player(1,15, c1);
        Player human = new Player(2,15, c2);

        Troll troll = new Troll(0);

        Game g = new Game(map, computer, human, troll);
        GameController gc = new GameController(g);

        gc.play();
    }
}