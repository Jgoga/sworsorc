/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ssterrain;

import java.util.ArrayList;
import Units.*;
import sshexmap.MapHex;
/**
 *
 * @author John Goettsche
 * CS 383 Software Engineering
 */
public class HEFord extends EdgeElement {
    @Override
    public HexEdgeType getEdgeType() {
        return HexEdgeType.Ford;
    }
    
    /*
    @Override
    public double getMovementCost(MoveableUnit unit) {
        ArrayList<HexEdgeType> list = new ArrayList<HexEdgeType>();
        list.add(HexEdgeType.Bridge);
        list.add(HexEdgeType.Road);
        list.add(HexEdgeType.Ford);
        list.add(HexEdgeType.Trail);
        if(hex.checkIfCrossed(list)) return 3;
        else return hex.getTerrainType().getMovementCost(unit);
    }

    @Override
    public double getCombatMultiplier(MoveableUnit unit) {
        return 0.5;
    }

    @Override
    public String getCombatEffect(MoveableUnit unit) {
        return "";
    }
    */
}
