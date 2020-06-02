package Helper;

import Model.Configuration;
import java.util.ArrayList;
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

    public ArrayList<Configuration> generateConfigs(Configuration c) throws Exception {
        ArrayList<Configuration> configs = new ArrayList<>();

        for (int i = 0 ; i < c.getStonePlayer1(); i++) {
            for (int j = 0 ; j < c.getStonePlayer2(); j++) {
                for (int t = -2 ; t <= 2 ; t++) {
                    Configuration toTest = new Configuration(i, j, t);

                    if (toTest.isTrivial()) {
                        toTest.setGain(computeTrivialConfig(i, j, t));
                    } else {
                        toTest.setGain(solve(toTest, configs));
                    }
                    configs.add(toTest);
                }
            }
        }
        return configs;
    }

    
    public double solve(Configuration c, ArrayList<Configuration> allConfig){   

        int nbStone1 = c.getStonePlayer1();
        int nbStone2 = c.getStonePlayer2();
        int posT = c.getPosTroll();
        
        int nbVar = nbStone1 + 1;
        
        System.out.print(nbStone1 + " " + nbStone2 + " " + posT);
        
        double[][] tabGain = new double[nbStone1][nbStone2];
        
        for (int i = nbStone1 - 1 ; i >= 0 ; i--) {
            for (int j = nbStone2 - 1 ; j >= 0 ; j--) {
                Configuration toSearch;
                if (nbStone1 - i > nbStone2 - j){
                    toSearch = new Configuration(i, j,posT+1);
                } else if (nbStone1 - i < nbStone2 - j) {
                    toSearch = new Configuration(i, j,posT-1);
                } else {
                    toSearch = new Configuration(i, j, posT);
                }

                for (Configuration configuration : allConfig) {
                    if (toSearch.equals(configuration)) {
                        tabGain[i][j] = configuration.getGain();
                    }
                }
            } 
        }  
       
        SimplexSolver solver = new SimplexSolver();

        NonNegativeConstraint nonneg = new NonNegativeConstraint(false);
            
        ArrayList<LinearConstraint> constraints = new ArrayList<>();
            
        // add constraints
        for (int i = nbStone2 - 1 ; i >= 0 ; i--){
            double[] constr = new double[nbVar];
                
            for (int j = nbStone1 - 1 ; j >= 0 ; j--){
                constr[j] = tabGain[j][i];
            }
                
            constr[nbVar - 1] = -1.0;
                
            constraints.add(new LinearConstraint(constr, Relationship.GEQ, 0.0));
        }
            
        double[] constrEq = new double[nbVar];
        for (int i = 0 ; i < nbVar - 1 ; i++) {
            constrEq[i] = 1.0;
        }
            
        constrEq[nbVar - 1] = 0.0;
            
        constraints.add(new LinearConstraint(constrEq, Relationship.EQ, 1.0));
            
        for (int i = 0 ; i < nbVar - 1 ; i++) {
            double[] constrSign = new double[nbVar];
            for (int j = 0 ; j < nbVar ; j++){
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
    
    private int computeTrivialConfig(int x, int y, int t) throws Exception {
        if (t == -2) {
            return -1;
        } else if (t == 2) {
            return 1;
        } else if (t == 0) {
            if(x == y){
                return 0;
            } else if (x > y) {
                    return 1;
            } else {
                return -1;
            }
        } else if (x == 0) {
            if (t - y < 0){
                return -1;
            } else if (t - y > 0){
                return 1;
            } else {
                return 0;
            }
        } else if (y == 0) {
            if (t + x > 0) {
                return -1;
            } else if (t + x < 0) {
                return 1;
            } else {
                return 0;
            }
        } else {
            throw new Exception("You try to compute a non-trivial configuration!");
        }
    }
}
