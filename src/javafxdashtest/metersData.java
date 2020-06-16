/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdashtest;

import java.util.ArrayList;

public class metersData {
    private ArrayList<meterData> lijst;
    ApiConnector conn = new ApiConnector();
    

public metersData(String ean_nummer, String startdate, String enddate) {
    if(startdate.equals("") && enddate.equals("")) 
    lijst = conn.DataConnection(ean_nummer);
    else {
     lijst = conn.DataConnectionSpecificPeriod(ean_nummer, startdate, enddate);
    }    
  }
  
  public void voegtoe( meterData data ) {
    lijst.add( data );
  }
  
  public void print() {
    for( meterData data : lijst ) {
      System.out.println( data );
    }
  }  
  public ArrayList<meterData> returnMetersData(){
      return lijst;
  }
  
}