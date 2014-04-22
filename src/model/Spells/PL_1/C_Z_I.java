/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Spells.PL_1;

import Character.Character;
import Units.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Tao Zhang & Cameron Simon
 */
public final class C_Z_I {
    JFrame frame;
    Character caster;
    
    public C_Z_I(Character c){
        caster = c;
        prepareGUI();
    }
    
    public void prepareGUI(){
        frame = new JFrame("C_Z_I");
        frame.setSize(100,100);
        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e )
            {  
                System.exit(0); 

            }
        });
        
        JLabel notice = new JLabel("This is C_Z_I");
        
        frame.add(notice);
        frame.setVisible(true);
    }   
    //check mana available
    public boolean checkMana(){
        return caster.cheackManna(1);
    }
    
    //return spell range
    public int getRange(){
        int range = 0;
        // get spells range
        return range;
    }
    
    //get distance to selected unit
    public int getDistance(){
        int distance = 0;
        //get distance
        return distance;        
    }
    
    public void getTarget(){
        // this function is used to get the target to cast spell
    }
    
    public boolean checkLimits(){
        return true; 
    }
    
    public void performSpellEffects(){
        // this function is used to perform the spell effects
        // like cost mana, or the real effects described in rules
        if(checkLimits() == true && checkMana() == true){
            // perform
            ArmyUnit unit1 = new ZombieInfantry(caster, 1);
            unit1.SetLifeSpan(2);
            
            if(getDistance() <= getRange()){
                //peform spell
            }
            // what I am thinking about performing some data effects
            // is that we can make a tmp data file that stores all the
            // char or unit info, 
            // then we can just go into that file and change the data
            // then we read the file again for refresh the game data
            
            
        }else{
            // show warning that it desn't fit all the limitations
        }
        
        
        
    }
        
}