package sshexmap;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/* ABC for diplomacy and game map, so they can both be drawn */
public abstract class HexMap {
    HashMap <String, Hex> hexHash = new HashMap();
    private DocumentBuilderFactory factory; 
    private DocumentBuilder builder;
    private Document doc;
    private File file;// = new File("resources/MainMap.xml");
    
    HexMap(String filename) {
        file = new File(filename);
        factory = DocumentBuilderFactory.newInstance();
        try {
            builder = factory.newDocumentBuilder();
            factory.setValidating(true);
            doc = builder.parse(file);
        
        } catch (ParserConfigurationException|SAXException ex) {
            Logger.getLogger(DiplomacyMap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(DiplomacyMap.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not find " + filename);
            System.out.println("Working Directory = " + System.getProperty("user.dir"));
        }        
    }
    
    void BuildMap() {        
        NodeList listOfHex = doc.getElementsByTagName("hex");
        for(int s = 0; s < listOfHex.getLength(); s++){
            Node hex = listOfHex.item(s);
            Hex h = makeHex(hex);
            hexHash.put(h.GetID(), h);
        }     
    }

    public Hex GetHex(String id){
        return hexHash.get(id);
    }    
    
    /**
     * get hex from integer coordinates. Right now this is 1-indexed
     */
    public Hex GetHex(int x, int y){
        return GetHex(GetIDFromCoords(x, y));
    }  

    public abstract int GetRows();
    public abstract int GetColumns();
    abstract Hex makeHex(Node h);
    

    //if 0 first row is like the 1st or 3rd rows in the game map (high or even)
    //if 1 first row is like the 2nd or 4th row in the game map (low or odd)
    public abstract boolean LowFirstRow();
    
    public static String GetIDFromCoords(int x, int y) {
        return String.format("%02d%02d", x, y);
    }
    
    public static int[] GetCoordsFromID(String hex) {
        int[] res = new int[2];
        int i = Integer.parseInt(hex);
        res[0] = (i / 100);
        res[1] = (i % 100);
        return res;
    }
    
    /**
     * return the distance (in hexagons) between two hexes.
     * For instance if both hexes are the same the distance will be 0,
     * while if the hexes are neighbors the distance will be 1.
     * @param hex1 A hexagon
     * @param hex2 Another hexagon!
     * @return the distance between them!
     **/
    public static int distance(String hex1, String hex2) {
        int[] coords1 = GetCoordsFromID(hex1);
        int[] coords2 = GetCoordsFromID(hex2);
        int x1 = coords1[0], x2 = coords2[0];
        int y1 = coords1[1], y2 = coords2[1];
        int xd = Math.abs( x2 - x1 );
        int yd = Math.abs( y2 - y1 );
        
        //if both hexes are in same column, return y distance
        if(xd == 0) {
            return yd;
        }
        
        //if both hexes are in different columns, things are more complicated
        //hopefully this works, really I'm just making stuff up
        //that's what unit tests are for, right?
        int min_y = y1 - (xd+((x1  )%2)) / 2;
        int max_y = y1 + (xd+((x1+1)%2)) / 2;
        
        if(y2 < min_y) {
            return xd + min_y - y2;
        }
        else if(y2 <= max_y) {
            return xd;
        }
        else {
            return xd + (y2 - max_y);
        }
    }
    public static int distance(Hex hex1, Hex hex2) {
        return distance(hex1.GetID(), hex2.GetID());
    }
  
}
