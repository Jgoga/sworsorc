/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ssterrain;

/**
 *
 * @author John Goettsche
 * CS 383 Software Engineering
 */
public class TTForest implements TerrainType{
    public TTForest(){
        
    }

    @Override
    public double getMovementCost(Unit unit) {
        if(unit.getRace().equals(Race.Spiders))return 1;
        else if(unit.getRace().equals(Race.Elves)) return 2;
        else return 3;
    }

    @Override
    public double getCombatMultiplier(Unit unit) {
        if(unit.getRace().equals(Race.Spiders) || unit.getRace().equals(Race.Elves))return 3;
        else return 2;            
    }

    @Override
    public String getCombatEffect(Unit unit) {
        return "";
    }
    
    @Override
    public String toString(){
        return "Forest";
    }
}
