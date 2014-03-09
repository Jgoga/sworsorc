/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package buildmapfilel;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 * This program automatically builds the diplomacy map in XML format.
 * @author David Klingenberg  2/20/2014
 */
public class BuildDiplomacyMapFile {
    
    private Formatter output;
    
    public void openFile(){
        try{
            output = new Formatter("DiplomacyMap.xml");
        }
        catch (SecurityException securityException){
            System.err.println ("you do not have write access to this file.");
            System.exit (1);
        }
        catch (FileNotFoundException fileNotFoundException) {
            System.err.println ("error opening or creating file.");
            System.exit (1);
        }
            
    }
    
    public void closeFile(){
        
        output.format("%s", "</map>");
        if (output != null)
            output.close();
    }
    
    public void addHeader(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        output.format("%s", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        output.format("%s", "<!--\r\n");
        output.format("  %s", "This file was auto generated by BuildDiplomacyMapFile.java\r\n\r\n");
        output.format("  %s", "Author: David Klingenberg\r\n");
        output.format("  %s", "Auto generated on: " + dateFormat.format(date) + "\r\n");
        output.format("%s", "-->\r\n");
        output.format("%s", "\r\n");
        output.format("%s", "\r\n");
        output.format("%s", "<!DOCTYPE map [\r\n");
        output.format("  %s", "<!ELEMENT map (hex*)>\r\n");
        output.format("  %s", "<!ELEMENT hex (hexNumber, northHexNumber, northEastHexNumber, southEastHexNumber, southHexNumber, southWestHexNumber, northWestHexNumber, specialHex?)>\r\n");
        output.format("  %s", "<!ELEMENT hexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT northHexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT northEastHexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT southEastHexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT southHexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT southWestHexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT northWestHexNumber (#PCDATA)>\r\n");
        output.format("  %s", "<!ELEMENT specialHex (#PCDATA)>\r\n");
        output.format("%s", "]>\r\n\r\n\r\n");
        output.format("%s", "<map>\r\n");
    }
    
    public void addRecords (){
        
        
        
        for (int a = 1; a <=11; a++)
          for(int b = 1; b <= 11; b++){
           
              if (formatmap(a, b)){
              output.format("  %s", "<hex>\r\n");
                addcell(a, b, "hexNumber");  
                
                
                //north
                if ((a == 1  && b == 4)||(a ==2  && b == 3)||(a == 3  && b == 3)||(a == 4  && b == 2)
                        ||(a == 5  && b == 2)||(a == 6  && b == 1)||(a == 7  && b == 2)
                        ||(a == 8  && b == 2)||(a == 9  && b == 3)||(a == 10  && b == 3)||(a == 11  && b == 4) )
                    addcell(0,0, "northHexNumber");
                else
                    addcell(a, b-1, "northHexNumber");
     
                //northeast
                if ((a == 6 && b == 1)||(a == 7  && b == 2)||(a == 8  && b == 2)||(a == 9  && b == 3)
                        ||(a == 10  && b == 3)||(a == 11  && b == 4)||(a == 11  && b == 5)||(a == 11  && b == 6)
                        ||(a == 11  && b == 7)||(a == 11  && b == 8)||(a == 11  && b == 9))
                    addcell(0,0, "northEastHexNumber");
                else
                    if (a < 6)
                        if (a%2 == 0)
                          addcell(a+1, b, "northEastHexNumber");
                        else
                          addcell(a+1, b-1, "northEastHexNumber");  
                    else
                        if (a%2 != 0)
                            addcell(a+1,b-1,"northEastHexNumber");
                        else    
                            addcell(a+1,b,"northEastHexNumber");
                
                
                //southeast
                if ((a == 6 && b == 11)||(a == 7  && b == 11)||(a == 8  && b == 10)||(a == 9  && b == 10)
                        ||(a == 10  && b == 11)||(a == 11  && b == 4)||(a == 11  && b == 5)||(a == 11  && b == 6)
                        ||(a == 11  && b == 7)||(a == 11  && b == 8)||(a == 11  && b == 9))
                    addcell(0,0, "southEastHexNumber");
                else
                    if (a < 6)
                        if (a%2 != 0)
                          addcell(a+1, b, "southEastHexNumber");
                        else
                          addcell(a+1, b+1, "southEastHexNumber");                        
                    else 
                        if (a%2 == 0)
                            addcell(a+1,b+1,"southEastHexNumber");
                        else    
                            addcell(a+1,b,"southEastHexNumber");
                
                //south
                if ((a == 1  && b == 9)||(a ==2  && b == 9)||(a == 3  && b == 10)||(a == 4  && b == 10)
                        ||(a == 5  && b == 11)||(a == 6  && b == 11)||(a == 7  && b == 11)
                        ||(a == 8  && b == 10)||(a == 9  && b == 10)||(a == 10  && b == 9)||(a == 11  && b == 9) )
                    addcell(0,0, "southHexNumber");
                else
                    addcell(a,b+1, "southHexNumber");
                
                //south west
                if ((a == 1 && b == 4)||(a == 1  && b == 5)||(a == 1  && b == 6)||(a == 1  && b == 7)
                        ||(a == 1  && b == 8)||(a == 1  && b == 9)||(a == 2  && b == 9)||(a == 3  && b == 10)
                        ||(a == 4  && b == 10)||(a == 5  && b == 11)||(a == 6  && b == 11))
                    addcell(0,0, "southWestHexNumber");
                else
                    if (a < 6)
                        if (a%2 != 0)
                          addcell(a-1, b, "southWestHexNumber");
                        else
                          addcell(a-1, b+1, "southWestHexNumber");                        
                    else 
                        if (a%2 == 0)
                            addcell(a-1,b+1,"southWestHexNumber");
                        else    
                            addcell(a-1,b,"southWestHexNumber");
                
                //north west
                if ((a == 1 && b == 4)||(a == 1  && b == 5)||(a == 1  && b == 6)||(a == 1  && b == 7)
                        ||(a == 1  && b == 8)||(a == 1  && b == 9)||(a == 2  && b == 3)||(a == 3  && b == 3)
                        ||(a == 4  && b == 2)||(a == 5  && b == 2)||(a == 6  && b == 1))
                    addcell(0,0, "northWestHexNumber");
                else
                    if (a < 6)
                        if (a%2 != 0)
                          addcell(a-1, b-1, "northWestHexNumber");
                        else
                          addcell(a-1, b, "northWestHexNumber");                        
                    else 
                        if (a%2 == 0)
                            addcell(a-1,b,"northWestHexNumber");
                        else    
                            addcell(a-1,b-1,"northWestHexNumber");
                
                
                // sets the special code for a player diplomacy hex.
                if ((a == 1 && b == 4) || (a == 1 && b == 9) || (a == 6 && b == 1) 
                        || (a == 6 && b == 1) || (a == 6 && b == 11) || (a == 11 && b == 4)
                        || (a == 11 && b == 9))
                    output.format("    %s", "<specialHex>1</specialHex>\r\n");
                else           
                    //  sets the special code for a neutral diplomacy hex  
                    if ( a == 6 && b == 6)
                        output.format("    %s", "<specialHex>2</specialHex>\r\n");
                    else
                        output.format("    %s", "<specialHex>0</specialHex>\r\n");
                
                  output.format("  %s", "</hex>\r\n");  
              }
          }
    }    

    private boolean addcell(int a, int b, String c) {
        if (a == 0 && b == 0){
            output.format("    %s", "<" + c + ">0</" + c + ">\r\n");
            return true;
        }
        if (a < 10 && b < 10 )
            output.format("    %s", "<" + c + ">0" + Integer.toString(a) + "0" + Integer.toString(b) + "</" + c + ">\r\n");
        if ( a >= 10 && b < 10)
            output.format("    %s", "<" + c + ">" + Integer.toString(a) + "0" + Integer.toString(b) + "</" + c + ">\r\n");
        if (a < 10 && b >= 10)
            output.format("    %s", "<" + c + ">0" + Integer.toString(a) + Integer.toString(b) + "</" + c + ">\r\n");
        if (a >= 10 && b >= 10)
            output.format("    %s", "<" + c + ">" + Integer.toString(a) + Integer.toString(b) + "</" + c + ">\r\n");
    
        return true;
    }

    public boolean formatmap(int a, int b) {
        if (a == 1 && (b < 4 || b > 9)) {
            return false;
        }
        if (a == 2 && (b < 3 || b > 9)) {
            return false;
        }
        if (a == 3 && (b < 3 || b > 10)) {
            return false;
        }
        if (a == 4 && (b < 2 || b > 10)) {
            return false;
        }
        if (a == 5 && (b < 2 || b > 11)) {
            return false;
        }
        if (a == 6 && (b < 1 || b > 11)) {
            return false;
        }
        if (a == 7 && (b < 2 || b > 11)) {
            return false;
        }
        if (a == 8 && (b < 2 || b > 10)) {
            return false;
        }
        if (a == 9 && (b < 3 || b > 10)) {
            return false;
        }
        if (a == 10 && (b < 3 || b > 9)) {
            return false;
        }
        if (a == 11 && (b < 4 || b > 9)) {
            return false;
        }
        return true;
    }
    
    
    public static void main(String[] args) {
        BuildDiplomacyMapFile application  =  new BuildDiplomacyMapFile();
        
        application.openFile();
        application.addHeader();
        application.addRecords();
        application.closeFile();
    }
}
