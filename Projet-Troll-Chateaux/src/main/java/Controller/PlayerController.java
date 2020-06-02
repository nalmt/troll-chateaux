package Controller;

import Helper.Solver;
import Model.Configuration;
import Model.Player;
import java.io.IOException;
import java.util.ArrayList;
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

        Solver s = new Solver();
        ArrayList<Configuration> allConfig = s.generateConfigs(c);

        s.solve(c, allConfig);

        return 0;
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
