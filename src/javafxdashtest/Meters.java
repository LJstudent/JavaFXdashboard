/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

import java.util.ArrayList;

/**
 *
 * @author Leon
 */
public class Meters {
    
private ArrayList<Meter> lijst;
ApiConnector conn = new ApiConnector();
  
  public Meters() {
     lijst = conn.SensorInfoConnection();
  }
  
  public void voegtoe( Meter meter ) {
    lijst.add( meter );
  }
  
  public void print() {
    for( Meter meter : lijst ) {
      System.out.println( meter );
    }
  }  

  public boolean check( int ean_nummer, String postal_code ) {
    boolean CheckUserPass = false; 
    Meter meter = zoek( ean_nummer, postal_code  );
    if( meter != null ) {
      CheckUserPass = true;
    }
    return CheckUserPass;
  }
  
  public Meter zoek( int ean_nummer, String postal_code  ) {
    for( Meter meter : lijst ) {
      if( (ean_nummer == meter.getEan_nummer()) && (postal_code.equals(meter.getPostal_code())))
        return meter;
        
    }
    return null; 
  }  
}
