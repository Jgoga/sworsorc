/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Units;

/**
 *
 * @author matt
 */
public class MoveableUnit {
    protected UnitType UnitType;
    protected int movement;
    protected Race race;
    protected String location;
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
     public void setRace(Race newRace){
        this.race = newRace;
    }
     
    public Race getRace(){
        return race;
    }
    
    public int getMovement() {
        return movement;
    }

    public UnitType getUnitType() {
        return UnitType;
    }

    public void setUnitType(UnitType UnitType) {
        this.UnitType = UnitType;
    }
    
    
}