/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Spells.PL_4;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Tao Zhang & Cameron Simon
 */
public final class Vortex_Creation {
    JFrame frame;
    
    public Vortex_Creation(){
        prepareGUI();
    }
    
    public void prepareGUI(){
        frame = new JFrame("Vortex_Creation");
        frame.setSize(100,100);
        frame.addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing( WindowEvent e )
            {  //System.exit(0); 
                frame.dispose();
            }
        });
        
        JLabel notice = new JLabel("This is Vortex_Creation");
        
        frame.add(notice);
        frame.setVisible(true);
    }   
    
     //check mana available
    public boolean checkMana(){
        boolean mana = false;
        
        //if( enough mana ){
          //  mana = true;
        //}
        //else{ print message that not enough mana};
        
        return mana;
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
        boolean limit = false;
        //No more than one Vortex may be created in each Vortex hex
        
        //if( fit all the limits ){
          //  limit = true;
        //}
        
        return limit; 
    }
    
    public void performSpellEffects(){
        // this function is used to perform the spell effects
        // like cost mana, or the real effects described in rules
        if(checkLimits() == true && checkMana() == true){
            // perform
            getTarget();
            //check to make sure chosen target is a vortex hex
            //if not return error
            if(getDistance() <= getRange()){
                //create vortex
                    //remove 2 manna points per vortex created
                
                
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
        
}