/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sshexmap;

import java.awt.Color;
import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Colin Clifford
 */
public class MapViewTest extends TestCase {
    HexMap map;
    
    public MapViewTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
         map = MainMap.GetInstance();
    }
    
    /*@Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }*/

    /**
     * Test of hexAt method, of class MapView.
     */
    public void testHexAt() {
        MapView mv = new MapView(map);
        double radius = mv.radius;
        double height = radius*Math.sqrt(3);
        //test the easy stuff
        assertEquals( "0101", mv.hexAt((int)(radius / 2.0),
                                       (int)(radius / 2.0)));
        assertEquals( "0154", mv.hexAt((int)(radius / 2.0),
                                       (int)(height*54 - height/2.0)));
    }

    /**
     * Test of hexEdgeRegionAt method, of class MapView.
     */
    public void testHexEdgeRegionAt() {
        MapView mv = new MapView(map);
        //just test hex "0104" for now
        double y = mv.height*(3.5);
        double x = mv.width / 2.0;
        
        double sixth = Math.PI*2.0 / 6.0;
        double angle = sixth / 2.0;
        
        for(int i = 0; i < 6; i++) {
            double dx = Math.cos(angle) * 5.0;
            double dy = -(Math.sin(angle) * 5.0);
            int res = mv.hexEdgeRegionAt((int)(x+dx),
                                         (int)(y+dy));
            assertEquals(i, res);
            angle += sixth;
        }
    }

    /**
     * Test of highlight method, of class MapView.
     */
    public void testHighlight_ArrayList() {
        MapView mv = new MapView(map);
        ArrayList<String> list = new ArrayList<String>();
        list.add("1234");
        list.add("4321");
        mv.highlightIDs(list, new Color(1, 2, 3, 4));
        assertEquals(mv.highlightSet.size(), 2);
        assertNotNull(mv.highlightSet.get("1234"));        
        assertNotNull(mv.highlightSet.get("4321"));    
    }

    /**
     * Test of highlight method, of class MapView.
     */
    public void testHighlight_String() {
        MapView mv = new MapView(map);
        mv.highlight("1234", new Color(1, 2, 3, 4));
        assertEquals(mv.highlightSet.size(), 1);
        assertNotNull(mv.highlightSet.get("1234"));
    }

    /**
     * Test of clearHighlights method, of class MapView.
     */
    public void testClearHighlights() {
        MapView mv = new MapView(map);
        ArrayList<String> list = new ArrayList<String>();
        list.add("1234");
        list.add("4321");
        mv.highlightIDs(list, new Color(1, 2, 3, 4));
        mv.highlight("2345", new Color(1, 2, 3, 4));
        mv.clearHighlights();
        assertEquals(mv.highlightSet.size(), 0);
    }

    /**
     * Test of clearHighlight method, of class MapView.
     */
    public void testClearHighlight() {
        MapView mv = new MapView(map);
        ArrayList<String> list = new ArrayList<String>();
        list.add("1234");
        list.add("4321");
        mv.highlightIDs(list, new Color(1, 2, 3, 4));
        mv.highlight("2345", new Color(1, 2, 3, 4));
        mv.clearHighlight("4321");
        assertEquals(mv.highlightSet.size(), 2);
        assertNotNull(mv.highlightSet.get("1234"));        
        assertNotNull(mv.highlightSet.get("2345"));    
        
    }

}
