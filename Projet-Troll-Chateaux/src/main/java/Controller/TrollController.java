package Controller;

import Model.Troll;

/**
 * The controller class of Troll.
 * @author louis
 */
public class TrollController {
    Troll t;

    //Constructor
    public TrollController(Troll t) {
        this.t = t;
    }
    
    //Methods
    public void goFront() {
        this.t.setPos(this.t.getPos() + 1);
    }
    
    public void goBack() {
        this.t.setPos(this.t.getPos() - 1);
    }
}
