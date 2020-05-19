/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Player;

/**
 *
 * @author louis
 */
public class PlayerController {
    Player p;

    //Constructor
    public PlayerController(Player p) {
        this.p = p;
    }
    
    //Methods
    public int sendRandomNbStone(){
        int nbStoneSent = (int) (Math.random() * (this.p.getNbStone()-1))+1;
        this.p.setNbStone(this.p.getNbStone() - nbStoneSent);
        return nbStoneSent;
    }

    public int gain(int x, int y, int t) {
        if (t == 0) {
            return 0;
        } else if (x == 0) {
            return Integer.compare(t, y);

        } else if (y == 0) {
            return Integer.compare(t, x);

        } else {
            int[][] matrix = new int[x][y];

            for(int i = 0; i<x ; i++) {
                for(int j = 0; j<y ; j++) {
                    matrix[i][j] = gain(i, j, t);
                    // System.out.println("x : "+ i + "y : " + j + "t : " + t);
                    // System.out.println(Arrays.deepToString(matrix));
                }
            }

            //TODO à partir de matrix faire un pl et retourner la valeur
            // pour l'instant on retourne matrix[0][0], c'est juste histoire de retourner un truc
            return matrix[0][0];
        }
    }
}
