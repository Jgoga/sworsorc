/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Units;
import Character.Characters;
/**
 *
 * @author matt
 */
public class CentauroidCavalry extends Conjured{

    public CentauroidCavalry(Characters c, int lc) {
        super(c, lc);
        strength = 3;
        movement = 9;
        demoralizedStrength = 1;
        this.ResetWorkingMovement();
    }
    
    
}
