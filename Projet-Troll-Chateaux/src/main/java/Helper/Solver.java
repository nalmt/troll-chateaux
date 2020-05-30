package Helper;

import Model.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.LinearConstraint;
import org.apache.commons.math3.optim.linear.LinearConstraintSet;
import org.apache.commons.math3.optim.linear.LinearObjectiveFunction;
import org.apache.commons.math3.optim.linear.NonNegativeConstraint;
import org.apache.commons.math3.optim.linear.Relationship;
import org.apache.commons.math3.optim.linear.SimplexSolver;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;


/**
 * Solver class
 * @author louis
 */
public class Solver {

    public Solver() {
    }   
    
    public ArrayList<Configuration> generateAllConfig(Configuration c) {
        ArrayList<Configuration> toReturn = this.generateTrivialConfig(c);
        
        int nbStone1 = c.getStonePlayer1();
        int nbStone2 = c.getStonePlayer2();
        int posT = c.getPosTroll();
        
        for (int i = 0; i < nbStone1; i++) {
            for (int j = 0; j < nbStone2; j++) {
                for (int t = -2; t <= 2; t++) {
                    Configuration toTest = new Configuration(i,j,t);
                    if (trivialCases(i,j,t) == -2) {
                        toTest.setGain(this.solve(toTest, toReturn));
                        toReturn.add(toTest);
                    }
                }
            }
        }
        
        return toReturn;
    }
    
    public ArrayList<Configuration> generateTrivialConfig(Configuration c){
        ArrayList<Configuration> toReturn = new ArrayList<>();
        
        int nbStone1 = c.getStonePlayer1();
        int nbStone2 = c.getStonePlayer2();
        int posT = c.getPosTroll();
        
        for (int i = 0; i < nbStone1; i++) {
            for (int j = 0; j < nbStone2; j++) {
                for (int t = -2; t <= 2; t++) {
                    Configuration toTest = new Configuration(i,j,t);
                    if (trivialCases(i,j,t) != -2) {
                        toTest.setGain(trivialCases(i,j,t));
                        toReturn.add(toTest);
                    }
                }
            }
        }
        
        return toReturn;
    }
    
    public double solve(Configuration c, ArrayList<Configuration> allConfig){   
        double toReturn = -2.0;
        
        int nbStone1 = c.getStonePlayer1();
        int nbStone2 = c.getStonePlayer2();
        int posT = c.getPosTroll();
        
        int nbVar = nbStone1+1;
        
        System.out.print(nbStone1 + " " + nbStone2 + " " + posT);
        
        double[][] tabGain = new double[nbStone1][nbStone2];
        
        for (int i = nbStone1-1; i >= 0; i--) {
            for (int j = nbStone2-1; j >= 0; j--) {
                Configuration toSearch = new Configuration();
                if (nbStone1-i > nbStone2-j){
                    toSearch = new Configuration(i,j,posT+1);
                } else if (nbStone1-i < nbStone2-j) {
                    toSearch = new Configuration(i,j,posT-1);
                } else {
                    toSearch = new Configuration(i,j,posT);
                }
                
                for (int k = 0; k < allConfig.size(); k++){
                    if (toSearch.equals(allConfig.get(k))){
                        tabGain[i][j] = allConfig.get(k).getGain();
                    }
                }
            } 
        }  
       
        SimplexSolver solver = new SimplexSolver();

        NonNegativeConstraint nonneg = new NonNegativeConstraint(false);
            
        ArrayList<LinearConstraint> constraints = new ArrayList<>();
            
        // add constraints
        for (int i = nbStone2-1; i >= 0; i--){
            double[] constr = new double[nbVar];
                
            for (int j = nbStone1-1; j >= 0; j--){
                constr[j] = tabGain[j][i];
            }
                
            constr[nbVar-1] = -1.0;
                
            constraints.add(new LinearConstraint(constr, Relationship.GEQ, 0.0));
        }
            
        double[] constrEq = new double[nbVar];
        for (int i = 0; i < nbVar-1; i++) { 
            constrEq[i] = 1.0;
        }
            
        constrEq[nbVar-1] = 0.0;
            
        constraints.add(new LinearConstraint(constrEq, Relationship.EQ, 1.0));
            
        for (int i = 0; i < nbVar-1; i++) {
            double[] constrSign = new double[nbVar];
            for (int j = 0; j < nbVar; j++){
                if (j == i) {
                    constrSign[j] = 1.0;
                } else {
                    constrSign[j] = 0.0;
                }
            }
                
            constraints.add(new LinearConstraint(constrSign, Relationship.GEQ, 0.0));
        }

        // set objective function
        double[] objF = new double[nbVar];
        for (int i = 0; i < nbVar-1; i++) {
            objF[i] = 0.0;
        }
            
        objF[nbVar-1] = 1.0;
            
        LinearObjectiveFunction objective = new LinearObjectiveFunction(objF, 0.0);

        LinearConstraintSet constraintSet = new LinearConstraintSet(constraints);
        PointValuePair optimal = solver.optimize(objective, nonneg, constraintSet, GoalType.MAXIMIZE);
       
        return Math.floor(optimal.getValue()*10)/10;
    }
    
    public int trivialCases(int x, int y, int t) {
        if (t == -2) {
            return -1;
        } else if (t == 2) {
            return 1;
        } else if (t == 0) {
            if(x == y){
                return 0;
            } else if (x > y) {
                    return 1;
            } else if (x < y) {
                return -1;
            }
        } else if (x == 0) {
            if (t-y < 0){
                return -1;
            } else if (t-y > 0){
                return 1;
            } else {
                return 0;
            }
        } else if (y == 0) {
            if (t+x > 0){
                return -1;
            } else if (t+x < 0){
                return 1;
            } else {
                return 0;
            }
        }
        
        return -2;
    }
}
