package Controller;

import Helper.Solver;
import Model.Configuration;
import Model.Player;
import javafx.util.Pair;

import javax.swing.text.ParagraphView;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author louis
 */
public class PlayerController {
    Player p;

    public PlayerController(Player p) {
        this.p = p;
    }

    public int sendRandomNbStone() {
        int nbStoneSent = (int) (Math.random() * (this.p.getNbStone()-1))+1;
        updateUserStoneCount(nbStoneSent);
        return nbStoneSent;
    }

    public int sendCarefulNumberOfStones(Configuration c) throws Exception {

        double[][] tabGain = fillStrategies(c);

        // Each line = one computer's number of rocks it will throw.

        // We pair each line with his lowest gain and his avg gain
        // example :
        // 0 : (-1, 0)
        // 1 : (0, 0.25)
        // 2 : (0, 0)
        List<Pair<Integer,Pair<Double, Double>>> gainAndAvgForEachLine = pairLowestGainAndAvgForEachLine(tabGain);

        // By default we choose the first line and then we'll see
        Pair<Integer,Pair<Double, Double>> chosenLine = gainAndAvgForEachLine.get(0);

        
        // look for the line with the lowest gain
        for(int i = 1 ; i < gainAndAvgForEachLine.size() ; i++) {

            if(gainAndAvgForEachLine.get(i).getValue().getKey() < chosenLine.getValue().getKey()) {
                chosenLine = gainAndAvgForEachLine.get(i);
            }
        }

        // if 2 lignes have the lowest gain, maybe we should use the one with the lowest avg
        for(int i = 1 ; i < gainAndAvgForEachLine.size() ; i++) {

            if(gainAndAvgForEachLine.get(i).getValue().getKey().equals(chosenLine.getValue().getKey())) {
                if(gainAndAvgForEachLine.get(i).getValue().getValue() < chosenLine.getValue().getValue()) {
                    chosenLine = gainAndAvgForEachLine.get(i);
                }
            }

        }

        updateUserStoneCount(chosenLine.getKey() + 1);

        return chosenLine.getKey() + 1;
    }


        private double[][]  fillStrategies(Configuration c) throws Exception {

        int nbStone1 = c.getStonePlayer1();
        int nbStone2 = c.getStonePlayer2();
        int posT = c.getPosTroll();

        Solver s = new Solver();
        ArrayList<Configuration> allConfig = s.generateConfigs(c);

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

        return tabGain;
    }

    private List<Pair<Integer,Pair<Double, Double>>>  pairLowestGainAndAvgForEachLine(double[][] tabGain)  {
        double lowestOfLine = 100.0; // default value doesn't matter, it just need to be higher than real values.
        double avgOfLine = 0.0;

        List<Pair<Integer,Pair<Double, Double>>> listOfTuples = new ArrayList<>();

        for(int i = 0 ; i < tabGain.length ; i++) {
            for(int j = 0 ; j < tabGain[i].length ; j++) {

                if(tabGain[i][j] < lowestOfLine) {
                    lowestOfLine = tabGain[i][j];
                }

                avgOfLine = avgOfLine + tabGain[i][j];
            }
            listOfTuples.add(new Pair<>(i, new Pair<>(lowestOfLine, avgOfLine/tabGain[i].length)));
            avgOfLine = 0;
        }

        return listOfTuples;
    }
    
    public int askUserNumberOfStonesToSend() throws IOException {
        int numberOfStonesToSend = -1;

        System.out.println("How many stones do you want to send?");
        while(numberOfStonesToSend < 0 || numberOfStonesToSend > this.p.getNbStone()) {
            System.out.print("Choose between 0 and "+this.p.getNbStone()+": ");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            numberOfStonesToSend = Integer.parseInt(input);
        }

        updateUserStoneCount(numberOfStonesToSend);
        return numberOfStonesToSend;
    }

    private void updateUserStoneCount(int numberOfStonesSent) {
        this.p.setNbStone(this.p.getNbStone() - numberOfStonesSent);
    }
}
