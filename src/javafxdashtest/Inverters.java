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
public class Inverters {
    
private ArrayList<Inverter> lijst;
ApiConnector conn = new ApiConnector();
  
  public Inverters() {
     lijst = conn.InverterInfoConnection();
  }
  
  public void voegtoe( Inverter inverter ) {
    lijst.add( inverter );
  }
  
  public void print() {
    for( Inverter inverter : lijst ) {
      System.out.println( inverter );
    }
  }
  public Inverter returninfo(int ean_nummer){
      for(Inverter inverter: lijst) {
          if(ean_nummer == inverter.getEan_nummer()) {
              return inverter;
          }   
      } 
      return null;
  }
}
