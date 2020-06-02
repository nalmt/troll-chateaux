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
public class Configuration {
    int stonePlayer1;
    int stonePlayer2;
    int posTroll;
    double gain;

    public Configuration() {
    }

    public Configuration(int stonePlayer1, int stonePlayer2, int posTroll) {
        this.stonePlayer1 = stonePlayer1;
        this.stonePlayer2 = stonePlayer2;
        this.posTroll = posTroll;
    }

    public double getGain() {
        return gain;
    }

    public int getPosTroll() {
        return posTroll;
    }

    public int getStonePlayer2() {
        return stonePlayer2;
    }

    public int getStonePlayer1() {
        return stonePlayer1;
    }

    public void setPosTroll(int posTroll) {
        this.posTroll = posTroll;
    }

    public void setStonePlayer1(int stonePlayer1) {
        this.stonePlayer1 = stonePlayer1;
    }

    public void setStonePlayer2(int stonePlayer2) {
        this.stonePlayer2 = stonePlayer2;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }
    
    @Override
    public String toString(){
        String toReturn = "";
        
        toReturn += "(" + this.stonePlayer1 + ";" + this.stonePlayer2 + ";" + this.posTroll + ")";
        toReturn += " = " + this.gain;
        
        return toReturn;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) { 
            return true; 
        } 
  
        /* Check if o is an instance of Complex or not 
          "null instanceof [type]" also returns false */
        if (!(o instanceof Configuration)) { 
            return false; 
        } 
        
        Configuration c = (Configuration) o;
        
        if (this.posTroll == c.posTroll
                && this.stonePlayer1 == c.stonePlayer1
                    && this.stonePlayer2 == c.stonePlayer2) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTrivial() {
        return posTroll == -2 || posTroll == 2 || posTroll == 0 || stonePlayer1 == 0 || stonePlayer2 == 0;
    }
}
